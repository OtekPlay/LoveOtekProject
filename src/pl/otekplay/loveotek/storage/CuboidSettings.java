package pl.otekplay.loveotek.storage;

import java.util.Arrays;
import java.util.List;

public class CuboidSettings {
    public static String MESSAGE_CUBOID_SPAWN_NAME = "SPAWN";
    public static String MESSAGE_CUBOID_SPAWN_EXIST = "&4Nie mozesz ustawic cuboida spawna, gdy juz jest ustawiony!";
    public static String MESSAGE_CUBOID_SPAWN_SET ="&4Ustawiles cuboid spawna na %x% %z% wielkosc: %distance%";
    public static String MESSAGE_BUILD_SAFE_ZONE = "&4Nie mozesz tutaj niszczyc!";
    public static String MESSAGE_BUILD_GUILD_TERRAIN = "&4Ten teren nalezy do gildii %name% nie mozesz tutaj budowac!";
    public static String MESSAGE_GUILD_ENEMY_MOVE_INFO_JOIN = "&4Intruz na terytorium gildii!";
    public static String MESSAGE_PLAYER_MOVE_INFO_JOIN = "&4Wszedles na teren %name%";
    public static String MESSAGE_PLAYER_MOVE_INFO_QUIT = "&4Wyszedles z terenu %name%";
    public static String MESSAGE_CUBOID_NO_EXIST = "&4Cuboid o nazwie %name% nie istnieje.";
    public static String MESSAGE_CUBOID_NAME_RESERVED = "&4Cuboid o nazwie %name% jest zajety.";
    public static String MESSAGE_CUBOID_PROPERTY_NAME = "&4Nazwa cuboida musi sie skladac od 8 znakow do 16";
    public static String MESSAGE_CUBOID_INFO_CREATE = "&4Admin %nick% stworzyl cuboid o nazwie %name%";
    public static String MESSAGE_CUBOID_CANT_DELETE_GUILD_TERRAIN = "&4Nie mozna usunac cuboidu gildii!";
    public static String MESSAGE_CUBOID_INFO_DELETE = "&4Admin %nick% usunal cuboid o nazwie %name%";
    public static String MESSAGE_CUBOID_INFO_FORMAT = "&4Cuboid[%type%] Nazwa: %name% Wielkosc: %size% (X:%x%, Z:%z%)";
    public static int CUBOID_NAME_SIZE_MIN = 8;
    public static int CUBOID_NAME_SIZE_MAX = 16;
    public static List<String> MESSAGE_CUBOID_LIST_COMMANDS = Arrays.asList(
            "&4Cuboid - lista komend",
            "/cub stworz [NAME] [SIZE]",
            "/cub usun [NAME]",
            "/cub list");


}
