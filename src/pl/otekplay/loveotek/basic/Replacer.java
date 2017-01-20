package pl.otekplay.loveotek.basic;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.utils.StringUtil;

import java.util.Arrays;
import java.util.List;

public class Replacer {
    private final String[] message;

    private Replacer(String... message) {
        this.message = message;
    }

    public Replacer add(String key, String value) {
        for (int i = 0; i < message.length; i++) {
            message[i] = StringUtil.replace(message[i], key, value);
        }
        return this;
    }

    public Replacer add(String key, int value) {
        return add(key, value + "");
    }

    public void send(Player p) {
        p.sendMessage(message);
    }

    public void send(User user) {
        if (!user.isOnline()) {
            return;
        }
        send(user.getPlayer());
    }

    public void send(Guild guild) {
        guild.getMembers().keySet().stream().filter(uuid -> Users.get(uuid).isOnline()).forEach(uuid -> send(Users.get(uuid)));
    }
    public void broadcast(UserRank rank){
        for(Player player:Bukkit.getOnlinePlayers()){
            User user = Users.get(player.getUniqueId());
            if(!user.hasPermissions(rank)){
                continue;
            }
            send(player);
        }
    }

    public String[] get() {
        return message;
    }

    public void broadcast() {
        Player[] players = Bukkit.getOnlinePlayers();
        Arrays.stream(players).forEach(player -> send(player));
    }

    public static Replacer build(String... message) {
        return new Replacer(message);
    }

    public static Replacer build(List<String> message) {
        return new Replacer(message.toArray(new String[message.size()]));
    }
}
