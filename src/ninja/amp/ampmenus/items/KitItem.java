package ninja.amp.ampmenus.items;

import ninja.amp.ampmenus.events.ItemClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.basic.Kit;
import pl.otekplay.loveotek.main.Kits;
import pl.otekplay.loveotek.storage.KitSettings;

public class KitItem extends NamedMenuItem {
    public KitItem(ItemStack icon) {
        super(icon);
    }


    @Override
    public void onItemClick(ItemClickEvent event) {
        Player p = event.getPlayer();
        Kit kit = Kits.icon(getIcon());
        if(event.getType() == ClickType.RIGHT){
            p.closeInventory();
            kit.getMenu().open(p);
            return;
        }
        super.onItemClick(event);
        if(!kit.hasPermissions(p.getUniqueId())){
            p.sendMessage(KitSettings.MESSAGE_PLAYER_KIT_PERMISSIONS);
            return;
        }
        if(!kit.canTake(p.getUniqueId())){
            p.sendMessage(KitSettings.MESSAGE_PLAYER_KIT_TIME);
            return;
        }
        kit.takeKit(p.getUniqueId());
        kit.give(p);



    }
}
