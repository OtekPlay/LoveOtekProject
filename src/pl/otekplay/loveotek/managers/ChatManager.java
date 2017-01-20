package pl.otekplay.loveotek.managers;

import lombok.Getter;
import lombok.Setter;
import pl.otekplay.loveotek.basic.ChatUser;
import pl.otekplay.loveotek.storage.ChatSettings;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ChatManager {
    @Getter
    @Setter
    private boolean chatMode = true;
    private final Map<UUID, ChatUser> chatUsers = new ConcurrentHashMap<>();

    public void registerChatUser(UUID uuid) {
        chatUsers.put(uuid, new ChatUser(uuid));
    }

    public void unregisterChatUser(UUID uuid) {
        chatUsers.remove(uuid);
    }

    public ChatUser getChatUser(UUID uuid) {
        return chatUsers.get(uuid);
    }
    public boolean isValidMessage(String message){
        for(String string: ChatSettings.MESSAGE_CHAT_BLOCKED_WORDS){
            if(message.contains(string)){
                return false;
            }
        }
        return true;
    }


}
