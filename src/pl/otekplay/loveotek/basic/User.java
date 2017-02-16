package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Rankings;
import pl.otekplay.loveotek.utils.TagUtil;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class User {
    private final UUID uniqueID;
    private final String name;
    private UserRank rank = UserRank.PLAYER;
    private Guild guild;
    private String terrain = "";
    private UUID teleportRequest;
    private Location home;

    public boolean hasGuild() {
        return getGuild() != null;
    }

    public boolean hasPermissions(UserRank rank) {
        return getRank().can(rank);
    }

    public boolean isOnline() {
        return getPlayer() != null;
    }

    public boolean isStaff() {
        return hasPermissions(UserRank.HELPER);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uniqueID);
    }

    public UserRanking getRanking() {
        return Rankings.get(uniqueID);
    }

    public void sendMessage(String message) {
        getPlayer().sendMessage(message);
    }

    public void setRank(UserRank rank) {
        this.rank = rank;
        this.updateTag();
    }

    public void updateTag(){
        if(!isOnline()){
            return;
        }
        TagUtil.updateBoard(getPlayer());
    }



}
