package pl.otekplay.loveotek.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import pl.otekplay.loveotek.enums.GuildRank;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.main.Rankings;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GuildSettings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@Getter
@Setter
public class Guild {
    private final String tag;
    private final String name;
    private final long createDate = System.currentTimeMillis();
    private final Collection<UUID> invites = new ArrayList<>();
    private final Collection<String> allyGuilds = new ArrayList<>();
    private final Map<UUID, GuildRank> members = new ConcurrentHashMap<>();
    private boolean pvp;
    private Location home;
    private long lastMoveInformation = 0;
    private long lastTnTExplode = 0;

    public UUID getLeaderUniqueID() {
        return members.keySet().stream().filter(uuid -> members.get(uuid) == GuildRank.LEADER).findFirst().orElse(null);
    }

    public Cuboid getCuboid() {
        return Cuboids.get(tag);
    }

    public GuildRanking getRanking() {
        return Rankings.get(tag);
    }

    public void addMember(UUID uuid, GuildRank rank) {
        members.put(uuid, rank);
    }

    public void setLeader(UUID uuid) {
        UUID oldLeader = getLeaderUniqueID();
        members.put(oldLeader, GuildRank.MEMBER);
        members.put(uuid, GuildRank.LEADER);
        Users.get(oldLeader).updateTag();
        Users.get(uuid).updateTag();
    }

    public void addAlly(String ally) {
        allyGuilds.add(ally);
    }

    public void removeAlly(String ally) {
        allyGuilds.remove(ally);
    }

    public void removeMember(UUID uuid) {
        members.remove(uuid);
    }

    public GuildRank getGuildRank(UUID uuid) {
        return members.get(uuid);
    }

    public boolean isMember(UUID uuid) {
        return members.keySet().contains(uuid);
    }

    public boolean hasInvite(UUID uuid) {
        return invites.contains(uuid);
    }

    public void addInvite(UUID uuid) {
        this.invites.add(uuid);
    }

    public void removeInvite(UUID uuid) {
        this.invites.remove(uuid);
    }

    public boolean isAllied(String tag) {
        return allyGuilds.contains(tag);
    }

    public boolean needInfoMove() {
        return (System.currentTimeMillis() - lastMoveInformation) > GuildSettings.GUILD_MOVE_INFO_COOLDOWN;
    }

    public void updateTag() {
        for (UUID uuid : getMembers().keySet()) {
            Users.get(uuid).updateTag();
        }
    }

}
