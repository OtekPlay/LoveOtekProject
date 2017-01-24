package pl.otekplay.loveotek.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.basic.Cuboid;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.CuboidSettings;

public class LocationUtil {


    public static boolean canBuild(Player p, Location loc) {
        if (Users.get(p.getUniqueId()).hasPermissions(UserRank.HEADADMIN)) {
            return false;
        }
        if (!Cuboids.inside(loc)) {
            return false;
        }
        Cuboid cuboid = Cuboids.cub(loc);
        if (!cuboid.isGuildTerrain()) {
            p.sendMessage(CuboidSettings.MESSAGE_BUILD_SAFE_ZONE);
            return true;
        }
        Guild guild = Guilds.tag(cuboid.getKey());
        if (!guild.isMember(p.getUniqueId())) {
            Replacer.build(CuboidSettings.MESSAGE_BUILD_GUILD_TERRAIN).add("%name%", guild.getTag()).send(p);
            return true;
        }
        return false;
    }

    public static boolean canUseBucket(Player p, Location location) {
        if (Users.get(p.getUniqueId()).hasPermissions(UserRank.HEADADMIN)) {
            return true;
        }
        if (!Cuboids.inside(location)) {
            return false;
        }
        Cuboid cuboid = Cuboids.cub(location);
        if (!cuboid.isGuildTerrain()) {
            return false;
        }
        Guild guild = Guilds.tag(cuboid.getKey());
        if(guild.isMember(p.getUniqueId())){
            return true;
        }
        return false;
    }


}
