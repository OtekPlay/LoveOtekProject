package pl.otekplay.loveotek.commands.admin.inventory;

import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.commands.admin.inventory.subs.OpenChestArgCommand;
import pl.otekplay.loveotek.commands.admin.inventory.subs.OpenInventoryArgCommand;
import pl.otekplay.loveotek.enums.UserRank;

public class OpenInventoryCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("otworz", UserRank.MODERATOR, "otworz [echest/inv] [NICK]", "open", "oi")
                .sub("echest", new OpenChestArgCommand())
                .sub("inv", new OpenInventoryArgCommand());
    }

    @Override
    public int minArgs() {
        return 1;
    }
}
