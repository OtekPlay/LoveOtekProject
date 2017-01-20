package pl.otekplay.loveotek.commands.admin.gamemode;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class GamemodeCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("gamemode", UserRank.HEADADMIN,"gamemode","gm");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        player.setGameMode(player.getGameMode() == GameMode.SURVIVAL ? GameMode.CREATIVE: GameMode.SURVIVAL);
        Replacer.build(GlobalSettings.MESSAGE_GAMEMODE_PLAYER_SET).add("%gamemode%",player.getGameMode().name()).send(player);
    }
}
