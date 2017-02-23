package pl.otekplay.loveotek.commands.admin.cuboid;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.commands.admin.cuboid.subs.CreateCuboidArgCommand;
import pl.otekplay.loveotek.commands.admin.cuboid.subs.DeleteCuboidArgCommand;
import pl.otekplay.loveotek.commands.admin.cuboid.subs.ListCuboidArgCommand;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.storage.CuboidSettings;

public class CuboidCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("cub", UserRank.HEADADMIN,"cub","cuboid")
                .sub("stworz",new CreateCuboidArgCommand())
                .sub("usun",new DeleteCuboidArgCommand())
                .sub("list",new ListCuboidArgCommand());
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        Replacer.build(CuboidSettings.MESSAGE_CUBOID_LIST_COMMANDS.toArray(new String[]{})).send(player);

    }
}
