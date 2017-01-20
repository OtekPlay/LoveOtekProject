package pl.otekplay.loveotek.commands.guild.normal.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.GuildRank;
import pl.otekplay.loveotek.main.Rankings;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.storage.GuildSettings;
import pl.otekplay.loveotek.utils.TagUtil;

public class RemoveArgCommand implements SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        String name = args[0];
        User user = Users.get(player.getUniqueId());
        if (!user.hasGuild()) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_YOU_NEED);
            return;
        }
        Guild guild = user.getGuild();
        GuildRank rank = guild.getGuildRank(user.getUniqueID());
        if (!Users.is(name)) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%nick%",name).send(player);
            return;
        }
        User remove = Users.get(name);
        if(!remove.hasGuild()){
            Replacer.build(GuildSettings.MESSAGE_GUILD_PLAYER_DONT_HAVE_GUILD).add("%nick%",remove.getName()).send(player);
            return;
        }
        if (!guild.isMember(remove.getUniqueID())) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_PLAYER_NO_SAME_GUILD).add("%nick%", remove.getName()).send(player);
            return;
        }
        GuildRank removeRank = guild.getGuildRank(remove.getUniqueID());
        if(removeRank.getPriority() >=rank.getPriority()){
            player.sendMessage(GuildSettings.MESSAGE_GUILD_CANT_KICK_HIGHER);
            return;
        }
        guild.removeMember(remove.getUniqueID());
        remove.setGuild(null);
        Replacer.build(GuildSettings.MESSAGE_GUILD_BROADCAST_KICKED).add("%nick%",remove.getName()).add("%tag%",guild.getTag()).broadcast();
        if(remove.isOnline()) {
            TagUtil.updateBoard(remove.getPlayer());
        }
        Rankings.sortGuilds();
    }

    @Override
    public int minArgs() {
        return 1;
    }
}
