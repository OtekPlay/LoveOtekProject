package pl.otekplay.loveotek.commands.admin.server;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class VersionCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("version", UserRank.PLAYER, "version", "pl", "wersja", "bukkit", "spigot", "ver");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        player.sendMessage(GlobalSettings.MESSAGE_SERVER_BY_LOVEOTEK);
    }
}
