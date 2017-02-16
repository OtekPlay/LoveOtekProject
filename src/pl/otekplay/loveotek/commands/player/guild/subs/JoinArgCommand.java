package pl.otekplay.loveotek.commands.player.guild.subs;

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

public class JoinArgCommand implements SubCommand {
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
        if (user.hasGuild()) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_YOU_HAVE);
            return;
        }
        Guild guild = Guilds.tag(tag);
        if(!guild.hasInvite(user.getUniqueID())){
            Replacer.build(GuildSettings.MESSAGE_GUILD_NO_INVITED_YOU).add("%tag%",guild.getTag()).send(player);
            return;
        }
        if(guild.getMembers().size() >= GuildSettings.GUILD_SIZE_MAX){
            Replacer.build(GuildSettings.MESSAGE_GUILD_HAVE_MAX_MEMBERS).add("%tag%",guild.getTag()).send(player);
            return;
        }
        guild.removeInvite(user.getUniqueID());
        guild.addMember(user.getUniqueID(), GuildRank.MEMBER);
        user.setGuild(guild);
        Replacer.build(GuildSettings.MESSAGE_GUILD_BROADCAST_JOIN).add("%tag%",guild.getTag()).add("%nick%",user.getName()).broadcast();
       user.updateTag();
        Rankings.sortGuilds();
    }
}
