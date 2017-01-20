package pl.otekplay.loveotek.storage;

import java.util.Arrays;
import java.util.List;

public class BackupSettings {
    public static String MESSAGE_BACKUP_SAVED_PLAYER = "&4Zapisales backup gracza %name%";
    public static String MESSAGE_BACKUP_MENU_HEADER = "&4Backupy gracza %name%";
    public static String MESSAGE_BACKUP_PLAYER_GOT = "&4Gracz %name% zostal przywrocony do %time% przez %admin%";
    public static String MESSAGE_BACKUP_ITEM_HEADER = "&4Backup nr %amount%";
    public static String MESSAGE_BACKUP_SAVE_GLOBAL = "&4Globalne zapisywanie itemow graczy...";
    public static List<String> MESSAGE_BACKUP_ITEM_INFO = Arrays.asList(
            "&4Typ: %type%",
            "&4Data: %time%",
            "&4Itemy: %contents%",
            "&4Armor: %armor%",
            "&4Ender: %enderchest%"
    );

}
