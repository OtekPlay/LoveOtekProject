package pl.otekplay.loveotek.storage;

import java.util.Arrays;
import java.util.List;

public class ChatSettings {
    public static long CHAT_COOLDOWN_TIME = 10000;
    public static List<String> MESSAGE_CHAT_BLOCKED_WORDS =
            Arrays.asList(
                    ".pl",
                    ".com",
                    "chuj",
                    "cipa",
                    "kurwa",
                    "spierdalaj",
                    "cwel",
                    "mc4u",
                    "craftcore",
                    "www"
            );

    public static String MESSAGE_CHAT_BLOCKED_MESSAGE = "&4Twoja wiadomosc zawiera nie dozwolone slowa!";
    public static String MESSAGE_CHAT_BLOCKED_TIME = "&4Pomiedzy twoimi wiadomosciami musi minac %time% sekund";
    public static String MESSAGE_CHAT_GUILD_FORMAT = "&4[Gildia] &7%PLAYER% --> &f%MESSAGE%";
    public static String MESSAGE_CHAT_GLOBAL_FORMAT = "%GROUP%&8[%POINTS%] &7%PLAYER% &f: %MESSAGE%";
    public static String MESSAGE_CHAT_DISABLED = "&4Czat na serwerze jest wylaczony!";

}
