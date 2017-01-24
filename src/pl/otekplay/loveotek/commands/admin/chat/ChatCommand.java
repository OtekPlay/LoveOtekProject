package pl.otekplay.loveotek.commands.admin.chat;

import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.commands.admin.chat.subs.ClearChatArgCommand;
import pl.otekplay.loveotek.commands.admin.chat.subs.CooldownChatArgCommand;
import pl.otekplay.loveotek.commands.admin.chat.subs.DisableChatArgCommand;
import pl.otekplay.loveotek.commands.admin.chat.subs.EnableChatArgCommand;
import pl.otekplay.loveotek.enums.UserRank;

public class ChatCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("chat", UserRank.MODERATOR,"chat [clear/enable/disable/cooldown] (seconds)")
                .sub("clear",new ClearChatArgCommand())
                .sub("cooldown",new CooldownChatArgCommand())
                .sub("disable",new DisableChatArgCommand())
                .sub("enable",new EnableChatArgCommand());
    }

    @Override
    public int minArgs() {
        return 0;
    }
}
