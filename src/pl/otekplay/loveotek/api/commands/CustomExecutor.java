package pl.otekplay.loveotek.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.utils.StringUtil;

import java.util.Arrays;

public class CustomExecutor extends Command {

    private final CommandInfo info;
    private final MainCommand executor;

    public CustomExecutor(CommandInfo info, MainCommand executor) {
        super(info.getName());
        this.info = info;
        this.setUsage(info.getUsage());
        this.setAliases(Arrays.asList(info.getAliases()));
        this.executor = executor;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player == false) {
            try{
                executor.onCommand(null, args);
            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }
        Player player = (Player) sender;
        User user = Users.get(player.getUniqueId());
        if(!user.hasPermissions(info.getRank())){
            Replacer.build(GlobalSettings.MESSAGE_NO_PERMISSIONS).add("%group%",info.getRank().name()).send(player);
            return true;
        }
        if (args.length == 0) {
            executeDefault(player, args);
            return true;
        }
        String arg = args[0].toLowerCase();
        if (!info.getSubList().containsKey(arg)) {
            executeDefault(player, args);
            return true;
        }
        SubCommand command = info.getSubList().get(arg);
        int arguments = args.length - 1;
        if (arguments >= command.minArgs()) {
            command.onCommand(player, Arrays.copyOfRange(args, 1, args.length));
            return true;
        }
        showUsage(sender);
        return true;

    }

    private void executeDefault(Player player, String[] args) {
        if (executor.minArgs() == -1) {
            showUsage(player);
            return;
        }
        if (args.length >= executor.minArgs()) {
            executor.onCommand(player, args);
            return;
        }
        showUsage(player);
    }

    private void showUsage(CommandSender sender) {
        String format = StringUtil.replace(GlobalSettings.MESSAGE_COMMAND_USAGE, "%usage%", this.getUsage());
        sender.sendMessage(format);
    }
}
