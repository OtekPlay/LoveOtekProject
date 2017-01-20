package pl.otekplay.loveotek.main;

import pl.otekplay.loveotek.basic.Combat;
import pl.otekplay.loveotek.managers.CombatManager;

import java.util.Collection;
import java.util.UUID;

public class Combats {


    public static void register(UUID uuid){
        manager().registerCombat(uuid);
    }
    public static void unregister(UUID uuid){
        manager().unregisterCombat(uuid);
    }
    public static Combat get(UUID uuid){
       return manager().getCombat(uuid);
    }
    public static Collection<Combat> all() {return manager().getCombats().values();}

    private static CombatManager manager() {
        return Core.getInstance().getCombatManager();
    }
}
