package pl.otekplay.loveotek.api.chest;


import net.minecraft.server.v1_7_R4.ContainerChest;
import net.minecraft.server.v1_7_R4.EntityHuman;
import net.minecraft.server.v1_7_R4.IInventory;
import net.minecraft.server.v1_7_R4.ItemStack;
import net.minecraft.server.v1_7_R4.PlayerInventory;

public class SilentContainerChest extends ContainerChest {

    public SilentContainerChest(IInventory i1, IInventory i2) {
        super(i1, i2);
        i2.closeContainer();
    }

    @Override
    public void b(EntityHuman entityHuman) {
        PlayerInventory playerinventory = entityHuman.inventory;
        if (playerinventory.getCarried() != null) {
            ItemStack carried = playerinventory.getCarried();
            playerinventory.setCarried(null);
            entityHuman.drop(carried, false);
        }
    }

}