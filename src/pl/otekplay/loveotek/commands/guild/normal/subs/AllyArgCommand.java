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

public class AllyArgCommand implements SubCommand {
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
        Guild ally = Guilds.tag(tag);
        if (guild.isAllied(ally.getTag())) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_ALREADY_ALLIED).add("%ally%", ally.getTag());
            return;
        }
        guild.addAlly(ally.getTag());
        Replacer.build(GuildSettings.MESSAGE_GUILD_YOUR_TAGGED_ALLY).add("%tag%",ally.getTag()).send(guild);
        TagUtil.updateBoard(guild);
    }
}
