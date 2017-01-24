package pl.otekplay.loveotek.commands.player.chat;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.ChatUser;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Chat;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.ChatSettings;
import pl.otekplay.loveotek.storage.GlobalSettings;

import java.util.UUID;

public class ReplyCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("reply", UserRank.PLAYER,"r","odpowiedz","odp");
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        ChatUser chat = Chat.get(player.getUniqueId());
        if(chat.getLastMessager() == null){
            player.sendMessage(ChatSettings.MESSAGE_CHAT_REPLY_NO_FIND);
            return;
        }
        UUID uuid = chat.getUniqueID();
        User send = Users.get(uuid);
        if (!send.isOnline()) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_IS_OFFLINE).add("%name%", send.getName()).send(player);
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            builder.append(args[i]);
        }
        String message = builder.toString();
        Replacer.build(ChatSettings.MESSAGE_CHAT_MSG_GET).add("%name%", send.getName()).add("%message%", message).send(send.getPlayer());
        Replacer.build(ChatSettings.MESSAGE_CHAT_MSG_SEND).add("%name%", player.getName()).add("%message%", message).send(player);
        Chat.get(send.getUniqueID()).setLastMessager(player.getUniqueId());
    }
}
