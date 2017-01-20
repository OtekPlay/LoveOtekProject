package pl.otekplay.loveotek.commands.player.teleport;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.runnables.TeleportTask;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.storage.TeleportSettings;

import java.util.UUID;

public class TeleportAcceptCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("tpaccept", UserRank.PLAYER,"tpaccept","tpacc");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        User user = Users.get(player.getUniqueId());
        if(user.getTeleportRequest() == null){
            Replacer.build(TeleportSettings.MESSAGE_TELEPORT_ACCEPT_NO_REQUEST).add("%name%",user.getName()).send(player);
            return;
        }
        UUID uuid = user.getTeleportRequest();
        User accept = Users.get(uuid);
        if(!accept.isOnline()){
            Replacer.build(GlobalSettings.MESSAGE_PLAYER_IS_OFFLINE).add("%name%", accept.getName()).send(player);
            return;
        }
        Player acceptPlayer = accept.getPlayer();
        Replacer.build(TeleportSettings.MESSAGE_TELEPORT_ACCEPT_FROM).add("%name%",accept.getName()).send(player);
        Replacer.build(TeleportSettings.MESSAGE_TELEPORT_ACCEPT_YOU).add("%name%",player.getName()).send(acceptPlayer);
        user.setTeleportRequest(null);
        TeleportTask.start(acceptPlayer,player.getLocation());
    }
}
