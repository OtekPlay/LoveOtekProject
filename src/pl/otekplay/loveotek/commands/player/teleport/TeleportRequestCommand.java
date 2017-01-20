package pl.otekplay.loveotek.commands.player.teleport;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.storage.TeleportSettings;

public class TeleportRequestCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("tpa", UserRank.PLAYER, "tpa [NICK]", "tparequest");
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String name = args[0];
        if (!Users.is(name)) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%name%", name).send(player);
            return;
        }
        User request = Users.get(name);
        if (!request.isOnline()) {
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_IS_OFFLINE).add("%name%", request.getName()).send(player);
            return;
        }
        if (request.getTeleportRequest() == null) {
            sendRequest(player, request);
            return;
        }
        if (request.getTeleportRequest().equals(player.getUniqueId())) {
            Replacer.build(TeleportSettings.MESSAGE_TELEPORT_REQUEST_ALREADY).add("%name%", request.getName()).send(player);
            return;
        }
        sendRequest(player, request);
    }

    private void sendRequest(Player player, User request) {
        request.setTeleportRequest(player.getUniqueId());
        Replacer.build(TeleportSettings.MESSAGE_TELEPORT_REQUEST_SEND).add("%name%", request.getName()).send(player);
        Replacer.build(TeleportSettings.MESSAGE_TELEPORT_REQUEST_GET).add("%name%", player.getName()).send(request);
    }
}
