package pl.otekplay.loveotek.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum UserRank {
    PLAYER("Gracz", "", ChatColor.WHITE, 1),
    VIP("VIP", "", ChatColor.GOLD, 2),
    SUPERVIP("SVIP", "", ChatColor.GREEN, 3),
    YOUTUBER("YT", "", ChatColor.DARK_GREEN, 4),
    STREAMER("TW", "", ChatColor.DARK_GREEN, 5),
    PREMIUM("P", "[✔]", ChatColor.AQUA, 6),
    HELPER("Helper", "[✉]", ChatColor.BLUE, 7),
    MODERATOR("Mod", "[✉✉]", ChatColor.DARK_BLUE, 8),
    ADMIN("Admin", "[✉✉✉]", ChatColor.RED, 9),
    HEADADMIN("H@", "[♛]", ChatColor.DARK_RED, 10),
    DEVELOPER("DeV", ChatColor.BOLD + "[⚡]", ChatColor.YELLOW, 25);


    final String prefix;
    final String icon;
    final ChatColor color;
    final int priority;


    public boolean can(UserRank rank) {
        return getPriority() >= rank.getPriority();
    }

    public static UserRank getGroup(String key) {
        return Arrays.asList(values()).stream().filter(userRank -> userRank.name().equalsIgnoreCase(key)).findFirst().orElse(null);
    }

    public String getPrefix() {
        return getColor() + ((can(UserRank.MODERATOR)) ? ChatColor.BOLD + "" : "") + "[" + prefix + "]";
    }

    public String getIcon() {
        return " " + ChatColor.RED + icon;
    }

    public static boolean isGroup(String key) {
        return getGroup(key) != null;
    }

}
