package ninja.amp.ampmenus.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RankingItem extends MenuItem {
    public RankingItem(ItemStack icon) {
        super(icon.getItemMeta().getDisplayName(), icon, icon.getItemMeta().getLore().toArray(new String[]{}));
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        return getIcon();
    }
}
