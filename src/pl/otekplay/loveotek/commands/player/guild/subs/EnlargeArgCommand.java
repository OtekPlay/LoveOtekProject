package pl.otekplay.loveotek.commands.player.guild.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.Cuboid;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.GuildRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GuildSettings;
import pl.otekplay.loveotek.utils.ItemUtil;

public class EnlargeArgCommand implements SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        User user = Users.get(player.getUniqueId());
        if (!user.hasGuild()) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_YOU_NEED);
            return;
        }

        Guild guild = user.getGuild();
        GuildRank rank = guild.getGuildRank(user.getUniqueID());
        if (rank == GuildRank.MEMBER) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_TOO_LOW_PERMISSIONS);
            return;
        }
        Cuboid cuboid = guild.getCuboid();
        int size = cuboid.getSize();
        if (size >= GuildSettings.GUILD_SIZE_MAX) {
            player.sendMessage(GuildSettings.MESSAGE_GUILD_SIZE_UPGRADE_MAX);
            return;
        }
        int totalUpgrades = size / GuildSettings.GUILD_SIZE_UPGRADE;
        int startUpgrades = GuildSettings.GUILD_SIZE_START / GuildSettings.GUILD_SIZE_UPGRADE;
        int upgrades = totalUpgrades - startUpgrades;
        upgrades++;
        int amount = GuildSettings.GUILD_AMOUNT_PER_UPGRADE * upgrades;
        if (ItemUtil.getItemCount(player.getInventory(), GuildSettings.GUILD_UPGRADE_ITEM_ID, 0) < amount) {
            Replacer.build(GuildSettings.MESSAGE_GUILD_UPGRADE_NEED_AMOUNT).add("%amount%", amount + "").send(player);
            return;
        }
        cuboid.setSize(size + GuildSettings.GUILD_SIZE_UPGRADE);
        ItemUtil.removeItems(player.getInventory(), GuildSettings.GUILD_UPGRADE_ITEM_ID, 0, amount);
        Replacer.build(GuildSettings.MESSAGE_GUILD_BROADCAST_UPGPRADE).add("%nick%", user.getName()).add("%tag%", guild.getTag()).broadcast();
    }

    @Override
    public int minArgs() {
        return 0;
    }
}
