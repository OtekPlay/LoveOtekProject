package pl.otekplay.loveotek.commands.admin.timings;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.spigotmc.CustomTimingsHandler;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.threads.PasteThread;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TimingsCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("timings", UserRank.HEADADMIN, "timings", "timingi");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        long sampleTime = System.nanoTime() - org.bukkit.command.defaults.TimingsCommand.timingStart;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PrintStream fileTimings = new PrintStream(bout);
        CustomTimingsHandler.printTimings(fileTimings);
        fileTimings.println("Sample time " + sampleTime + " (" + sampleTime / 1.0E9 + "s)");
        fileTimings.println("<spigotConfig>");
        fileTimings.println(Bukkit.spigot().getConfig().saveToString());
        fileTimings.println("</spigotConfig>");
        PasteThread thread = new PasteThread(bout);
        thread.start();
    }
}
