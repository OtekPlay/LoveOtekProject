package pl.otekplay.loveotek.commands.admin.tnt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.storage.CombatSettings;

public class TnTCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("tnt", UserRank.HEADADMIN, "tnt");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        CombatSettings.TNT_ENABLED = !CombatSettings.TNT_ENABLED;
        Bukkit.broadcastMessage(CombatSettings.TNT_ENABLED ? CombatSettings.MESSAGE_BRODCAST_TNT_ENABLED : CombatSettings.MESSAGE_BROADCAST_TNT_DISABLED);
    }
}
