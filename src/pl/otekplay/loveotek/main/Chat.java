package pl.otekplay.loveotek.main;

import pl.otekplay.loveotek.basic.ChatUser;
import pl.otekplay.loveotek.managers.ChatManager;

import java.util.UUID;

public class Chat {


    public static void register(UUID uuid) {
        manager().registerChatUser(uuid);
    }

    public static void unregister(UUID uuid) {
        manager().unregisterChatUser(uuid);
    }

    public static ChatUser get(UUID uuid) {
        return manager().getChatUser(uuid);
    }

    public static boolean valid(String message) {
        return manager().isValidMessage(message);
    }

    public static void chat(boolean chat) {
        manager().setChatMode(chat);
    }

    public static boolean mode() {
        return manager().isChatMode();
    }

    private static ChatManager manager() {
        return Core.getInstance().getChatManager();
    }
}
