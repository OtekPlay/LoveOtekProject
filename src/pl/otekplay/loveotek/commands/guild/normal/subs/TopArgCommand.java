package pl.otekplay.loveotek.commands.guild.normal.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.main.Rankings;

public class TopArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        Rankings.gTop(player);
    }
}
