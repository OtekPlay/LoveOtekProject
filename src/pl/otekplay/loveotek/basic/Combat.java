package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.main.Rankings;
import pl.otekplay.loveotek.storage.CombatSettings;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Combat {
    @Getter
    private final UUID uniqueID;
    private final List<Attacker> attackers = new CopyOnWriteArrayList<>();

    public Player getPlayer(){
        return Bukkit.getPlayer(uniqueID);
    }
    public void attack(UUID uuid, int damage) {
        Attacker attacker = null;
        if (!hasValidAttackers()) {
            clear();
        } else {
            attacker = attackers.stream().filter(att -> att.getUniqueID().equals(uuid)).findFirst().orElse(null);
        }
        if (attacker == null) {
            attacker = new Attacker(uuid);
            attackers.add(attacker);
        }
        attacker.attack(damage);
    }


    public void dead() {
        kill();
        clear();
    }

    private void kill() {
        Rankings.calculate(uniqueID,attackers.stream().filter(attacker -> isValidAttacker(attacker.getLastAttack())).collect(Collectors.toList()));
    }

    private void clear()
    {
        attackers.clear();
    }

    public boolean hasValidAttackers(){
        for(Attacker attacker:attackers){
            if(isValidAttacker(attacker.getLastAttack())){
                return true;
            }
        }
        return false;
    }
    public long getTimeLastValidAttacker(){
        long time = 0;
        for(Attacker attacker:attackers){
            if(time > attacker.getLastAttack()){
                continue;
            }
            time = attacker.getLastAttack();
        }
        return time;
    }

    private static boolean isValidAttacker(long time) {
        return (System.currentTimeMillis() - time) < CombatSettings.COMBAT_VALID_ATTACKER_TIME;
    }
}
