package pl.otekplay.loveotek.commands.admin.fly;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class FlyCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("fly", UserRank.HELPER, "fly", "lataj", "f");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (player.isFlying()) {
            player.sendMessage(GlobalSettings.MESSAGE_FLYING_DISABLED);
            player.setAllowFlight(false);
            player.setFlying(false);
            return;
        }
        player.sendMessage(GlobalSettings.MESSAGE_FLYING_ENABLED);
        player.setAllowFlight(true);
        player.setFlying(true);

    }
}
