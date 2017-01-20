package pl.otekplay.loveotek.storage;

import java.util.Arrays;
import java.util.List;

public class GlobalSettings {
    public static int SERVER_MAP_BORDER = 2500;
    public static String MESSAGE_SERVER_CLOSED_DEFAULT = "&4Restartowanie serwera!";
    public static String MESSAGE_ADMIN_CANT_ATTACK = "&4Administracja nie moze atakowac innych graczy.";
    public static String MESSAGE_ADMIN_GOD_MODE = "&4Gracze nie moga atakowac administracji";
    public static String MESSAGE_FLYING_ENABLED = "&4Wlaczyles latanie!";
    public static String MESSAGE_FLYING_DISABLED = "&4Wylaczyles latanie!";
    public static String MESSAGE_VANISH_ENABLED = "&4Wlaczyles niewidocznosc!";
    public static String MESSAGE_VANISH_DISABLED = "&4Wylaczyles niewidocznosc!";
    public static String MESSAGE_NO_PERMISSIONS = "&2Nie masz praw do tego, potrzebujesz rangi &4%group%";
    public static String MESSAGE_COMMAND_USAGE = "&2Poprawne uzycie komendy: /%usage%";
    public static String MESSAGE_PLAYER_NO_EXIST = "&4Gracz %name% nie istnieje!";
    public static String MESSAGE_PLAYER_IS_OFFLINE = "&4Gracz %name% jest offline.";
    public static String MESSAGE_ADMIN_CLEAR_INVENTORY = "&4Wyczysciles swoje inventory!";
    public static String MESSAGE_ADMIN_OPEN_INVENTORY = "&4Administrator %admin% otworzyl %type% gracza %name%";
    public static String MESSAGE_ADMIN_NAME_INVENTORY = "Ekwipunek";
    public static String MESSAGE_ADMIN_NAME_ENDERCHEST = "Enderchest";
    public static String MESSAGE_GROUP_NO_EXIST = "&2Grupa %name% nie istnieje!";
    public static String MESSAGE_GROUP_SET_USER = "&2Grupa %name% zostala nadana dla gracza %nick%";
    public static String MESSAGE_GROUP_GIVE_TOO_HIGH = "&2Nie mozesz nadac grup wiekszych albo rownych sobie!";
    public static String MESSAGE_GROUP_TAKE_TOO_HIGH = "&2Nie mozesz zmniejszyc rangi kogos wyzszego albo rownego!";
    public static String MESSAGE_GAMEMODE_PLAYER_SET = "&4Ustawiles tryb %gamemode%";
    public static String MESSAGE_SERVER_TIMINGS_PASTE = "&4Timingsy znajdziesz tutaj: %link%";
    public static String MESSAGE_SERVER_TIMINGS_FAILED = "&4Wystapil problem z timingsami.";
    public static List<String> MESSAGE_SERVER_PERFORMANCE_INFO = Arrays.asList(
            "&4Informacje o serwerze:",
            "TPS: %tps%",
            "Pamiec wolna: %freememory%",
            "Pamiec zajeta: %reservememory%",
            "Pamiec cala: %totalmemory%",
            "Chunki: %chunks%",
            "Entites: %entities%",
            "Gracze: %players%",
            "Watki: %threads%",
            "Rdzenie: %cores%",
            ""
    );


}
