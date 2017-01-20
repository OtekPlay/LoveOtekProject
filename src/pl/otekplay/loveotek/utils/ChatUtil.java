package pl.otekplay.loveotek.utils;

import net.md_5.bungee.api.ChatColor;

public class ChatUtil {


    public static String fixColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }



}
