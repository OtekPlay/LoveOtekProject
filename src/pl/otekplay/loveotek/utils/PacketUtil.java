package pl.otekplay.loveotek.utils;

import net.minecraft.server.v1_7_R4.Packet;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketUtil {
    public static void sendPacket(Player p, Packet packet) {
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

}
