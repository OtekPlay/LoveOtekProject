package pl.otekplay.loveotek.api.entities;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_7_R4.Navigation;
import net.minecraft.server.v1_7_R4.PathfinderGoal;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class PathfinderGoalWalkForward extends PathfinderGoal {
    private final long alive;
    private final double speed;
    private final CustomCreeper creeper;
    private final Navigation navigation;

    public PathfinderGoalWalkForward(CustomCreeper entity, double speed) {
        this.creeper = entity;
        this.navigation = this.creeper.getNavigation();
        this.speed = speed;
        this.alive = System.currentTimeMillis() + 5000;
        this.creeper.setCustomNameVisible(true);
    }

    public boolean a() {
        return true;
    }

    public boolean isAlive() {
        return alive > System.currentTimeMillis();
    }

    public int getSeconds() {
        return (int) ((alive - System.currentTimeMillis()) / 1000) + 1;
    }

    @Override
    public boolean b() {
        System.out.println("b");
        return a();
    }

    @Override
    public void c() {
        System.out.println("c");
    }

    @Override
    public void d() {
        System.out.println("d");
    }

    @Override
    public void e() {
        this.creeper.setCustomName(ChatColor.RED + (getSeconds() + ""));
        if (isAlive()) {
            Entity ent = creeper.getBukkitEntity();
            Location to = ent.getLocation().clone().add(ent.getLocation().getDirection());
            this.navigation.a(this.navigation.a(to.getBlockX(), to.getBlockY(), to.getBlockZ()), speed);
            return;
        }
        creeper.explode();
    }

}