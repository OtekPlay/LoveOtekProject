package pl.otekplay.loveotek.commands.player.guild.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.GuildRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.storage.GuildSettings;

public class DemoteArgCommand implements SubCommand {
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
        String name = args[0];
        if (!Users.is(name)) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%nick%",name).send(player);
            return;
        }
        User demote = Users.get(name);
        if(user.equals(demote)){
            player.sendMessage(GuildSettings.MESSAGE_GUILD_CANT_CHOOSE_YOURSELF);
            return;
        }
        if(!demote.hasGuild()){
            Replacer.build(GuildSettings.MESSAGE_GUILD_PLAYER_DONT_HAVE_GUILD).add("%nick%",demote.getName()).send(player);
            return;
        }
        if (!guild.isMember(demote.getUniqueID())) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_PLAYER_NO_SAME_GUILD).add("%nick%", demote.getName()).send(player);
            return;
        }
        GuildRank rank = guild.getGuildRank(demote.getUniqueID());
        if(rank == GuildRank.MEMBER){
            Replacer.build(GuildSettings.MESSAGE_GUILD_CANT_DEMOTE).add("%nick%",demote.getName()).send(player);
            return;
        }
        guild.addMember(demote.getUniqueID(),GuildRank.MEMBER);
        Replacer.build(GuildSettings.MESSAGE_GUILD_DEMOTE_COMPLETED).add("%nick%",demote.getName()).send(guild);
        demote.updateTag();
    }
}
