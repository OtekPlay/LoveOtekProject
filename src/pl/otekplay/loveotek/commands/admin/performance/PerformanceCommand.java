package pl.otekplay.loveotek.commands.admin.performance;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.utils.StringUtil;

import java.text.NumberFormat;

public class PerformanceCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("performance", UserRank.HEADADMIN, "performance", "wydajnosc", "gc", "mem", "tps");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        World world = player.getWorld();
        Runtime runtime = Runtime.getRuntime();
        NumberFormat format = NumberFormat.getInstance();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        String tps = "";
        for (double doub : Bukkit.getServer().spigot().getTPS()) {
            tps = tps + StringUtil.round(doub, 1) + ", ";
        }
        Replacer.build(GlobalSettings.MESSAGE_SERVER_PERFORMANCE_INFO)
                .add("%tps%", tps)
                .add("%freememory%", format.format(freeMemory / 1024))
                .add("%reservememory%", format.format(allocatedMemory / 1024))
                .add("%totalmemory%", format.format(maxMemory / 1024))
                .add("%chunks%", world.getLoadedChunks().length)
                .add("%entities%", world.getEntities().size())
                .add("%liveentities%", world.getLivingEntities().size())
                .add("%players%", world.getPlayers().size())
                .add("%threads%", Thread.activeCount())
                .add("%cores%", Runtime.getRuntime().availableProcessors() + "")
                .send(player);
    }
}
