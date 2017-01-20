package pl.otekplay.loveotek.api.bar;

import net.minecraft.server.v1_7_R4.EntityWither;
import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.utils.PacketUtil;

import java.util.HashMap;
import java.util.UUID;

public class BarAPI {
    private static final HashMap<UUID, Integer> bars = new HashMap<>();

    private static void removeBars(Player p) {
        if (!bars.containsKey(p.getUniqueId())) {
            return;
        }
        int entityID = bars.get(p.getUniqueId());
        bars.remove(p.getUniqueId());
        Packet packet = new PacketPlayOutEntityDestroy(entityID);
        PacketUtil.sendPacket(p, packet);
    }

    public static void setBar(Player p, String message) {
        setBar(p, message, 100);
    }

    public static void setBar(Player p, String message, float health) {
        removeBars(p);
        if (5 > health) {
            return;
        }
        EntityWither wither = createWither(p.getWorld());
        Location dragon = getDragonLocation(p.getLocation());
        Bukkit.getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            wither.setCustomName(message);
            wither.setHealth(health * 3);
            wither.setLocation(dragon.getBlockX(), dragon.getBlockY(), dragon.getBlockZ(), dragon.getYaw(), dragon.getPitch());
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(wither);
            PacketUtil.sendPacket(p, packet);
            bars.put(p.getUniqueId(), wither.getId());
        });
    }

    private static Location getDragonLocation(Location loc) {
        float pitch = loc.getPitch();
        if (pitch >= 55) {
            loc.add(0, -115, 0);
        } else if (pitch <= -55) {
            loc.add(0, 125, 0);
        } else {
            BlockFace direction = getDirection(loc);
            loc = loc.getBlock().getRelative(direction, 115).getLocation();
        }
        return loc;
    }

    private static BlockFace getDirection(Location loc) {
        float dir = Math.round(loc.getYaw() / 90);
        if (dir == -4 || dir == 0 || dir == 4)
            return BlockFace.SOUTH;
        if (dir == -1 || dir == 3)
            return BlockFace.EAST;
        if (dir == -2 || dir == 2)
            return BlockFace.NORTH;
        if (dir == -3 || dir == 1)
            return BlockFace.WEST;
        return null;
    }

    private static EntityWither createWither(World world) {
        return new EntityWither(((CraftWorld)world).getHandle());
    }
}
