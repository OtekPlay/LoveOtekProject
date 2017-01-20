package pl.otekplay.loveotek.managers;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import pl.otekplay.loveotek.api.commands.CustomExecutor;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.utils.FileUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

public class CommandManager {
    private final Collection<MainCommand> commands = new ArrayList<>();
    private final SimpleCommandMap map = (SimpleCommandMap) initCommandMap();
    private File folder;

    public void add(MainCommand command) {
        commands.add(command);
    }

    public void init() {
        Bukkit.getScheduler().runTaskLaterAsynchronously(Core.getInstance(), () -> {
            try {
                Field field = map.getClass().getDeclaredField("knownCommands");
                field.setAccessible(true);
                Map<String, Command> value = ((Map<String, Command>) field.get(map));
                value.clear();
                commands.forEach(cmd -> {
                    CustomExecutor command = new CustomExecutor(loadCommandFile(cmd), cmd);
                    value.put(command.getName(), command);
                    for (String string : command.getAliases()) {
                        value.put(string, command);
                    }
                });
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }, 50);
        folder = FileUtil.createFolder(new File(Core.getInstance().getDataFolder(), "commands"));
        createFiles();
    }


    private void createFiles() {
        commands.forEach(command -> saveCommandFile(command));
    }

    private Object initCommandMap() {
        try {
            Field f = CraftServer.class.getDeclaredField("commandMap");
            f.setAccessible(true);
            return f.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveCommandFile(MainCommand command) {
        try {
            CommandInfo info = command.getDefaultInfo();
            File file = new File(folder, info.getName() + ".yml");
            if (!file.exists()) {
                file.createNewFile();
            }
            YamlConfiguration yaml = new YamlConfiguration();
            yaml.addDefault("Name", info.getName());
            yaml.addDefault("Rank", info.getRank().name());
            yaml.addDefault("Usage", info.getUsage());
            yaml.addDefault("Aliases", Arrays.asList(info.getAliases()));
            for (Map.Entry<String, SubCommand> entry : info.getSubList().entrySet()) {
                yaml.addDefault(entry.getValue().getClass().getSimpleName(), entry.getKey());
            }
            yaml.options().copyDefaults(true);
            yaml.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private CommandInfo loadCommandFile(MainCommand command) {
        CommandInfo info = command.getDefaultInfo();
        File file = new File(folder, info.getName() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        String name = yaml.getString("Name");
        UserRank rank = UserRank.getGroup(yaml.getString("Rank"));
        String usage = yaml.getString("Usage");
        List<String> aliases = yaml.getStringList("Aliases");
        CommandInfo newInfo = new CommandInfo(name, rank, usage, aliases.toArray(new String[aliases.size()]));
        for (Map.Entry<String, SubCommand> entry : info.getSubList().entrySet()) {
            newInfo.sub(entry.getKey(), entry.getValue());
        }
        return newInfo;
    }
}
