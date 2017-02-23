package pl.otekplay.loveotek.commands.admin.cuboid.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.CuboidType;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.storage.CuboidSettings;

public class CreateCuboidArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 2;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String name = args[0];
        int size = name.length();
        if(size > CuboidSettings.CUBOID_NAME_SIZE_MAX || size < CuboidSettings.CUBOID_NAME_SIZE_MIN){
            Replacer.build(CuboidSettings.MESSAGE_CUBOID_PROPERTY_NAME).send(player);
            return;
        }
        if(Cuboids.is(name)){
            Replacer.build(CuboidSettings.MESSAGE_CUBOID_NAME_RESERVED).add("%name%",name).send(player);
            return;
        }
        int distance = Integer.parseInt(args[1]);
        Cuboids.add(name, player.getLocation(),distance, CuboidType.CUSTOM);
        Replacer.build(CuboidSettings.MESSAGE_CUBOID_INFO_CREATE).add("%name%",name).add("%nick%",player.getName()).broadcast(UserRank.ADMIN);

    }
}
