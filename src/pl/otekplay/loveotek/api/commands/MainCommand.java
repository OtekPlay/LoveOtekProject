package pl.otekplay.loveotek.api.commands;


import pl.otekplay.loveotek.basic.CommandInfo;

/**
 * Created by Oskar on 07.01.2017.
 */
public interface MainCommand extends SubCommand{
    CommandInfo getDefaultInfo();
}
