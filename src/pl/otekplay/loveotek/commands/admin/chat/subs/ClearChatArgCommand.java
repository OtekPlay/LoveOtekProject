package pl.otekplay.loveotek.commands.admin.chat.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.storage.ChatSettings;

public class ClearChatArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        Replacer.build(new String[100]).broadcast();
        Replacer.build(ChatSettings.MESSAGE_CHAT_GLOBAL_CLEAR).add("%name%",player.getName()).broadcast();
    }
}
