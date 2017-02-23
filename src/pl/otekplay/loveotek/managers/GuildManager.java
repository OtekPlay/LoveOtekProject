package pl.otekplay.loveotek.managers;


import org.bukkit.Location;
import org.bukkit.Material;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.CuboidType;
import pl.otekplay.loveotek.enums.GuildRank;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.main.Rankings;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GuildSettings;
import pl.otekplay.loveotek.utils.SpaceUtil;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GuildManager {
    private final Map<String, Guild> guilds = new ConcurrentHashMap<>();

    public void createGuild(String tag, String name, UUID leader, Location loc) {
        Guild guild = new Guild(tag, name,  false, loc,0,0);
        guild.addMember(leader, GuildRank.LEADER);
        guilds.put(guild.getTag(), guild);
        Cuboids.add(guild.getTag(), loc.getWorld(), loc.getBlockX(), loc.getBlockZ(), GuildSettings.GUILD_SIZE_START, CuboidType.GUILD);
        Rankings.register(guild.getTag());
        Users.get(leader).setGuild(guild);
        createRoomGuild(loc);
    }

    public void deleteGuild(String tag) {
        Guild g = getGuildByTag(tag);
        Set<UUID> uuids = g.getMembers().keySet();
        g.getCuboid().getLocation().subtract(0, 1, 0).getBlock().setType(Material.AIR);
        g.getCuboid().getLocation().subtract(0, 2, 0).getBlock().setType(Material.AIR);
        guilds.remove(g.getTag());
        guilds.values().stream().filter(guild -> guild.getAllyGuilds().contains(g.getTag())).forEach(guild -> guild.getAllyGuilds().remove(g.getTag()));
        Cuboids.remove(g.getTag());
        Rankings.unregister(g.getTag());
        for(UUID uuid:uuids){
            User user = Users.get(uuid);
            user.setGuild(null);
            user.updateTag();
        }
    }

    private void createRoomGuild(Location location) {
        Location c = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
        c.setY(c.getY() - 1);
        for (Location loc : SpaceUtil.getSquare(c, 4, 3)) {
            loc.getBlock().setType(Material.AIR);
        }
        for (Location loc : SpaceUtil.getSquare(c, 4)) {
            loc.getBlock().setType(Material.OBSIDIAN);
        }
        for (Location loc : SpaceUtil.getCorners(c, 4, 3)) {
            loc.getBlock().setType(Material.OBSIDIAN);
        }
        c.add(0, 4, 0);
        for (Location loc : SpaceUtil.getWalls(c, 4)) {
            loc.getBlock().setType(Material.OBSIDIAN);
        }
        c = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
        c.getBlock().setType(Material.DRAGON_EGG);
        c.setY(c.getY() - 1);
        c.getBlock().setType(Material.BEDROCK);

    }

    public Guild getGuildByTag(String tag) {
        return guilds.get(tag);
    }

    public Guild getGuildByName(String name) {
        return getGuilds().stream().filter(guild -> guild.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public boolean isGuildByTag(String tag) {
        return guilds.containsKey(tag);
    }

    public boolean isGuildByName(String name) {
        return getGuildByName(name) != null;
    }

    public Collection<Guild> getGuilds() {
        return guilds.values();
    }
}
