package pl.otekplay.loveotek.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.basic.Deposit;
import pl.otekplay.loveotek.main.Deposits;
import pl.otekplay.loveotek.storage.DepositSettings;
import pl.otekplay.loveotek.utils.ItemUtil;

/**
 * Created by Oskar on 14.01.2017.
 */
@RequiredArgsConstructor
public enum DepositType {
    PEARL(new ItemStack(Material.ENDER_PEARL, 1)),
    GOLDEN(new ItemStack(Material.GOLDEN_APPLE, 1)),
    KOX(new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1)), ;
    @Getter
    private final ItemStack item;

    public int getMax() {
        switch (this) {
            case PEARL:
                return DepositSettings.DEPOSIT_MAX_PEARL;
            case GOLDEN:
                return DepositSettings.DEPOSIT_MAX_GOLDEN;
            case KOX:
                return DepositSettings.DEPOSIT_MAX_KOX;
        }
        return 0;
    }

    public  boolean hasMax(Inventory inventory){
        return amount(inventory) >= getMax();
    }
    public int amount(Inventory inventory) {
        return ItemUtil.getItemCount(inventory, getID(), getData());
    }

    public void set(Player player, int rem) {
        Deposit deposit = Deposits.get(player.getUniqueId());
        ItemUtil.removeItems(player.getInventory(), getID(), getData(), rem);
        switch (this) {
            case PEARL:
                deposit.setPearls(deposit.getPearls() + rem);
                break;
            case GOLDEN:
                deposit.setGolden(deposit.getGolden() + rem);
                break;
            case KOX:
                deposit.setKox(deposit.getKox() + rem);
                break;
        }
    }

    public int getAmount(Deposit deposit){
        switch (this) {
            case PEARL:
                return deposit.getPearls();
            case GOLDEN:
                return deposit.getGolden();
            case KOX:
                return deposit.getKox();
        }
        return 0;
    }

    public String getMenuName() {
        switch (this) {
            case PEARL:
                return DepositSettings.MESSAGE_DEPOSIT_ITEM_NAME_PEARL;
            case GOLDEN:
                return DepositSettings.MESSAGE_DEPOSIT_ITEM_NAME_GOLDEN;
            case KOX:
                return DepositSettings.MESSAGE_DEPOSIT_ITEM_NAME_KOX;
        }
        return "";
    }
    public int getLimit() {
        switch (this) {
            case PEARL:
                return DepositSettings.DEPOSIT_MAX_PEARL;
            case GOLDEN:
                return DepositSettings.DEPOSIT_MAX_GOLDEN;
            case KOX:
                return DepositSettings.DEPOSIT_MAX_KOX;
        }
        return 0;
    }

    private int getID() {
        if (this == PEARL) {
            return 368;
        }
        return 322;
    }

    private short getData() {
        return (short) ((this == KOX) ? 1 : 0);
    }
}
