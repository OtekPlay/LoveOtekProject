package ninja.amp.ampmenus.items;

import ninja.amp.ampmenus.events.ItemClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.basic.Deposit;
import pl.otekplay.loveotek.enums.DepositType;
import pl.otekplay.loveotek.main.Deposits;
import pl.otekplay.loveotek.storage.DepositSettings;

import java.util.UUID;

public class DepositItem extends NamedMenuItem {
    private final UUID uuid;
    private final DepositType type;

    public DepositItem(ItemStack icon, UUID uuid, DepositType type) {
        super(icon);
        this.uuid = uuid;
        this.type = type;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        Player p = event.getPlayer();
        if (type.hasMax(p.getInventory())) {
            p.sendMessage(DepositSettings.MESSAGE_DEPOSIT_CANT_TAKE_FULL);
            return;
        }
        Deposit deposit = Deposits.get(uuid);
        if(!deposit.canTake(type)){
            p.sendMessage(DepositSettings.MESSAGE_DEPOSIT_CANT_TAKE_AMOUNT);
            return;
        }
        deposit.take(type);
        p.getInventory().addItem(type.getItem().clone());
        p.closeInventory();
        Deposits.open(p);
    }
}