package pl.otekplay.loveotek.commands.admin.cuboid.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.storage.CuboidSettings;

public class ListCuboidArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        Cuboids.all().forEach(cuboid -> Replacer.build(CuboidSettings.MESSAGE_CUBOID_INFO_FORMAT).add("%name%", cuboid.getKey()).add("%size%", cuboid.getSize()).add("%type%", cuboid.getType().name()).add("%x%", cuboid.getLocation().getBlockX()).add("%z%", cuboid.getLocation().getBlockZ()).send(player));
    }
}
