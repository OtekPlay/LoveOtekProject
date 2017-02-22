package pl.otekplay.loveotek.listeners.player;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.otekplay.loveotek.basic.Cuboid;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.commands.player.guild.subs.InfoArgCommand;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.main.Teleporter;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.CuboidSettings;
import pl.otekplay.loveotek.utils.InventoryUtil;

public class PlayerInteractListener implements Listener {


    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        User user = Users.get(p.getUniqueId());
        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) {
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (user.hasPermissions(UserRank.MODERATOR)) {
                if (block.getType() == Material.CHEST) {
                    InventoryUtil.silentOpen(p, block);
                    event.setCancelled(true);
                    return;
                }
            }
        }
        if (block.getType() == Material.DRAGON_EGG) {
            Cuboid cuboid = Cuboids.cub(block.getLocation());
            if (cuboid != null) {
                if (cuboid.isGuildTerrain()) {
                    InfoArgCommand.showInfoAboutGuild(p, Guilds.tag(cuboid.getKey()));
                    event.setCancelled(true);
                    return;
                }
            }
        }
        if (Cuboids.inside(block.getX(), block.getZ())) {
            Cuboid cuboid = Cuboids.cub(block.getLocation());
            if (block.getType() == Material.STONE_BUTTON || block.getType() == Material.WOOD_BUTTON) {
                if (!cuboid.getKey().equalsIgnoreCase(CuboidSettings.MESSAGE_CUBOID_SPAWN_NAME)) {
                    return;
                }
                Teleporter.random(p);
                return;
            }
        }

    }
}