package pl.otekplay.loveotek.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;
import pl.otekplay.loveotek.enums.CuboidType;

@AllArgsConstructor
@Getter
@Setter
public class Cuboid {
    private final String key;
    private final Location location;
    private final CuboidType type;
    private int size;

    public World getWorld() {
        return getLocation().getWorld();
    }

    public int getCenterX() {
        return getLocation().getBlockX();
    }

    public int getCenterZ() {
        return getLocation().getBlockZ();
    }

    public boolean isInside(int x, int z) {
        return Math.abs(getCenterX() - x) <= size && Math.abs(getCenterZ() - z) <= size;
    }

    public boolean isInside(Location loc) {
        return isInside(loc.getBlockX(), loc.getBlockZ());
    }

    public boolean isGuildTerrain() {
        return type == CuboidType.GUILD;
    }
}
