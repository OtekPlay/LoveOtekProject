package pl.otekplay.loveotek.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.enums.BackupType;
import pl.otekplay.loveotek.main.Backups;
import pl.otekplay.loveotek.storage.BackupSettings;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class BackupTask implements Runnable {
    @Override
    public void run() {
        Bukkit.broadcastMessage(BackupSettings.MESSAGE_BACKUP_SAVE_GLOBAL);
        for(Player player: Bukkit.getOnlinePlayers()){
            Backups.save(player.getUniqueId(), BackupType.AUTOSAVE);
        }
    }
}
