package pl.otekplay.loveotek.main;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.basic.Deposit;
import pl.otekplay.loveotek.managers.DepositManager;

import java.util.UUID;

public class Deposits {

    private static DepositManager manager() {
        return Core.getInstance().getDepositManager();
    }

    public static void check(Player p) {
        manager().checkPlayer(p);
    }

    public static void register(UUID uuid) {
        manager().registerDeposit(uuid);
    }

    public static Deposit get(UUID uuid) {
        return manager().getDeposit(uuid);
    }

    public static void open(Player p) {
        manager().getMenu(p.getUniqueId()).open(p);
    }
}
