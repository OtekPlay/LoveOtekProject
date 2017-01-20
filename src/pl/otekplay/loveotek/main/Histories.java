package pl.otekplay.loveotek.main;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.basic.History;
import pl.otekplay.loveotek.basic.Participant;
import pl.otekplay.loveotek.managers.HistoryManager;

import java.util.List;
import java.util.UUID;

public class Histories {

    public static void menu(UUID uuid, Player player) {
        manager().generateHistoryFightMenu(uuid).open(player);
    }

    private static HistoryManager manager() {
        return Core.getInstance().getInstance().getHistoryManager();
    }

    public static History register(List<Participant> pants) {
        return manager().registerHistory(pants);
    }
}
