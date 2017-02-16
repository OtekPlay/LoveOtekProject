package pl.otekplay.loveotek.commands.player.helpop;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.ChatUser;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Chat;
import pl.otekplay.loveotek.storage.ChatSettings;

public class HelpopCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("helpop", UserRank.PLAYER,"helpop [MESSAGE]","hop");
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        ChatUser chat = Chat.get(player.getUniqueId());
        if(!chat.canSendHelpop()){
            player.sendMessage(ChatSettings.MESSAGE_HELPOP_MESSAGE_WAIT);
            return;
        }
        StringBuilder builder = new StringBuilder();
        for(String s:args){
            builder.append(s);
        }
        chat.setHelpopLastMessage(System.currentTimeMillis()+ChatSettings.HELPOP_COOLDOWN_TIME);
        player.sendMessage(ChatSettings.MESSAGE_HELPOP_MESSAGE_SEND);
        Replacer.build(ChatSettings.MESSAGE_HELPOP_MESSAGE_GET).add("%name%",player.getName()).add("%message%",builder.toString()).broadcast(UserRank.HELPER);
    }
}
