package pl.otekplay.loveotek.commands.player.home;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Teleporter;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.runnables.TeleportTask;
import pl.otekplay.loveotek.storage.HomeSettings;

public class HomeCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("home", UserRank.PLAYER, "home [NAZWA]", "dom");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        User user = Users.get(player.getUniqueId());
        if(user.getHome() == null){
            player.sendMessage(HomeSettings.MESSAGE_HOME_NEED_SET);
            return;
        }
        if(!Teleporter.can(user,player.getLocation())){
            player.sendMessage(HomeSettings.MESSAGE_HOME_CANT_GUILD);
            return;
        }
        TeleportTask.start(player,user.getHome());
    }
}
