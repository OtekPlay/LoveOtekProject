package pl.otekplay.loveotek.runnables;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import pl.otekplay.loveotek.basic.Generator;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.main.Generators;

import java.util.Collection;

public class GeneratorTask extends BukkitRunnable {


    @Override
    public void run() {
        Collection<Generator> generators = Generators.destroyed();
        generators.forEach(generator -> Generators.repair(generator));
        Bukkit.getScheduler().runTask(Core.getInstance(), () -> generators.forEach(Generator::repair));
    }
}
