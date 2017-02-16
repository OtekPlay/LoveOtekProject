package pl.otekplay.loveotek.commands.admin.guild.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.storage.GuildSettings;

public class GuildAdminInviteCommand implements SubCommand {
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
        if (!user.isOnline()) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_IS_OFFLINE).add("%name%", user.getName()).send(player);
            return;
        }
        if (guild.hasInvite(user.getUniqueID())) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_ALREADY_INVITED).add("%nick%", user.getName()).send(player);
            return;
        }
        if (user.hasGuild()) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_INVITE_HAS_GUILD).add("%nick%", user.getName()).send(player);
            return;
        }
        guild.addInvite(user.getUniqueID());
        Replacer.build(GuildSettings.MESSAGE_GUILD_GOT_INVITE).add("%name%", guild.getTag()).send(user);
        Replacer.build(GuildSettings.MESSAGE_GUILD_INVITE_PLAYER).add("%invite%", user.getName()).add("%nick%", player.getName()).send(guild);
    }
}
