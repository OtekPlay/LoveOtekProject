package pl.otekplay.loveotek.commands.admin.chat.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.storage.ChatSettings;
import pl.otekplay.loveotek.utils.StringUtil;

public class CooldownChatArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if(StringUtil.isInteger(args[0])){
            player.sendMessage(ChatSettings.MESSAGE_CHAT_COOLDOWN_FORMAT);
            return;
        }
        int seconds = Integer.parseInt(args[0]);
        ChatSettings.CHAT_COOLDOWN_TIME = seconds*1000;
        Replacer.build(ChatSettings.MESSAGE_CHAT_COOLDOWN_SET).add("%seconds%",seconds).send(player);
    }
}
