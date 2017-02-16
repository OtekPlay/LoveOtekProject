package pl.otekplay.loveotek.commands.player.guild.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.storage.GuildSettings;

public class LeaderArgCommand implements SubCommand {
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
        String name = args[0];
        if (!Users.is(name)) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%nick%",name).send(player);
            return;
        }
        User newLeader = Users.get(name);
        if(user.equals(newLeader)){
            player.sendMessage(GuildSettings.MESSAGE_GUILD_CANT_CHOOSE_YOURSELF);
            return;
        }
        if(!newLeader.hasGuild()){
            Replacer.build(GuildSettings.MESSAGE_GUILD_PLAYER_DONT_HAVE_GUILD).add("%nick%",newLeader.getName()).send(player);
            return;
        }
        if (!guild.isMember(newLeader.getUniqueID())) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_PLAYER_NO_SAME_GUILD).add("%nick%", newLeader.getName()).send(player);
            return;
        }
        guild.setLeader(user.getUniqueID());
        Replacer.build(GuildSettings.MESSAGE_GUILD_BROADCAST_NEW_LEADER).add("%tag%",guild.getTag()).add("%nick%",newLeader.getName()).send(guild);
    }
}
