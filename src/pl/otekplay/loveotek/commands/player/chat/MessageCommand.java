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

public class MessageCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("msg", UserRank.PLAYER, "msg [NICK] [WIADOMOSC]", "message", "priv", "whisper", "w");
    }

    @Override
    public int minArgs() {
        return 2;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String name = args[0];
        if (!Users.is(name)) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%name%", name).send(player);
            return;
        }
        User user = Users.get(name);
        if (!user.isOnline()) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_IS_OFFLINE).add("%name%", user.getName()).send(player);
            return;
        }
        ChatUser chat = Chat.get(user.getUniqueID());
        if (!chat.isPrivateMessages()) {
            player.sendMessage(ChatSettings.MESSAGE_CHAT_PRIVATE_DISABLED);
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            builder.append(args[i]);
        }
        String message = builder.toString();
        Replacer.build(ChatSettings.MESSAGE_CHAT_MSG_GET).add("%name%", player.getName()).add("%message%", message).send(user.getPlayer());
        Replacer.build(ChatSettings.MESSAGE_CHAT_MSG_SEND).add("%name%", user.getName()).add("%message%", message).send(player);
        chat.setLastMessager(player.getUniqueId());
    }
}
