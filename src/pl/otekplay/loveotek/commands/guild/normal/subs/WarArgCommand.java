package pl.otekplay.loveotek.commands.guild.normal.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GuildSettings;
import pl.otekplay.loveotek.utils.TagUtil;

public class WarArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        User user = Users.get(player.getUniqueId());
        if (!user.hasGuild()) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_YOU_NEED);
            return;
        }
        Guild guild = user.getGuild();
        if (!guild.getLeaderUniqueID().equals(user.getUniqueID())) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_NEED_LEADER);
            return;
        }
        String tag = args[0];
        if (!Guilds.isTag(tag)) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_NO_EXIST).add("%tag%", tag).send(player);
            return;
        }
        Guild war = Guilds.tag(tag);
        if (!guild.isAllied(war.getTag())) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_ALLY_NO_TAGGED).add("%tag%", war.getTag());
            return;
        }
        guild.removeAlly(war.getTag());
        Replacer.build(GuildSettings.MESSAGE_GUILD_YOUR_TAGGED_WAR).add("%tag%",war.getTag()).send(guild);
        TagUtil.updateBoard(war);
    }
}