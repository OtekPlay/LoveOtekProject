package pl.otekplay.loveotek.api.chest;

import net.minecraft.server.v1_7_R4.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_7_R4.event.CraftEventFactory;
import org.bukkit.entity.HumanEntity;

import java.util.List;

public class CustomChestInventory extends TileEntityChest {

    private final TileEntityChest chest;

    public CustomChestInventory(TileEntityChest chest) {
        this.chest = chest;
    }


    public ItemStack[] getContents() {
        return this.chest.getContents();
    }

    public void onOpen(CraftHumanEntity who) {
        this.chest.onOpen(who);
    }

    public void onClose(CraftHumanEntity who) {
        this.chest.onClose(who);
    }

    public List<HumanEntity> getViewers() {
        return this.chest.getViewers();
    }

    public void setMaxStackSize(int size) {
        this.chest.setMaxStackSize(size);
    }

    public int getSize() {
        return this.chest.getSize();
    }

    public ItemStack getItem(int i) {
        return this.chest.getItem(i);
    }

    public ItemStack splitStack(int i, int j) {
        return this.chest.splitStack(i, j);
    }

    public ItemStack splitWithoutUpdate(int i) {
        return this.chest.splitWithoutUpdate(i);
    }

    public void setItem(int i, ItemStack itemstack) {
        this.chest.setItem(i, itemstack);
    }

    public String getInventoryName() {
        return this.chest.getInventoryName();
    }

    public boolean k_() {
        return this.chest.k_();
    }

    public void a(String s) {
        this.chest.a(s);
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.chest.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.chest.b(nbttagcompound);
    }

    public int getMaxStackSize() {
        return this.chest.getMaxStackSize();
    }

    public boolean a(EntityHuman entityhuman) {
        return this.chest.a(entityhuman);
    }

    public void u() {
        this.chest.u();
    }

    private void a(TileEntityChest tileentitychest, int i) {
        if (tileentitychest.r()) {
            this.chest.a = false;
        } else if (this.chest.a) {
            switch (i) {
                case 0:
                    if (this.chest.l != tileentitychest) {
                        this.chest.a = false;
                    }
                    break;
                case 1:
                    if (this.chest.k != tileentitychest) {
                        this.chest.a = false;
                    }
                    break;
                case 2:
                    if (this.chest.i != tileentitychest) {
                        this.chest.a = false;
                    }
                    break;
                case 3:
                    if (this.chest.j != tileentitychest) {
                        this.chest.a = false;
                    }
            }
        }

    }

    public void i() {
        this.chest.i();
    }

    private boolean a(int i, int j, int k) {
        if (this.chest.getWorld() == null) {
            return false;
        } else {
            Block block = this.chest.getWorld().getType(i, j, k);
            return block instanceof BlockChest && ((BlockChest) block).a == this.chest.j();
        }
    }

    public void h() {
        this.chest.h();
    }

    public boolean c(int i, int j) {
        return this.chest.c(i, j);
    }

    public void startOpen() {
        if (this.chest.o < 0) {
            this.chest.o = 0;
        }

        int oldPower = Math.max(0, Math.min(15, this.o));
        ++this.chest.o;
        if (this.chest.getWorld() != null) {
            this.chest.getWorld().playBlockAction(this.chest.x, this.chest.y, this.chest.z, this.chest.q(), 1, this.chest.o);
            this.chest.i();
            if (this.chest.o > 0 && this.chest.m == 0.0F && this.chest.i == null && this.chest.k == null) {
                double d1 = (double) this.chest.x + 0.5D;
                double d0 = (double) this.chest.z + 0.5D;
                if (this.chest.l != null) {
                    d0 += 0.5D;
                }

                if (this.chest.j != null) {
                    d1 += 0.5D;
                }

                // this.world.makeSound(d1, (double)this.y + 0.5D, d0, "random.chestopen", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
            }

            if (this.chest.q() == Blocks.TRAPPED_CHEST) {
                int newPower = Math.max(0, Math.min(15, this.chest.o));
                if (oldPower != newPower) {
                    CraftEventFactory.callRedstoneChange(this.chest.getWorld(), this.chest.x, this.chest.y, this.chest.z, oldPower, newPower);
                }
            }

            this.chest.getWorld().applyPhysics(this.chest.x, this.chest.y, this.chest.z, this.chest.q());
            this.chest.getWorld().applyPhysics(this.chest.x, this.chest.y - 1, this.chest.z, this.chest.q());
        }
    }

    public void closeContainer() {
        if (this.chest.q() instanceof BlockChest) {
            int oldPower = Math.max(0, Math.min(15, this.chest.o));
            --this.chest.o;
            if (this.chest.getWorld() == null) {
                return;
            }

            this.chest.getWorld().playBlockAction(this.chest.x, this.chest.y, this.chest.z, this.chest.q(), 1, this.chest.o);
            this.chest.i();
            if (this.chest.o == 0 && this.chest.i == null && this.chest.k == null) {
                double d0 = (double) this.chest.x + 0.5D;
                double d2 = (double) this.chest.z + 0.5D;
                if (this.chest.l != null) {
                    d2 += 0.5D;
                }

                if (this.chest.j != null) {
                    d0 += 0.5D;
                }

                // this.chest.world.makeSound(d0, (double)this.chest.y + 0.5D, d2, "random.chestclosed", 0.5F, this.chest.world.random.nextFloat() * 0.1F + 0.9F);
            }

            if (this.chest.q() == Blocks.TRAPPED_CHEST) {
                int newPower = Math.max(0, Math.min(15, this.chest.o));
                if (oldPower != newPower) {
                    CraftEventFactory.callRedstoneChange(this.chest.getWorld(), this.chest.x, this.chest.y, this.chest.z, oldPower, newPower);
                }
            }

            this.chest.getWorld().applyPhysics(this.chest.x, this.chest.y, this.chest.z, this.chest.q());
            this.chest.getWorld().applyPhysics(this.chest.x, this.chest.y - 1, this.chest.z, this.chest.q());
        }

    }

    public boolean b(int i, ItemStack itemstack) {
        return this.chest.b(i, itemstack);
    }

    public void s() {
        this.chest.s();
    }

    public int j() {
        return this.chest.j();
    }
}
