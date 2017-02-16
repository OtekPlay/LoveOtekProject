package pl.otekplay.loveotek.storage;

import java.util.Arrays;
import java.util.List;

public class GuildSettings {
    public static int GUILD_SIZE_START = 25;
    public static int GUILD_SIZE_UPGRADE = 5;
    public static int GUILD_SIZE_MAX = 50;
    public static int GUILD_UPGRADE_ITEM_ID = 133;
    public static int GUILD_AMOUNT_PER_UPGRADE = 10;
    public static long GUILD_MOVE_INFO_COOLDOWN = 30000;
    public static String MESSAGE_GUILD_TAG_FORMAT = "&4Tag musi skladac sie z 4 znakow!";
    public static String MESSAGE_GUILD_NAME_FORMAT = "&4Nazwa musi zawierac od 10 do 20 znakow!";
    public static String MESSAGE_GUILD_NO_ENOUGH_DISTANCE = "&4Jestes za blisko innego cuboida, musisz odejsc dalej!";
    public static String MESSAGE_GUILD_TAG_RESERVED = "&4Tag %name% jest zarezerwowany!";
    public static String MESSAGE_GUILD_NAME_RESERVED = "&4Nazwa %name% jest zarezerwowana!";
    public static String MESSAGE_GUILD_BROADCAST_CREATE = "&2Gildia %tag% zostala zalozona przez %nick%";
    public static String MESSAGE_GUILD_NEED_LEADER = "&4Potrzebujesz byc liderem gildii!";
    public static String MESSAGE_GUILD_YOU_NEED = "&4Musisz posiadac gildie!";
    public static String MESSAGE_GUILD_YOU_HAVE = "&4Posiadasz juz gildie!";
    public static String MESSAGE_GUILD_TOO_LOW_PERMISSIONS = "&4Twoja ranga w gildii jest zbyt niska!";
    public static String MESSAGE_GUILD_PLAYER_NO_SAME_GUILD = "&4Gracz %nick% nie jest w twojej gildi.";
    public static String MESSAGE_GUILD_UPGRADE_NEED_AMOUNT = "&4Aby ulepszyc gildie potrzebujesz %amount%";
    public static String MESSAGE_GUILD_GOT_INVITE = "&4Dostales zaproszenie do gildi %name%";
    public static String MESSAGE_GUILD_INVITE_PLAYER = "&4Gracz %nick% zaprosil do gildi %invite%";
    public static String MESSAGE_GUILD_ALREADY_INVITED = "&4Gracz %nick% jest juz zaproszony do gildi!";
    public static String MESSAGE_GUILD_NO_INVITED = "&4Gracz %nick% nie jest zaproszony do gildi!";
    public static String MESSAGE_GUILD_INVITED_MAX = "&4Maksymalnie %max% graczy moze byc zaproszonych!";
    public static String MESSAGE_GUILD_INVITE_HAS_GUILD = "&4Gracz %nick% ma juz gildie, nie mozesz go zaprosic!";
    public static String MESSAGE_GUILD_NO_EXIST = "&4Gildia z tagiem %tag% nie istnieje.";
    public static String MESSAGE_GUILD_PLAYER_DONT_HAVE_GUILD = "&4Gracz %nick% nie ma gildii!";
    public static String MESSAGE_GUILD_CANT_KICK_HIGHER = "&4Nie mozesz wyrzucic graczy z wyzsza ranga!";
    public static String MESSAGE_GUILD_BROADCAST_KICKED = "&4Gracz %nick% zostal wyrzucony z gildii %tag%";
    public static String MESSAGE_GUILD_SETHOME_MUST_INSIDE = "&4Dom gildi musi byc ustawiaony na terenie gildii!";
    public static String MESSAGE_GUILD_ALREADY_ALLIED = "&4Twoja gildia juz oznaczyla sojusz z gildia %ally%";
    public static String MESSAGE_GUILD_BROADCAST_LEAVE = "&4Gracz %nick% opuscil gildie %tag%";
    public static String MESSAGE_GUILD_BROADCAST_CLOSED = "&4Gracz %tag% zostala zamknieta!";
    public static String MESSAGE_GUILD_NO_INVITED_YOU = "&4Nie posiadasz zaproszenia od gildi %tag%";
    public static String MESSAGE_GUILD_HAVE_MAX_MEMBERS = "&4Gildia %tag% ma maksymalna ilosc czlonkow w gildi!";
    public static String MESSAGE_GUILD_BROADCAST_JOIN = "&4Gracz %nick% dolaczyl do gildii %tag%";
    public static String MESSAGE_GUILD_CANT_LEAVE_LEADER = "&4Nie mozesz opuscic gildi bedac liderem!";
    public static String MESSAGE_GUILD_CANT_KICK_LEADER  = "&4Nie mozna wyrzucic lidera gildii.";
    public static String MESSAGE_GUILD_CANT_PROMOTE = "&4Nie mozesz awansowac gracza %nick%";
    public static String MESSAGE_GUILD_CANT_DEMOTE = "&4Nie mozesz degradowac gracza %nick%.";
    public static String MESSAGE_GUILD_CANT_CHOOSE_YOURSELF = "&4Nie mozesz wybrac siebie!";
    public static String MESSAGE_GUILD_PROMOTE_COMPLETED = "&4Gracz %nick% zostal awansowany w gildi!";
    public static String MESSAGE_GUILD_BROADCAST_NEW_LEADER = "&4Lider gildi %tag% przekazal lidera dla %nick%";
    public static String MESSAGE_GUILD_DEMOTE_COMPLETED = "&4Gracz %nick% zostal zdegradowany w gildi!";
    public static String MESSAGE_GUILD_PVP_HAS_BEEN_ENABLED = "&4PvP w gildi zostalo wlaczone!";
    public static String MESSAGE_GUILD_PVP_HAS_BEEN_DISABLED = "&4PvP w gildi zostalo wylaczone!";
    public static String MESSAGE_GUILD_INVITE_CANCELLED = "&4Zaproszenie dla gracza %nick% zostalo anulowane.";
    public static String MESSAGE_GUILD_CANCEL_THE_INVITE = "&4Zaproszenie od gildi %tag% zostalo wycofane.";
    public static String MESSAGE_GUILD_SETHOME_SET_NEW = "&4Dom gildii zostal ustawiony przez %nick% na X: %x% Z: %z%";
    public static String MESSAGE_GUILD_YOUR_TAGGED_ALLY = "&4Twoja gildia oznaczyla gildie %tag% jako sojusznicza!";
    public static String MESSAGE_GUILD_YOUR_TAGGED_WAR = "&4Twoja gildia oznaczyla gildie %tag% jako wroga!";
    public static String MESSAGE_GUILD_ALLY_NO_TAGGED = "&4Twoja gildia nie oznaczyla gildi %tag%";
    public static String MESSAGE_GUILD_SIZE_UPGRADE_MAX = "&4Twoja gildia ma maksymalny poziom wielkosci!";
    public static String MESSAGE_GUILD_BROADCAST_UPGPRADE = "&4Gildia %tag% zostala powiekszona przez %nick%";
    public static String MESSAGE_GUILD_PLAYER_OTHER_GUILD = "&4Gracz %nick% jest w innej gildii.";
    public static String MESSAGE_GUILD_PLAYER_ALREADY_LEADER = "&4Gracz %nick% jest juz liderem tej gildii!";
    public static int GUILD_INVITE_MAX = 3;
    public static int GUILD_MEMBERS_MAX = 10;
    public static List<String> MESSAGE_GUILD_INFO = Arrays.asList(
            "&4Informacje o gildi %tag%",
            "&4Tag: %tag%",
            "&4Nazwa: %name%",
            "&4Lider: %leader%",
            "&4Czlonkowie: %members%/%max%",
            "&4Ally: %ally%",
            "&4Wielkosc cuba: %size%",
            "&4Cordy X: %x% Z: %z%"

    );
    public static List<String> MESSAGE_GUILD_LIST_COMMANDS = Arrays.asList(
            "&4Gildie - lista komend",
            "/g zaloz [TAG] [NAZWA]",
            "/g dodaj [NICK]",
            "/g wyrzuc [NICK]",
            "/g sojusz [TAG]",
            "/g zamknij",
            "/g degraduj [NICK]",
            "/g awansuj [NICK]",
            "/g lider [NICK]",
            "/g wyjdz",
            "/g info [TAG]",
            "/g pvp",
            "/g rank [TAG]",
            "/g dom",
            "/g ustawdom",
            "/g anuluj [NICK]",
            "/g powieksz",
            "/g dolacz [TAG]");


}
