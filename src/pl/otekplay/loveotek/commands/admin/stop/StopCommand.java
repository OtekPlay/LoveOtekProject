package pl.otekplay.loveotek.commands.admin.stop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class StopCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("stop", UserRank.HEADADMIN,"stop","wylacz");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        for(Player online: Bukkit.getOnlinePlayers()){
            online.kickPlayer(GlobalSettings.MESSAGE_SERVER_CLOSED_DEFAULT);
        }
        Bukkit.broadcastMessage(GlobalSettings.MESSAGE_SERVER_CLOSED_DEFAULT);
        Bukkit.shutdown();
    }
}
