package pl.otekplay.loveotek.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;

import java.util.*;

public class VanishManager {
    private final Collection<UUID> vanished = new ArrayList<>();

    public boolean hasVanish(UUID uuid){
        return vanished.contains(uuid);
    }

    public boolean hasVanish(Player p){
        return hasVanish(p.getUniqueId());
    }

    public void addVanish(Player p){
        vanished.add(p.getUniqueId());
        hidePlayer(p);
    }

    public void removeVanish(Player p){
        vanished.remove(p.getUniqueId());
        showPlayer(p);
    }

    private void hidePlayer(Player p) {
        Player[] online = Bukkit.getOnlinePlayers();
        Arrays.stream(online).filter(player -> !p.equals(player)).forEach(player -> hidePlayer(player,p));
    }

    public void hideVanished(Player p){
        for(UUID uuid:vanished){
            Player player = Bukkit.getPlayer(uuid);
            if(player == null){
                continue;
            }
            hidePlayer(player,p);
        }
    }

    private void showPlayer(Player p) {
        Player[] online = Bukkit.getOnlinePlayers();
        Arrays.stream(online).filter(player -> !p.equals(player)).forEach(player -> showPlayer(player,p));
    }

    private void hidePlayer(Player p, Player hide){
        if(Users.get(p.getUniqueId()).hasPermissions(UserRank.MODERATOR)){
            return;
        }
        p.hidePlayer(hide);
    }
    private void showPlayer(Player p, Player show){
        p.showPlayer(show);
    }

}
