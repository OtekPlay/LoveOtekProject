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
    public static String MESSAGE_CHAT_MSG_GET = "&4Gracz %name% przyslal wiadomosc: %message%";
    public static String MESSAGE_CHAT_MSG_SEND = "&4Wyslales wiadomosc do %name%: %message%";
    public static String MESSAGE_CHAT_PRIVATE_DISABLED = "&4Ten gracz ma zablokowane prywatne wiadomosci.";
    public static String MESSAGE_CHAT_REPLY_NO_FIND = "&4Nikt ostatnio do Ciebie nie pisal.";
    public static String MESSAGE_CHAT_GLOBAL_ENABLE = "&4Czat zostal wlaczony!";
    public static String MESSAGE_CHAT_GLOBAL_DISABLE = "&4Czat zostal wylaczony!";
    public static String MESSAGE_CHAT_GLOBAL_CLEAR = "&4Czat zostal wyczyszczony przez %name%";
    public static String MESSAGE_CHAT_COOLDOWN_SET = "&4Ustawiles odstep pomiedzy wiadomosciami na %seconds% sekund.";
    public static String MESSAGE_CHAT_COOLDOWN_FORMAT = "&4Pierwszy argument musi byc liczba !";
    public static String MESSAGE_PRIVATE_MESSAGES_ENABLE = "&4Wlaczyles prywatne wiadomosci!";
    public static String MESSAGE_PRIVATE_MESSAGES_DISABLE = "&4Wylaczyles prywatne wiadomosci!";
}
