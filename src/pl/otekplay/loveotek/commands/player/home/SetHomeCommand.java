package pl.otekplay.loveotek.commands.player.home;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Teleporter;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.HomeSettings;

public class SetHomeCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("sethome", UserRank.PLAYER,"sethome","shome");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        User user = Users.get(player.getUniqueId());
        Location loc = player.getLocation();
        if(!Teleporter.can(user,loc)){
            player.sendMessage(HomeSettings.MESSAGE_SETHOME_CUBOID);
            return;
        }
        user.setHome(player.getLocation());
        Replacer.build(HomeSettings.MESSAGE_SETHOME_SET).add("%x%",loc.getBlockX()).add("%y%",loc.getBlockY()).add("%z%",loc.getBlockZ()).send(player);
    }
}
