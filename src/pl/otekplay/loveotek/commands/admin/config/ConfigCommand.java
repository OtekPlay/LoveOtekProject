package pl.otekplay.loveotek.commands.admin.config;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.utils.ConfigUtil;

public class ConfigCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("config", UserRank.HEADADMIN, "config [NAZWA/WSZYSTKIE]", "cfg");
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String name = args[0];
        if (name.equalsIgnoreCase("all") || name.equalsIgnoreCase("wszystkie")) {
            Core.getInstance().initSettings();
            Replacer.build(GlobalSettings.MESSAGE_CONFIG_INFO_ALL).send(player);
            return;
        }
        try {
            Class cl = Class.forName("pl.otekplay.loveotek.storage." + name + "Settings");
            ConfigUtil.loadSettings(cl);
            Replacer.build(GlobalSettings.MESSAGE_CONFIG_INFO_RELOADED).add("%name%", name).send(player);
            return;
        } catch (ClassNotFoundException ignored) {

        }
        Replacer.build(GlobalSettings.MESSAGE_CONFIG_NO_EXIST).add("%name%", name).send(player);
    }
}
