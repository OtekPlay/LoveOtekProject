package pl.otekplay.loveotek.commands.player.spawn;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.CuboidType;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.storage.CuboidSettings;
import pl.otekplay.loveotek.utils.StringUtil;

public class SetSpawnCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("setspawn", UserRank.HEADADMIN, "setspawn [DISTANCE]");
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        int distance = 100;
        String arg = args[0];
        if (StringUtil.isInteger(arg)) {
            distance = Integer.parseInt(arg);
        }
        Location loc = player.getLocation();
        if (Cuboids.is(CuboidSettings.MESSAGE_CUBOID_SPAWN_NAME)) {
            Replacer.build(CuboidSettings.MESSAGE_CUBOID_SPAWN_EXIST).send(player);
            return;
        }
        Cuboids.add(CuboidSettings.MESSAGE_CUBOID_SPAWN_NAME, loc, distance, CuboidType.SPAWN);
        Replacer.build(CuboidSettings.MESSAGE_CUBOID_SPAWN_SET).add("%x%", loc.getBlockX()).add("%z%", loc.getBlockZ()).add("%distance%", distance).send(player);
    }
}
