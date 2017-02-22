package pl.otekplay.loveotek.runnables;

import ninja.amp.ampmenus.items.MenuItem;
import ninja.amp.ampmenus.items.StableMenuItem;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import pl.otekplay.loveotek.builders.ItemBuilder;
import pl.otekplay.loveotek.main.Core;

import java.util.UUID;

public class ChestTask implements Runnable {
    private final UUID uuid;
    private final ItemMenu menu;
    private final int tick;
    private final int time;

    public ChestTask(UUID uuid, ItemMenu menu, int tick, int time) {
        this.uuid = uuid;
        this.menu = menu;
        this.tick = tick;
        this.time = time;
    }

    @Override
    public void run() {
        Player p = Bukkit.getPlayer(uuid);
        if (p == null) {
            return;
        }
        MenuItem[] items = menu.getItems();
        MenuItem[] newItems = new MenuItem[items.length];
        for (int i = 0; i < items.length; i++) {
            if (i == 8) {
                newItems[0] = items[i];
            } else {
                newItems[i + 1] = items[i];
            }
        }
        if (tick == 4) {
            Bukkit.getScheduler().runTask(Core.getInstance(), () -> {
                Firework work = p.getWorld().spawn(p.getLocation().subtract(0,1,0), Firework.class);
                FireworkEffect fire = FireworkEffect.builder().withColor(Color.AQUA).with(FireworkEffect.Type.STAR).build();
                FireworkMeta meta = work.getFireworkMeta();
                meta.addEffect(fire);
                work.setFireworkMeta(meta);
            });
            menu.fillSlots(new StableMenuItem(new ItemBuilder(Material.STAINED_GLASS_PANE).toItemStack()));
            menu.setItem(4, newItems[4]);
            menu.update(p);
            Bukkit.getScheduler().runTaskLaterAsynchronously(Core.getInstance(), () -> {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.SKELETON_DEATH, 3, 3);
                p.getInventory().addItem(newItems[4].getIcon().clone());
            },20);
        } else {
            menu.setItems(newItems);
            menu.update(p);
            int nextTick = (time == 0) ? tick + 1 : tick;
            p.playSound(p.getLocation(), Sound.SKELETON_DEATH, 3, 3);
            Bukkit.getScheduler().runTaskLaterAsynchronously(Core.getInstance(), new ChestTask(uuid, menu, nextTick, (time == 0) ? 5 : time - 1), nextTick);
        }
    }
}
