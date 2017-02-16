package ninja.amp.ampmenus.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StableMenuItem extends MenuItem {

    public StableMenuItem(ItemStack icon) {
        super(null, icon, new String[0]);
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        return getIcon();
    }
}
