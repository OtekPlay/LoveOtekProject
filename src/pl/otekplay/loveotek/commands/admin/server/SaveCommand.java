package pl.otekplay.loveotek.commands.admin.server;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class SaveCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("save", UserRank.HEADADMIN,"save","zapisz");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        long start = System.currentTimeMillis();
        Bukkit.savePlayers();
        Replacer.build(GlobalSettings.MESSAGE_SERVER_SAVE_PLAYERS).add("%players%", (int) (System.currentTimeMillis()-start)).send(player);
        start = System.currentTimeMillis();
        Bukkit.getWorlds().forEach(World::save);
        Replacer.build(GlobalSettings.MESSAGE_SERVER_SAVE_WORLD).add("%maps%", (int) (System.currentTimeMillis()-start)).send(player);

    }
}
