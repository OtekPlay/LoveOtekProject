package pl.otekplay.loveotek.api.entities;

import net.minecraft.server.v1_7_R4.EntityCreeper;
import net.minecraft.server.v1_7_R4.PathfinderGoalSelector;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;

import java.util.List;

public class CustomCreeper extends EntityCreeper {
    public CustomCreeper(World world, Location location) {
        super(((CraftWorld)world).getHandle());
        this.setPowered(true);
        List goalB = (List) EntityTypes.getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
        List goalC = (List)EntityTypes.getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
        List targetB = (List)EntityTypes.getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
        List targetC = (List)EntityTypes.getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
        this.goalSelector.a(1,new PathfinderGoalWalkToLoc(this,location,1.0));
        this.goalSelector.a(2,new PathfinderGoalWalkToLoc(this,location,1.0));
    }
}
