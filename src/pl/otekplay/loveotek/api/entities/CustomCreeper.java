package pl.otekplay.loveotek.api.entities;

import net.minecraft.server.v1_7_R4.Entity;
import net.minecraft.server.v1_7_R4.EntityCreeper;
import net.minecraft.server.v1_7_R4.PathfinderGoalSelector;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.event.entity.ExplosionPrimeEvent;

import java.util.List;

public class CustomCreeper extends EntityCreeper {
    public CustomCreeper(World world) {
        super(((CraftWorld) world).getHandle());
        this.setPowered(true);
        List goalB = (List) EntityTypes.getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
        goalB.clear();
        List goalC = (List) EntityTypes.getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
        goalC.clear();
        List targetB = (List) EntityTypes.getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
        targetB.clear();
        List targetC = (List) EntityTypes.getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
        targetC.clear();
        this.goalSelector.a(1, new PathfinderGoalWalkForward(this, 1.0));
    }

    @Override
    public void collide(Entity entity) {
        return;
    }

    protected void explode() {
        ExplosionPrimeEvent event = new ExplosionPrimeEvent(this.getBukkitEntity(), 6.0F, false);
        this.world.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            this.world.createExplosion(this, this.locX, this.locY, this.locZ, event.getRadius(), event.getFire(), true);
            this.die();
        }
    }
}

