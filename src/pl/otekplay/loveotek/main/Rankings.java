package pl.otekplay.loveotek.main;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.basic.Attacker;
import pl.otekplay.loveotek.basic.GuildRanking;
import pl.otekplay.loveotek.basic.UserRanking;
import pl.otekplay.loveotek.managers.RankingManager;

import java.util.List;
import java.util.UUID;

public class Rankings {

    public static GuildRanking register(String tag) {
        return manager().registerRanking(tag);
    }

    public static UserRanking register(UUID uuid, int points, int kills, int deaths, int assists) {
        return manager().registerRanking(uuid, points, kills, deaths, assists);
    }

    public static void sortGuilds() {
        manager().sortGuilds();
    }

    public static void sortUsers() {
        manager().sortUsers();
    }

    public static void unregister(String tag) {
        manager().removeRanking(tag);
    }

    public static void unregister(UUID uuid) {
        manager().removeRanking(uuid);
    }

    public static UserRanking get(UUID uuid) {
        return manager().getUserRanking(uuid);
    }

    public static GuildRanking get(String tag) {
        return manager().getGuildRanking(tag);
    }

    public static int place(GuildRanking rank) {
        return manager().getGuildPlace(rank);
    }

    public static int place(UserRanking rank) {
        return manager().getUserPlace(rank);
    }

    public static UserRanking uTop(int i) {
        return manager().getUserRanking(i);
    }

    public static GuildRanking gTop(int i) {
        return manager().getGuildRanking(i);
    }

    public static void uTop(Player player) {
        manager().getTopUsers().open(player);
    }

    public static void gTop(Player player) {
        manager().getTopGuilds().open(player);
    }

    public static void calculate(UUID death, List<Attacker> attackers) {
        manager().calculate(death, attackers);
    }

    public List<UserRanking> users() {
        return manager().getUserRankings();
    }

    public List<GuildRanking> guilds() {
        return manager().getGuildRankings();
    }

    private static RankingManager manager() {
        return Core.getInstance().getRankingManager();
    }
}
