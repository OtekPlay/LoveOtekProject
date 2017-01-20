package pl.otekplay.loveotek.storage;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class CombatSettings {
    public static long COMBAT_VALID_ATTACKER_TIME = 25000;
    public static int COMBAT_VALID_MIN_PERCETAGE = 20;
    public static String MESSAGE_BAR_ANTYRELOG = "&4[ANTYRELOG]";
    public static List<Integer> HARDER_BLOCKS = Arrays.asList(49,130,116, Material.WATER.getId(),Material.STATIONARY_WATER.getId(),Material.LAVA.getId(),Material.STATIONARY_LAVA.getId());
    public static double HARDER_BLOCK_EXPLOSION_CHANCE = 40;
    public static int TNT_EXPLODE_RADIUS = 4;
    public static boolean TNT_ENABLED = true;
    public static int TIME_HOUR_TNT_START = 12;
    public static int TIME_HOUR_TNT_END = 24;
    public static String MESSAGE_BRODCAST_TNT_ENABLED = "&4TnT zostalo wlaczone!";
    public static String MESSAGE_BROADCAST_TNT_DISABLED = "&4TnT zostalo wylaczone!";

}
