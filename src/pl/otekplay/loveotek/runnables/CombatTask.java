package pl.otekplay.loveotek.runnables;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.bar.BarAPI;
import pl.otekplay.loveotek.basic.Combat;
import pl.otekplay.loveotek.main.Combats;
import pl.otekplay.loveotek.storage.CombatSettings;

public class CombatTask implements Runnable {
    @Override
    public void run() {
        for (Combat combat : Combats.all()) {
            Player p = combat.getPlayer();
            if (!combat.hasValidAttackers()) {
                continue;
            }
            long time = combat.getTimeLastValidAttacker();
            long fight = System.currentTimeMillis() - time;
            float percetage = 100*fight/ CombatSettings.COMBAT_VALID_ATTACKER_TIME;
            BarAPI.setBar(p, CombatSettings.MESSAGE_BAR_ANTYRELOG,100-percetage);
        }
    }
}
