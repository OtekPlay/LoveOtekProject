package pl.otekplay.loveotek.commands.player.guild.subs;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GuildSettings;

public class CreateArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 2;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String tag = args[0].toUpperCase();
        String name = args[1];
        if (tag.length() != 4) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_TAG_FORMAT);
            return;
        }
        if (name.length() < 10 && name.length() > 20) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_NAME_FORMAT);
            return;
        }
        if (Guilds.isTag(tag)) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_TAG_RESERVED).add("%name%", name).send(player);
            return;
        }
        if (Guilds.isName(name)) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_NAME_RESERVED).add("%name%", name).send(player);
            return;
        }
        User user = Users.get(player.getUniqueId());
        if(user.hasGuild()){
            player.sendMessage(GuildSettings.MESSAGE_GUILD_YOU_HAVE);
            return;
        }
        Location loc = player.getLocation();
        if (!Cuboids.can(loc)) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_NO_ENOUGH_DISTANCE);
            return;
        }
        Guilds.create(tag, name, player.getUniqueId(), loc);
        Replacer.build(GuildSettings.MESSAGE_GUILD_BROADCAST_CREATE).add("%tag%", tag).add("%nick%", player.getName()).broadcast();
        user.updateTag();
    }

}