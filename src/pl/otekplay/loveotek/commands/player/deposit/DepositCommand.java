package pl.otekplay.loveotek.commands.player.deposit;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Deposits;

public class DepositCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("deposit", UserRank.PLAYER,"deposit","depozyt","depo","schowek");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        Deposits.open(player);
    }
}
