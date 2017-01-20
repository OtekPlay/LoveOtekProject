package pl.otekplay.loveotek.utils;

import net.minecraft.server.v1_7_R4.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R4.event.CraftEventFactory;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.chest.CustomChestInventory;
import pl.otekplay.loveotek.api.chest.SilentContainerChest;

public class InventoryUtil {

    public static void silentOpen(Player p, org.bukkit.block.Block block) {

        EntityPlayer player = ((CraftPlayer) p).getHandle();
        World world = player.world;
        Object tile = world.getTileEntity(block.getX(), block.getY(), block.getZ());
        IInventory inventory = new CustomChestInventory((TileEntityChest) tile);
        int id = Block.getId(world.getType(block.getX(), block.getY(), block.getZ()));
        if (Block.getId(world.getType(block.getX(), block.getY(), block.getZ() + 1)) == id) {
            inventory = new InventoryLargeChest("", inventory, new CustomChestInventory((TileEntityChest) world.getTileEntity(block.getX(), block.getY(), block.getZ() + 1)));
        } else if (Block.getId(world.getType(block.getX(), block.getY(), block.getZ() - 1)) == id) {
            inventory = new InventoryLargeChest("", new CustomChestInventory((TileEntityChest) world.getTileEntity(block.getX(), block.getY(), block.getZ() - 1)), inventory);
        } else if (Block.getId(world.getType(block.getX() + 1, block.getY(), block.getZ())) == id) {
            inventory = new InventoryLargeChest("", inventory, new CustomChestInventory((TileEntityChest) world.getTileEntity(block.getX() + 1, block.getY(), block.getZ())));
        } else if (Block.getId(world.getType(block.getX() - 1, block.getY(), block.getZ())) == id) {
            inventory = new InventoryLargeChest("", new CustomChestInventory((TileEntityChest) world.getTileEntity(block.getX() - 1, block.getY(), block.getZ())), inventory);
        }
        Container container = new SilentContainerChest(player.inventory, inventory);
        container = CraftEventFactory.callInventoryOpenEvent(player, container);
        if (container == null) {
            return;
        }
        int windowId = player.nextContainerCounter();
        player.playerConnection.sendPacket(new PacketPlayOutOpenWindow(windowId, 0, (inventory instanceof InventoryLargeChest) ? "Wielka skrzynia" : "Skrzynia", inventory.getSize(), true));
        player.activeContainer = container;
        player.activeContainer.windowId = windowId;
        player.activeContainer.addSlotListener(player);
        return;
    }
}
