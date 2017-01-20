package pl.otekplay.loveotek.commands.guild.normal.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Rankings;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GuildSettings;
import pl.otekplay.loveotek.utils.TagUtil;

public class LeaveArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        User user = Users.get(player.getUniqueId());
        if (!user.hasGuild()) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_YOU_NEED);
            return;
        }
        Guild guild = user.getGuild();
        if (guild.getLeaderUniqueID().equals(user.getUniqueID())) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_CANT_LEAVE_LEADER);
            return;
        }
        guild.removeMember(player.getUniqueId());
        user.setGuild(null);
        Replacer.build(GuildSettings.MESSAGE_GUILD_BROADCAST_LEAVE).add("%nick%", user.getName()).add("%tag%", guild.getTag()).broadcast();
        TagUtil.updateBoard(player);
        Rankings.sortGuilds();
    }
}
