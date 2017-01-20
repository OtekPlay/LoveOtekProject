package pl.otekplay.loveotek.managers;

import lombok.Getter;
import ninja.amp.ampmenus.items.RankingItem;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.basic.*;
import pl.otekplay.loveotek.builders.ItemBuilder;
import pl.otekplay.loveotek.enums.ParticipantType;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.main.Histories;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.CombatSettings;
import pl.otekplay.loveotek.storage.RankingSettings;

import java.util.*;

public class RankingManager {
    @Getter
    private final List<UserRanking> userRankings = new ArrayList<>();
    @Getter
    private final List<GuildRanking> guildRankings = new ArrayList<>();
    private final Comparator<Ranking> comparator = (o1, o2) -> Integer.compare(o2.getPoints(), o1.getPoints());

    public void init() {
        sortUsers();
        sortGuilds();
    }

    public ItemMenu getTopUsers() {
        ItemMenu menu = new ItemMenu(RankingSettings.MESSAGE_RANKING_HEADER_MENU, ItemMenu.Size.TWO_LINE, Core.getInstance());
        int i = 0;
        for (UserRanking ranking : userRankings) {
            if (i == 17) {
                break;
            }
            menu.setItem(i, new RankingItem(getPropertyRanking(ranking, i + 1)));
            i++;
        }
        return menu;
    }

    public ItemMenu getTopGuilds() {
        ItemMenu menu = new ItemMenu(RankingSettings.MESSAGE_RANKING_HEADER_MENU, ItemMenu.Size.TWO_LINE, Core.getInstance());
        int i = 0;
        for (GuildRanking ranking : guildRankings) {
            if (i == 17) {
                break;
            }
            menu.setItem(i, new RankingItem(getPropertyRanking(ranking, i + 1)));
            i++;
        }
        return menu;
    }

    public UserRanking registerRanking(UUID uuid, int points, int kills, int deaths, int assists) {
        UserRanking ranking = new UserRanking(uuid, points, kills, deaths, assists);
        userRankings.add(ranking);
        sortUsers();
        return ranking;
    }

    public GuildRanking registerRanking(String tag) {
        GuildRanking ranking = new GuildRanking(tag);
        guildRankings.add(ranking);
        sortGuilds();
        return ranking;
    }

    public void removeRanking(UUID uuid) {
        userRankings.remove(getUserRanking(uuid));
    }

    public void removeRanking(String tag) {
        guildRankings.remove(getGuildRanking(tag));
    }

    public UserRanking getUserRanking(int i) {
        return getUserRankings().get(i);
    }

    public GuildRanking getGuildRanking(int i) {
        return getGuildRankings().get(i);
    }

    public int getGuildPlace(GuildRanking guildRanking) {
        for (int i = 0; i < guildRankings.size(); i++) {
            if (guildRankings.get(i).equals(guildRanking)) {
                return i + 1;
            }
        }
        return -1;
    }

    public int getUserPlace(UserRanking userRanking) {
        for (int i = 0; i < userRankings.size(); i++) {
            if (userRankings.get(i).equals(userRanking)) {
                return i + 1;
            }
        }
        return -1;
    }

    public UserRanking getUserRanking(UUID uuid) {
        return getUserRankings().parallelStream().filter(userRanking -> userRanking.getUniqueID().equals(uuid)).findFirst().orElse(null);
    }

    public GuildRanking getGuildRanking(String tag) {
        return getGuildRankings().parallelStream().filter(guildRanking -> guildRanking.getTag().equalsIgnoreCase(tag)).findFirst().orElse(null);
    }

    public void calculate(UUID death, List<Attacker> attackers) {
        if (attackers.size() == 0) {
            return;
        }
        UserRanking rank = getUserRanking(death);
        int totalPointsPlayers = 0;
        int totalDamage = 0;
        for (Attacker attacker : attackers) {
            totalPointsPlayers = getUserRanking(attacker.getUniqueID()).getPoints() + totalPointsPlayers;
            totalDamage = attacker.getDamageDone() + totalDamage;
        }
        double deadpoints = rank.getPoints();
        double killPoints = totalPointsPlayers / attackers.size();
        double plus, minus, procent = deadpoints * 0.075D;
        if (killPoints <= deadpoints) {
            double wartosc = (deadpoints - killPoints) / killPoints + 1.0D;
            plus = Math.round(procent * wartosc);
            minus = Math.round(procent);
        } else {
            double wartosc = (killPoints - deadpoints) / deadpoints + 1.0D;
            plus = Math.round(procent / wartosc);
            minus = Math.round(procent / (wartosc * wartosc));
        }
        Collections.sort(attackers, (o1, o2) -> o2.getDamageDone() - o1.getDamageDone());
        List<Participant> participants = new ArrayList<>();
        for (int i = 0; i < attackers.size(); i++) {
            Attacker att = attackers.get(i);
            int perAttack = (int) ((att.getDamageDone() * 100.0) / totalDamage);
            if (CombatSettings.COMBAT_VALID_MIN_PERCETAGE > perAttack) {
                continue;
            }
            participants.add(new Participant(att.getUniqueID(), (i == 0) ? ParticipantType.KILLER : ParticipantType.ASSISTANT, att.getDamageDone(), perAttack, (int) (plus * perAttack / 100)));
        }
        participants.add(new Participant(death, ParticipantType.DEATH, totalDamage, 100, (int) -minus));
        participants.forEach(participant -> participant.getParticipantType().update(participant.getRanking(),participant.getDeservePoints()));
        Histories.register(participants).broadcast();
        sortUsers();
        sortGuilds();
    }

    private ItemStack getPropertyRanking(UserRanking ranking, int i) {
        String[] lore =
                Replacer.build(RankingSettings.MESSAGE_RANKING_LIST_USER)
                        .add("%points%", ranking.getPoints() + "")
                        .add("%kills%", ranking.getKills() + "")
                        .add("%deaths%", ranking.getDeaths() + "")
                        .add("%assists%", ranking.getAssists() + "")
                        .add("%place%", i + "")
                        .get();
        return new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD)).setName(Replacer.build(RankingSettings.MESSAGE_RANKING_HEADER_USER).add("%name%", ranking.getUser().getName()).get()[0]).setLore(lore).toItemStack();
    }

    private ItemStack getPropertyRanking(GuildRanking ranking, int i) {
        Guild guild = ranking.getGuild();
        String[] lore = Replacer.build(RankingSettings.MESSAGE_RANKING_LIST_GUILD)
                .add("%points%", ranking.getPoints() + "")
                .add("%kills%", ranking.getKills() + "")
                .add("%leader%", Users.get(guild.getLeaderUniqueID()).getName())
                .add("%assists%", ranking.getAssists() + "")
                .add("%deaths%", ranking.getDeaths() + "")
                .add("%place%", i + "")
                .get();
        return new ItemBuilder(new ItemStack(Material.DIAMOND_BLOCK)).setName(Replacer.build(RankingSettings.MESSAGE_RANKING_HEADER_GUILD).add("%tag%", guild.getTag()).get()[0]).setLore(lore).toItemStack();
    }

    public void sortUsers() {
        Collections.sort(userRankings, comparator);
    }

    public void sortGuilds() {
        Collections.sort(guildRankings, comparator);
    }

}
