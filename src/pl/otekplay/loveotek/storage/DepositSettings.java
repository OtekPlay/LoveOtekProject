package pl.otekplay.loveotek.storage;

import java.util.Arrays;
import java.util.List;

public class DepositSettings {
    public static int DEPOSIT_MAX_PEARL = 10;
    public static int DEPOSIT_MAX_KOX = 3;
    public static int DEPOSIT_MAX_GOLDEN = 12;
    public static String MESSAGE_DEPOSIT_MENU_NAME = "&4Depozyt";
    public static String MESSAGE_DEPOSIT_TAKE_ITEMS = "&4Miales za duzo itemow, zostaly one przeniesione do /depozyt";
    public static String MESSAGE_DEPOSIT_ITEM_NAME_PEARL = "&4Depozyt perel";
    public static String MESSAGE_DEPOSIT_ITEM_NAME_KOX = "&4Depozyt koxow";
    public static String MESSAGE_DEPOSIT_ITEM_NAME_GOLDEN = "&4Depozyt jablek";
    public static String MESSAGE_DEPOSIT_CANT_TAKE_FULL = "&4Posiadasz maksymalna ilosc w eq!";
    public static String MESSAGE_DEPOSIT_CANT_TAKE_AMOUNT = "&4Nie mozesz wybrac itemu z pustego schowka!";
    public static List<String> MESSAGE_DEPOSIT_ITEM_LORE = Arrays.asList("&4Posiadasz: %amount%", "Limit %limit%");
}
