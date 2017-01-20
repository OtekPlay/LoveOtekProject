package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import pl.otekplay.loveotek.enums.GeneratorType;
import pl.otekplay.loveotek.main.Generators;

@RequiredArgsConstructor
@Getter
@Setter
public class Generator {
    private final Location location;
    private final GeneratorType type;
    private long destroyTime;

    public boolean isReadyForRepair() {
        return System.currentTimeMillis() - destroyTime > type.getRepairTime();
    }

    public void destroy() {
        setDestroyTime(System.currentTimeMillis());
        Generators.destroy(this);
    }

    public void repair() {
        getLocation().clone().add(0, 1, 0).getBlock().setType(type.getMaterial());
    }

    public void build() {
        getLocation().getBlock().setType(Material.ENDER_STONE);
        repair();
    }
}
