package ninja.amp.ampmenus.items;

import ninja.amp.ampmenus.events.ItemClickEvent;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Backups;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.BackupSettings;
import pl.otekplay.loveotek.utils.TimeUtil;

import java.util.UUID;

public class BackupItem extends StaticMenuItem {
    private final UUID uuid;
    private final long time;

    public BackupItem(ItemStack icon, UUID uuid, long time) {
        super(icon);
        this.uuid = uuid;
        this.time = time;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        super.onItemClick(event);
        Backups.get(uuid,time).equip();
        Replacer.build(BackupSettings.MESSAGE_BACKUP_PLAYER_GOT).add("%name%", Users.get(uuid).getName()).add("%time%", TimeUtil.getDate(time)).add("%admin%",event.getPlayer().getName()).broadcast(UserRank.MODERATOR);
    }
}
