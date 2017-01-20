package pl.otekplay.loveotek.storage;

import java.util.Arrays;
import java.util.List;

public class DropSettings {


    public static String MESSAGE_DROP_ITEM_FIND = "&4Znalazles %name%";
    public static int DROP_PLAYER_STONE_EXP = 1;
    public static int DROP_USER_STONE_EXP = 1;
    public static int DROP_LEVEL_FIRST_LEVEL = 50;
    public static String DROP_MENU_ITEM_ENABLED = "&2Wlaczony";
    public static String DROP_MENU_ITEM_DISABLED = "&4Wylaczony";
    public static String DROP_MENU_ITEM_HEADER = "&4Menu Dropu";
    public static String DROP_MENU_ITEM_NAME = "&4%name%";
    public static List<String> DROP_MENU_ITEM_LORE = Arrays.asList(
            "Szansa: %chance%",
            "Wysokosc: %height%",
            "PlayerExp: %playerexp%",
            "DropExp: %userexp%",
            "Tryb: %type%"
    );
    public static List<Integer> DROP_MENU_BLOCKED_BLOCKS = Arrays.asList(56,129);
}
