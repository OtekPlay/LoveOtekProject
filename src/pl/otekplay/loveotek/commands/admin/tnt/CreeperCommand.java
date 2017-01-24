package pl.otekplay.loveotek.commands.admin.tnt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.api.entities.CustomCreeper;
import pl.otekplay.loveotek.api.entities.EntityTypes;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;

public class CreeperCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("creeper", UserRank.HEADADMIN, "creeper", "crep");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        Player p = Bukkit.getPlayer("DragereK");
        EntityTypes.spawnEntity(new CustomCreeper(player.getWorld(),p.getLocation()),player.getLocation());
    }
}
