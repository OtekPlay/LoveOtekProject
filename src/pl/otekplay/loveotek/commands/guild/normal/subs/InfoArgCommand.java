package pl.otekplay.loveotek.commands.guild.normal.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GuildSettings;

public class InfoArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String tag = args[0];
        if(!Guilds.isTag(tag)){
            Replacer.build(GuildSettings.MESSAGE_GUILD_NO_EXIST).add("%tag%",tag).send(player);
            return;
        }
        Guild guild = Guilds.tag(tag);
        Replacer.build(GuildSettings.MESSAGE_GUILD_INFO.toArray(new String[GuildSettings.MESSAGE_GUILD_INFO.size()]))
                .add("%tag%",guild.getTag())
                .add("%name%",guild.getName())
                .add("%members%",guild.getMembers().size()+"")
                .add("%invites%",guild.getInvites().size()+"")
                .add("%ally%",guild.getAllyGuilds().size()+"")
                .add("%max%", GuildSettings.GUILD_MEMBERS_MAX+"")
                .add("%leader%", Users.get(guild.getLeaderUniqueID()).getName())
                .add("%size%",guild.getCuboid().getSize()+"")
                .add("%x%",guild.getCuboid().getCenterX()+"")
                .add("%z%",guild.getCuboid().getCenterZ()+"").send(player);


    }
}
