package pl.otekplay.loveotek.commands.player.spawn;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.runnables.TeleportTask;

public class SpawnCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("spawn", UserRank.VIP, "spawn");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        TeleportTask.start(player, Cuboids.spawn());
    }
}
