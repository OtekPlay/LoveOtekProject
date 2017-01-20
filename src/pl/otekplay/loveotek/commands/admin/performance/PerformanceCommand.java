package pl.otekplay.loveotek.commands.admin.performance;

import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;

public class PerformanceCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("performance", UserRank.HEADADMIN,"performance","wydajnosc","gc","mem","tps");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        World world = player.getWorld();
//        Replacer.build(GlobalSettings.MESSAGE_SERVER_PERFORMANCE_INFO)
//                .add("%mem%",);
    }
}
