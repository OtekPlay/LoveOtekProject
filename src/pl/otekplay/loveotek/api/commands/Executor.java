package pl.otekplay.loveotek.api.commands;

import org.bukkit.entity.Player;

/**
 * Created by Oskar on 31.12.2016.
 */
public interface Executor {
    default void onCommand(Player player, String[] args) {}

}
