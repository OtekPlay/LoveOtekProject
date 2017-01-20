package pl.otekplay.loveotek.main;

import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.otekplay.loveotek.commands.admin.backup.BackupCommand;
import pl.otekplay.loveotek.commands.admin.group.GroupCommand;
import pl.otekplay.loveotek.commands.admin.root.RootCommand;
import pl.otekplay.loveotek.commands.admin.stop.StopCommand;
import pl.otekplay.loveotek.commands.admin.fly.FlyCommand;
import pl.otekplay.loveotek.commands.admin.gamemode.GamemodeCommand;
import pl.otekplay.loveotek.commands.admin.inventory.ClearInventoryCommand;
import pl.otekplay.loveotek.commands.admin.inventory.EnderchestCommand;
import pl.otekplay.loveotek.commands.admin.inventory.OpenInventoryCommand;
import pl.otekplay.loveotek.commands.admin.teleport.CordsCommand;
import pl.otekplay.loveotek.commands.admin.teleport.GoCommand;
import pl.otekplay.loveotek.commands.admin.teleport.RandomCommand;
import pl.otekplay.loveotek.commands.admin.teleport.SummonCommand;
import pl.otekplay.loveotek.commands.admin.timings.TimingsCommand;
import pl.otekplay.loveotek.commands.admin.vanish.VanishCommand;
import pl.otekplay.loveotek.commands.guild.normal.GuildCommand;
import pl.otekplay.loveotek.commands.player.deposit.DepositCommand;
import pl.otekplay.loveotek.commands.player.drop.DropCommand;
import pl.otekplay.loveotek.commands.player.history.HistoryCommand;
import pl.otekplay.loveotek.commands.player.home.HomeCommand;
import pl.otekplay.loveotek.commands.player.home.SetHomeCommand;
import pl.otekplay.loveotek.commands.player.ranking.RankingCommand;
import pl.otekplay.loveotek.commands.player.ranking.TopCommand;
import pl.otekplay.loveotek.commands.player.spawn.SetSpawnCommand;
import pl.otekplay.loveotek.commands.player.spawn.SpawnCommand;
import pl.otekplay.loveotek.commands.player.teleport.TeleportAcceptCommand;
import pl.otekplay.loveotek.commands.player.teleport.TeleportRequestCommand;
import pl.otekplay.loveotek.listeners.block.BeaconEffectListener;
import pl.otekplay.loveotek.listeners.block.BlockBreakListener;
import pl.otekplay.loveotek.listeners.block.BlockPlaceListener;
import pl.otekplay.loveotek.listeners.chat.AsyncPlayerChatListener;
import pl.otekplay.loveotek.listeners.entity.EntityDamageByEntityListener;
import pl.otekplay.loveotek.listeners.entity.EntityDamageListener;
import pl.otekplay.loveotek.listeners.entity.EntityExplodeListener;
import pl.otekplay.loveotek.listeners.entity.EntityTargetListener;
import pl.otekplay.loveotek.listeners.food.FoodLevelChangeListener;
import pl.otekplay.loveotek.listeners.inventory.InventoryClickListener;
import pl.otekplay.loveotek.listeners.inventory.InventoryOpenListener;
import pl.otekplay.loveotek.listeners.player.*;
import pl.otekplay.loveotek.managers.*;
import pl.otekplay.loveotek.runnables.BackupTask;
import pl.otekplay.loveotek.runnables.CombatTask;
import pl.otekplay.loveotek.runnables.GeneratorTask;
import pl.otekplay.loveotek.storage.*;
import pl.otekplay.loveotek.utils.ConfigUtil;

public class Core extends JavaPlugin {
    @Getter
    private static Core instance;
    @Getter(AccessLevel.PROTECTED)
    private final UserManager userManager = new UserManager();
    @Getter(AccessLevel.PROTECTED)
    private final GuildManager guildManager = new GuildManager();
    @Getter(AccessLevel.PROTECTED)
    private final CuboidManager cuboidManager = new CuboidManager();
    @Getter(AccessLevel.PROTECTED)
    private final CommandManager commandManager = new CommandManager();
    @Getter(AccessLevel.PROTECTED)
    private final RankingManager rankingManager = new RankingManager();
    @Getter(AccessLevel.PROTECTED)
    private final HistoryManager historyManager = new HistoryManager();
    @Getter(AccessLevel.PROTECTED)
    private final CombatManager combatManager = new CombatManager();
    @Getter(AccessLevel.PROTECTED)
    private final ChatManager chatManager = new ChatManager();
    @Getter(AccessLevel.PROTECTED)
    private final BackupManager backupManager = new BackupManager();
    @Getter(AccessLevel.PROTECTED)
    private final VanishManager vanishManager = new VanishManager();
    @Getter(AccessLevel.PROTECTED)
    private final DepositManager depositManager = new DepositManager();
    @Getter(AccessLevel.PROTECTED)
    private final TeleporterManager teleporterManager = new TeleporterManager();
    @Getter(AccessLevel.PROTECTED)
    private final DropManager dropManager = new DropManager();
    @Getter(AccessLevel.PROTECTED)
    private final GeneratorManager generatorManager = new GeneratorManager();

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        init();
        initSettings();
        initRunnables();
        initCommands();
        initListeners();
        initFactories();
    }


    private void init() {
        ((SimplePluginManager)Bukkit.getPluginManager()).useTimings(true);
    }

    private void initSettings() {
        ConfigUtil.loadSettings(BackupSettings.class);
        ConfigUtil.loadSettings(ChatSettings.class);
        ConfigUtil.loadSettings(CombatSettings.class);
        ConfigUtil.loadSettings(CuboidSettings.class);
        ConfigUtil.loadSettings(DepositSettings.class);
        ConfigUtil.loadSettings(GlobalSettings.class);
        ConfigUtil.loadSettings(GuildSettings.class);
        ConfigUtil.loadSettings(HomeSettings.class);
        ConfigUtil.loadSettings(RankingSettings.class);
        ConfigUtil.loadSettings(TeleportSettings.class);
        ConfigUtil.loadSettings(DropSettings.class);
        ConfigUtil.loadSettings(GeneratorSettings.class);
    }

    private void initCommands() {
        CommandManager manager = getCommandManager();
        manager.add(new GamemodeCommand());
        manager.add(new TimingsCommand());
        manager.add(new GuildCommand());
        manager.add(new GroupCommand());
        manager.add(new RankingCommand());
        manager.add(new HistoryCommand());
        manager.add(new TopCommand());
        manager.add(new BackupCommand());
        manager.add(new RootCommand());
        manager.add(new FlyCommand());
        manager.add(new TeleportAcceptCommand());
        manager.add(new TeleportRequestCommand());
        manager.add(new VanishCommand());
        manager.add(new SummonCommand());
        manager.add(new GoCommand());
        manager.add(new StopCommand());
        manager.add(new CordsCommand());
        manager.add(new RandomCommand());
        manager.add(new DepositCommand());
        manager.add(new SpawnCommand());
        manager.add(new SetSpawnCommand());
        manager.add(new SetHomeCommand());
        manager.add(new HomeCommand());
        manager.add(new DropCommand());
        manager.add(new OpenInventoryCommand());
        manager.add(new EnderchestCommand());
        manager.add(new ClearInventoryCommand());
    }

    private void initListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BeaconEffectListener(), this);
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new InventoryOpenListener(), this);
        pm.registerEvents(new InventoryClickListener(), this);
        pm.registerEvents(new BlockBreakListener(), this);
        pm.registerEvents(new PlayerPickupListener(), this);
        pm.registerEvents(new BlockPlaceListener(), this);
        pm.registerEvents(new PlayerMoveListener(), this);
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new EntityDamageListener(), this);
        pm.registerEvents(new EntityDamageByEntityListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
        pm.registerEvents(new EntityTargetListener(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new FoodLevelChangeListener(), this);
        pm.registerEvents(new AsyncPlayerChatListener(), this);
        pm.registerEvents(new EntityExplodeListener(),this);
    }

    private void initFactories() {
        getCommandManager().init();
        getRankingManager().init();
        getDropManager().init();
        getGeneratorManager().init();
    }

    private void initRunnables() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new GeneratorTask(), 10, 10);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new BackupTask(), 20 * 60 * 30, 20 * 60 * 30);
        Bukkit.getScheduler().runTaskTimer(this, new CombatTask(), 10, 10);
    }
}
