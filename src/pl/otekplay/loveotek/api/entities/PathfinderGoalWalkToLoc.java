package pl.otekplay.loveotek.api.entities;

import net.minecraft.server.v1_7_R4.EntityInsentient;
import net.minecraft.server.v1_7_R4.Navigation;
import net.minecraft.server.v1_7_R4.PathEntity;
import net.minecraft.server.v1_7_R4.PathfinderGoal;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class PathfinderGoalWalkToLoc extends PathfinderGoal {
    private double speed;

    private EntityInsentient entity;

    private Location loc;

    private Navigation navigation;

    public PathfinderGoalWalkToLoc(EntityInsentient entity, Location loc, double speed) {
        this.entity = entity;
        this.loc = loc;
        this.navigation = this.entity.getNavigation();
        this.speed = speed;
    }

    public boolean a() {
        Location location = new Location(entity.world.getWorld(),entity.locX,entity.locY,entity.locZ);
        if(location == loc){
            this.entity.world.createExplosion(entity,entity.locX,entity.locY,entity.locZ, 3,true, true);
            this.entity.die();
        }
        loc = location;
        Vector vector = location.getDirection().normalize().multiply(1.0);
        Location newLocation = location.add(vector);
        PathEntity pathEntity = this.navigation.a(newLocation.getBlockX(), newLocation.getBlockY(), newLocation.getBlockZ());
        this.navigation.a(pathEntity, speed);
        return true;
    }
}