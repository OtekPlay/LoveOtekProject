package pl.otekplay.loveotek.managers;

import lombok.Getter;
import pl.otekplay.loveotek.basic.Combat;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CombatManager {
    @Getter
    private final Map<UUID,Combat> combats = new ConcurrentHashMap<>();

    public void registerCombat(UUID uuid){
        combats.put(uuid,new Combat(uuid));
    }
    public void unregisterCombat(UUID uuid){
        combats.remove(uuid);
    }
    public Combat getCombat(UUID uuid){
        return combats.get(uuid);
    }
}
