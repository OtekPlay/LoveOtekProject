package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
    private long helpopLastMessage = 0;
    private UUID lastMessager;


    public boolean canSendHelpop(){
        return System.currentTimeMillis() > helpopLastMessage;
    }
    public boolean canSendMessage(){
        return System.currentTimeMillis() > lastMessage;
    }
}
