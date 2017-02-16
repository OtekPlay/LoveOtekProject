package pl.otekplay.loveotek.commands.admin.guild.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.main.Rankings;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.storage.GuildSettings;

public class GuildAdminKickCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 2;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String tag = args[0];
        String name = args[1];
        if (!Guilds.isTag(tag)) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_NO_EXIST).add("%tag%", tag).send(player);
            return;
        }
        Guild guild = Guilds.tag(tag);
        if (!Users.is(name)) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%name%", name).send(player);
            return;
        }
        User user = Users.get(name);
        if (!user.hasGuild()) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_PLAYER_DONT_HAVE_GUILD).add("%nick%", user.getName()).send(player);
            return;
        }
        Guild userGuild = user.getGuild();
        if (!guild.equals(userGuild)) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_PLAYER_OTHER_GUILD).add("%nick%", user.getName()).send(player);
            return;
        }
        if (user.getUniqueID().equals(guild.getLeaderUniqueID())) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_CANT_KICK_LEADER).send(player);
            return;
        }
        guild.removeMember(user.getUniqueID());
        user.setGuild(null);
        user.updateTag();
        Rankings.sortGuilds();
        Replacer.build(GuildSettings.MESSAGE_GUILD_BROADCAST_KICKED).add("%nick%", user.getName()).add("%tag%", guild.getTag()).broadcast();

    }
}
