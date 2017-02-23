package pl.otekplay.loveotek.managers;

import com.mongodb.client.model.Projections;
import ninja.amp.ampmenus.items.DepositItem;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.basic.Ban;
import pl.otekplay.loveotek.basic.Deposit;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.builders.ItemBuilder;
import pl.otekplay.loveotek.database.Database;
import pl.otekplay.loveotek.enums.DepositType;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.storage.DepositSettings;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DepositManager {
    private final Map<UUID, Deposit> deposits = new HashMap<>();

    public void init() {
        Database.find(Ban.class).projection(Projections.excludeId()).forEach(document -> {
            Deposit deposit = Database.gson().fromJson(Database.gson().toJson(document), Deposit.class);
            deposits.put(deposit.getUniqueID(), deposit);
        }, (aVoid, throwable) -> {
            String simpleName = this.getClass().getSimpleName();
            System.out.println("[" + simpleName + "] Loaded " + deposits.size() + " " + simpleName.replace("Manager", "s").toLowerCase() + "!");
        });
    }

    public void registerDeposit(UUID uuid) {
        Deposit deposit = new Deposit(uuid);
        deposits.put(uuid, deposit);
    }

    public Deposit getDeposit(UUID uuid) {
        return deposits.get(uuid);
    }

    public void checkPlayer(Player p) {
        boolean message = false;
        for (DepositType type : DepositType.values()) {
            int max = type.getMax();
            int playerAmount = type.amount(p.getInventory());
            if (max >= playerAmount) {
                continue;
            }
            int rem = playerAmount - max;
            type.set(p, rem);
            message = true;
        }
        if(message){
            p.sendMessage(DepositSettings.MESSAGE_DEPOSIT_TAKE_ITEMS);
        }
    }

    public ItemMenu getMenu(UUID uuid) {
        ItemMenu menu = new ItemMenu(DepositSettings.MESSAGE_DEPOSIT_MENU_NAME, ItemMenu.Size.ONE_LINE, Core.getInstance());
        Deposit deposit = getDeposit(uuid);
        menu.setItem(3, new DepositItem(getItem(DepositType.PEARL, deposit), uuid, DepositType.PEARL));
        menu.setItem(4, new DepositItem(getItem(DepositType.GOLDEN, deposit), uuid, DepositType.GOLDEN));
        menu.setItem(5, new DepositItem(getItem(DepositType.KOX, deposit), uuid, DepositType.KOX));
        return menu;
    }


    private ItemStack getItem(DepositType type, Deposit deposit) {
        return new ItemBuilder(type.getItem().clone()).setName(type.getMenuName()).setLore(Replacer.build(DepositSettings.MESSAGE_DEPOSIT_ITEM_LORE).add("%limit%", type.getLimit()).add("%amount%", type.getAmount(deposit)).get()).toItemStack();
    }
}
