package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.storage.DropSettings;
import pl.otekplay.loveotek.utils.RandomUtil;

@RequiredArgsConstructor
@Getter
@Setter
public class Drop {
    private final String dropName;
    private final double chance;
    private final double vipChance;
    private final int expPlayer;
    private final int expUser;
    private final int height;
    private final ItemStack item;


    public boolean luck(DropUser user) {
        return RandomUtil.getChance(user.hasVip() ? vipChance : chance);
    }


    public void check(Player p, DropUser user) {
        if (user.isDisabledDrop(this)) {
            return;
        }
        if (p.getLocation().getBlockY() > height) {
            return;
        }
        if (!luck(user)) {
            return;
        }
        int amount = p.getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) ? RandomUtil.getRandInt(1, p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1) : 1;
        user.setExp(user.getExp() + getExpUser());
        p.giveExp(getExpPlayer());
        ItemStack itemStack = getItem().clone();
        itemStack.setAmount(amount);
        p.getInventory().addItem(itemStack);
        p.sendMessage(Replacer.build(DropSettings.MESSAGE_DROP_ITEM_FIND).add("%fortune%",amount).add("%name%", getDropName()).get()[0]);
    }
}
