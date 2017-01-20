package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.otekplay.loveotek.storage.ChatSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class ChatUser {
    private final UUID uniqueID;
    private final List<UUID> ignoredUsers = new ArrayList<>();
    private boolean privateMessages = true;
    private long lastMessage = 0;


    public boolean canSend(){
        return System.currentTimeMillis() - lastMessage > ChatSettings.CHAT_COOLDOWN_TIME;
    }
}
