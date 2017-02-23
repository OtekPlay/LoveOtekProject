package pl.otekplay.loveotek.commands.admin.cuboid.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.storage.CuboidSettings;

public class DeleteCuboidArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String name = args[0];
        if(!Cuboids.is(name)){
            Replacer.build(CuboidSettings.MESSAGE_CUBOID_NO_EXIST).add("%name%",name).send(player);
            return;
        }
        if(Cuboids.get(name).isGuildTerrain()){
            Replacer.build(CuboidSettings.MESSAGE_CUBOID_CANT_DELETE_GUILD_TERRAIN).send(player);
            return;
        }
        Cuboids.remove(name);
        Replacer.build(CuboidSettings.MESSAGE_CUBOID_INFO_DELETE).add("%name%",name).add("%nick%",player.getName()).broadcast(UserRank.ADMIN);
    }
}
