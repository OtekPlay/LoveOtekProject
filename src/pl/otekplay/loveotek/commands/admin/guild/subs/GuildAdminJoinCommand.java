package pl.otekplay.loveotek.commands.admin.guild.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.GuildRank;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.main.Rankings;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GuildSettings;

public class GuildAdminJoinCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String tag = args[0];
        if (!Guilds.isTag(tag)) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_NO_EXIST).add("%tag%", tag).send(player);
            return;
        }
        User user = Users.get(player.getUniqueId());
        Guild guild = Guilds.tag(tag);
        user.setGuild(guild);
        guild.addMember(player.getUniqueId(), GuildRank.MEMBER);
        user.updateTag();
        Replacer.build(GuildSettings.MESSAGE_GUILD_BROADCAST_JOIN).add("%tag%", guild.getTag()).add("%nick%", user.getName()).broadcast();
        Rankings.sortGuilds();

    }
}
