package pl.otekplay.loveotek.listeners.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.otekplay.loveotek.basic.Cuboid;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.CuboidSettings;

public class PlayerMoveListener implements Listener {


    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        User user = Users.get(p.getUniqueId());
        Location loc = event.getTo();
        if (!Cuboids.inside(loc)) {
            if (user.getTerrain().isEmpty()) {
                return;
            }
            Replacer.build(CuboidSettings.MESSAGE_PLAYER_MOVE_INFO_QUIT).add("%name%", user.getTerrain()).send(p);
            user.setTerrain("");
            return;
        }
        Cuboid cuboid = Cuboids.cub(loc);
        if (user.getTerrain().equals(cuboid.getKey())) {
            return;
        }
        user.setTerrain(cuboid.getKey());
        Replacer.build(CuboidSettings.MESSAGE_PLAYER_MOVE_INFO_JOIN).add("%name%", cuboid.getKey()).send(p);
        if (!cuboid.isGuildTerrain()) {
            return;
        }
        Guild guild = Guilds.tag(cuboid.getKey());
        if (guild.isMember(p.getUniqueId())) {
            return;
        }
        if (!guild.needInfo()) {
            return;
        }
        Replacer.build(CuboidSettings.MESSAGE_GUILD_ENEMY_MOVE_INFO_JOIN).send(guild);
        guild.setLastMoveInformation(System.currentTimeMillis());
    }

}


