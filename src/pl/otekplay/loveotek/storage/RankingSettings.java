package pl.otekplay.loveotek.storage;

import java.util.Arrays;
import java.util.List;

public class RankingSettings {
    public static int RANKING_START_POINTS = 1000;
    public static int RANKING_START_KILLS = 0;
    public static int RANKING_START_DEATHS = 0;
    public static int RANKING_START_ASSISTS = 0;
    public static String MESSAGE_PART_KILLER = "&4Zabojca";
    public static String MESSAGE_PART_ASSIST = "&6Asystant";
    public static String MESSAGE_PART_DEATH = "&4Smierc";
    public static String MESSAGE_RANKING_HEADER_MENU = "&4Ranking";
    public static String MESSAGE_RANKING_HEADER_USER = "&4Gracz %name%";
    public static String MESSAGE_RANKING_HEADER_GUILD = "&4Gildia %tag%";
    public static String MESSAGE_FORMAT_KILLER = "&4Zabojca: %name%[+%points%] zadal: %damage%(%percetage%%) obrazen.";
    public static String MESSAGE_FORMAT_ASSIST = "&4Assysta: %name%[+%points%] zadal: %damage%(%percetage%%) obrazen.";
    public static String MESSAGE_FORMAT_DEATH = "&4Gracz %name%[%points%] otrzymal %damage% obrazen od:";
    public static List<String> MESSAGE_RANKING_LIST_USER = Arrays.asList(
            "&4Miejsce: %place%",
            "&4Punkty: %points%",
            "&4Zabojstwa: %kills%",
            "&4Smierci: %deaths%",
            "&4Asysty: %assists%"
    );
    public static List<String> MESSAGE_RANKING_LIST_GUILD = Arrays.asList(
            "&4Lider: %leader%",
            "&4Miejsce: %place%",
            "&4Punkty: %points%",
            "&4Zabojstwa: %kills%",
            "&4Smierci: %deaths%",
            "&4Asysty: %assists%"
    );
    public static List<String> MESSAGE_RANKING_GUILD = Arrays.asList(
            "&4Ranking gildi %tag%",
            "&4Punkty: %points%",
            "&4Zabicia: %kills%",
            "&4Zgony: %deaths%",
            "&4Asysty %assists%"
    );
    public static List<String> MESSAGE_RANKING_USER = Arrays.asList(
            "&4Ranking gracza %name%",
            "&4Punkty: %points%",
            "&4Zabicia: %kills%",
            "&4Zgony: %deaths%",
            "&4Asysty %assists%"
    );
    public static String MESSAGE_HISTORY_ITEM_HEADER = "&4Historia %name%";
    public static List<String> MESSAGE_HISTORY_ITEM_LORE = Arrays.asList(
            "&4Smierc: %death% (%minus%)",
            "&2Zabojca: %killer% (%plus%)",
            "&4Udzial: %type% (%get%)",
            "&6Obrazen: %damage%/%total% (%percetage%%)"
    );
}
