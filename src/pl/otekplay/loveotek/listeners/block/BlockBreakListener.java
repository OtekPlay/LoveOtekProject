package pl.otekplay.loveotek.listeners.block;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import pl.otekplay.loveotek.basic.DropUser;
import pl.otekplay.loveotek.basic.Generator;
import pl.otekplay.loveotek.enums.GeneratorType;
import pl.otekplay.loveotek.main.CobbleX;
import pl.otekplay.loveotek.main.Drops;
import pl.otekplay.loveotek.main.Generators;
import pl.otekplay.loveotek.storage.CobbleXSettings;
import pl.otekplay.loveotek.storage.DropSettings;
import pl.otekplay.loveotek.utils.LocationUtil;
import pl.otekplay.loveotek.utils.RecalculateUtil;

public class BlockBreakListener implements Listener {

    private static final ItemStack STONE_ITEM = new ItemStack(Material.STONE, 1);
    private static final ItemStack COBBLESTONE_ITEM = new ItemStack(Material.COBBLESTONE, 1);

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Player p = event.getPlayer();
        Block block = event.getBlock();
        if (DropSettings.DROP_MENU_BLOCKED_BLOCKS.contains(block.getTypeId())) {
            block.breakNaturally(new ItemStack(Material.AIR));
            return;
        }
        Location loc = block.getLocation();
        if (LocationUtil.canBuild(p, loc)) {
            event.setCancelled(true);
            return;
        }
        if (checkCobbleX(p, block)) {
            event.setCancelled(true);
            return;
        }
        if (checkGenerators(p, block)) {
            event.setCancelled(true);
            return;
        }
        checkDrops(p, block);
    }

    private boolean isPickaxe(ItemStack itemStack) {
        return itemStack != null && itemStack.getType().name().contains("PICKAXE");
    }


    private boolean checkGenerators(Player p, Block block) {
        if (block.getType() == Material.ENDER_STONE) {
            Location location = block.getLocation();
            if (!Generators.is(location)) {
                return false;
            }
            Generator generator = Generators.get(location);
            GeneratorType type = generator.getType();
            Generators.unregister(generator.getLocation());
            block.breakNaturally(new ItemStack(Material.AIR));
            p.getInventory().addItem(type.getItem().clone());
            return true;
        }
        if (block.getType() == GeneratorType.STONE.getMaterial() || block.getType() == GeneratorType.OBSIDIAN.getMaterial()) {
            Location location = block.getLocation().subtract(0, 1, 0);
            if (location.getBlock().getType() != Material.ENDER_STONE) {
                return false;
            }
            if (!Generators.is(location)) {
                return false;
            }
            Generators.get(location).destroy();
        }
        return false;
    }

    private boolean checkCobbleX(Player p, Block block) {
        if(block.getType() != Material.getMaterial(CobbleXSettings.COBBLEX_ITEM_ID)){
            return false;
        }
        Location location = block.getLocation();
        if (!CobbleX.is(location)) {
            return false;
        }
        CobbleX.remove(location);
        location.getBlock().setType(Material.AIR);
        p.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES,10);
        Item item = p.getWorld().dropItem(location.add(0, 1, 0), CobbleX.random());
        item.setVelocity(new Vector(0, 0.5, 0));
        return true;
    }

    private void checkDrops(Player p, Block block) {
        if (block.getType() != Material.STONE) {
            return;
        }
        if (!isPickaxe(p.getItemInHand())) {
            return;
        }
        DropUser user = Drops.get(p.getUniqueId());
        Drops.drops().forEach(drop -> drop.check(p, user));
        if (user.isCobble()) {
            p.getInventory().addItem(p.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) ? STONE_ITEM : COBBLESTONE_ITEM).clone();
        }
        user.setExp(user.getExp() + DropSettings.DROP_USER_STONE_EXP);
        p.giveExp(DropSettings.DROP_PLAYER_STONE_EXP);
        block.breakNaturally(new ItemStack(Material.AIR));
        RecalculateUtil.recalculateDurability(p, p.getItemInHand());
    }
}
