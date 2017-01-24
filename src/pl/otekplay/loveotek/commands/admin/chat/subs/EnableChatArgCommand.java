package pl.otekplay.loveotek.commands.admin.chat.subs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.main.Chat;
import pl.otekplay.loveotek.storage.ChatSettings;

public class EnableChatArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        Chat.chat(true);
        Bukkit.broadcastMessage(ChatSettings.MESSAGE_CHAT_GLOBAL_ENABLE);
    }
}
