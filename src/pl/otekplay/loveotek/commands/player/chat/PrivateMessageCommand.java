package pl.otekplay.loveotek.commands.player.chat;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.ChatUser;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Chat;
import pl.otekplay.loveotek.storage.ChatSettings;

public class PrivateMessageCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("antypw", UserRank.PLAYER,"wyjebane","pw");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        ChatUser user = Chat.get(player.getUniqueId());
        user.setPrivateMessages(!user.isPrivateMessages());
        Replacer.build(user.isPrivateMessages() ? ChatSettings.MESSAGE_PRIVATE_MESSAGES_ENABLE:ChatSettings.MESSAGE_PRIVATE_MESSAGES_DISABLE).send(player);
    }
}
