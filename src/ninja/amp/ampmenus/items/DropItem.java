package ninja.amp.ampmenus.items;

import ninja.amp.ampmenus.events.ItemClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.basic.Drop;
import pl.otekplay.loveotek.basic.DropUser;
import pl.otekplay.loveotek.main.Drops;

public class DropItem extends StaticMenuItem {
    private final Drop drop;
    public DropItem(ItemStack icon,Drop drop) {
        super(icon);
        this.drop = drop;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        Player p = event.getPlayer();
        DropUser user = Drops.get(p.getUniqueId());
        if(user.isDisabledDrop(drop)){
            user.enableDrop(drop);
        }else{
            user.disableDrop(drop);
        }
        p.closeInventory();
        Drops.menu(p);
    }
}
