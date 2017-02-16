package pl.otekplay.loveotek.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.List;

public class ChatUtil {


    public static String fixColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static List<String> fixColors(List<String> messages){
        for(int i=0;i<messages.size();i++){
            messages.set(i,ChatUtil.fixColors(messages.get(i)));
        }
        return messages;
    }



}
