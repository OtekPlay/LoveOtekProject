package pl.otekplay.loveotek.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.otekplay.loveotek.main.Core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class ConfigUtil {
    public static void loadSettings(Class cl) {
        try {
            File folder = FileUtil.createFolder(Core.getInstance().getDataFolder());
            File file = FileUtil.createFile(new File(folder, cl.getSimpleName().replace("Settings", ".yml")));
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            for (Field field : cl.getDeclaredFields()) {
                configuration.addDefault(field.getName(), field.get(null));
            }
            configuration.options().copyDefaults(true);
            for (Field field : cl.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getType() == String.class) {
                    field.set(field, ChatUtil.fixColors((String) configuration.get(field.getName())));
                } else if (field.getType() == List.class) {
                    if(field.getGenericType() == String.class) {
                        List<String> list = configuration.getStringList(field.getName());
                        for (int i = 0; i < list.size(); i++) {
                            list.set(i, ChatUtil.fixColors(list.get(i)));
                        }
                        field.set(field, list);
                    }else if(field.getGenericType() == Integer.class){
                        field.set(field, configuration.getIntegerList(field.getName()));
                    }
                } else {
                    field.set(field, configuration.get(field.getName()));
                }
                field.setAccessible(false);
            }
            configuration.save(file);
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }
}
