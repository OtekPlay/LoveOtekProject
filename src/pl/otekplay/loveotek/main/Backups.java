package pl.otekplay.loveotek.main;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.basic.Backup;
import pl.otekplay.loveotek.enums.BackupType;
import pl.otekplay.loveotek.managers.BackupManager;

import java.util.UUID;

public class Backups {

    private static BackupManager manager() {
        return Core.getInstance().getBackupManager();
    }

    public static Backup get(UUID uuid, long time) {
        return manager().getBackup(uuid, time);
    }

    public static void save(UUID uuid, BackupType type) {
        manager().registerBackup(uuid, type);
    }

    public static boolean is(UUID uuid, long time) {
        return manager().isBackup(uuid, time);
    }

    public static void menu(Player open, UUID uuid) {
        manager().getItemMenu(uuid).open(open);
    }
}
