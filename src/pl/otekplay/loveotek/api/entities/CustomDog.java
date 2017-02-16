package pl.otekplay.loveotek.api.entities;

import net.minecraft.server.v1_7_R4.EntityWolf;
import net.minecraft.server.v1_7_R4.PathfinderGoalSelector;
import net.minecraft.server.v1_7_R4.World;

import java.util.List;

public class CustomDog extends EntityWolf {
    public CustomDog(World world) {
        super(world);
        List goalB = (List) EntityTypes.getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
        goalB.clear();
        List goalC = (List) EntityTypes.getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
        goalC.clear();
        List targetB = (List) EntityTypes.getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
        targetB.clear();
        List targetC = (List) EntityTypes.getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
        targetC.clear();
        this.bp.setSitting(true);
    }
}
