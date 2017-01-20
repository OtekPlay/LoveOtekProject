package pl.otekplay.loveotek.commands.admin.teleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.storage.TeleportSettings;
import pl.otekplay.loveotek.utils.StringUtil;

public class CordsCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("cords", UserRank.HELPER, "cords [X] [Y] [Z]", "c");
    }

    @Override
    public int minArgs() {
        return 2;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        int x = 0;
        int y = 0;
        int z = 0;
        if (args.length == 3) {
            if (StringUtil.isInteger(args[0])) {
                x = Integer.parseInt(args[0]);
            }
            if (StringUtil.isInteger(args[1])) {
                y = Integer.parseInt(args[1]);
            }
            if (StringUtil.isInteger(args[2])) {
                z = Integer.parseInt(args[2]);
            }
        } else {
            if (StringUtil.isInteger(args[0])) {
                x = Integer.parseInt(args[0]);
            }
            if (StringUtil.isInteger(args[1])) {
                z = Integer.parseInt(args[1]);
            }
        }
        if (y == 0) {
            y = player.getWorld().getHighestBlockYAt(x, z);
        }
        Location location = new Location(player.getWorld(), x, y, z);
        player.teleport(location);
        Replacer.build(TeleportSettings.MESSAGE_TP_CORDS).add("%x%", x).add("%y%", y).add("%z%", z).send(player);
    }
}
