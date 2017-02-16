package pl.otekplay.loveotek.commands.admin.guild.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.storage.GuildSettings;

public class GuildAdminDeleteCommand implements SubCommand {
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
        Guild guild = Guilds.tag(tag);
        Replacer.build(GuildSettings.MESSAGE_GUILD_BROADCAST_CLOSED).add("%tag%",guild.getName()).broadcast();
        Guilds.destroy(guild.getTag());
    }
}
