package pl.otekplay.loveotek.commands.guild.normal.subs;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GuildSettings;

public class SetHomeArgCommand implements SubCommand {
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
        if (!guild.getLeaderUniqueID().equals(user.getUniqueID())) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_NEED_LEADER);
            return;
        }
        Location loc = player.getLocation();
        if(!guild.getCuboid().isInside(loc)){
            player.sendMessage(GuildSettings.MESSAGE_GUILD_SETHOME_MUST_INSIDE);
            return;
        }
        guild.setHome(loc);
        Replacer.build(GuildSettings.MESSAGE_GUILD_SETHOME_SET_NEW).add("%nick%",user.getName()).add("%x%",loc.getBlockX()+"").add("%z%",loc.getBlockZ()+"").send(guild);
    }
}
