package pl.otekplay.loveotek.commands.admin.vanish;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Vanish;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.utils.TagUtil;

public class VanishCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("vanish", UserRank.MODERATOR, "vanish", "v");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (!Vanish.has(player)) {
            player.sendMessage(GlobalSettings.MESSAGE_VANISH_ENABLED);
            Vanish.hide(player);
            TagUtil.updateBoard(player);
            return;
        }
        player.sendMessage(GlobalSettings.MESSAGE_VANISH_DISABLED);
        Vanish.show(player);
        TagUtil.updateBoard(player);
    }
}
