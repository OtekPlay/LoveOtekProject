package pl.otekplay.loveotek.commands.admin.hand;

import net.minecraft.server.v1_7_R4.ItemStack;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;

public class HandCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("hand", UserRank.HELPER, "hand", "h");
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        ItemStack nms = CraftItemStack.asNMSCopy(player.getItemInHand());
        PacketPlayOutChat packet = new PacketPlayOutChat(nms.E(), true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
