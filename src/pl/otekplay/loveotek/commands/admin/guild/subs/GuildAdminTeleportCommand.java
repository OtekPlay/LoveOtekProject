package pl.otekplay.loveotek.commands.admin.guild.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.storage.GuildSettings;
import pl.otekplay.loveotek.storage.TeleportSettings;

public class GuildAdminTeleportCommand implements SubCommand {
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
        player.teleport(guild.getHome());
        Replacer.build(TeleportSettings.MESSAGE_TELEPORT_SUCCESS).send(player);
    }
}
