package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class DropUser {

    private final UUID uniqueID;
    private List<Drop> disabledDrop = new ArrayList<>();
    private boolean cobble = true;
    private int level = 1;
    private int exp = 0;

    public void disableDrop(Drop drop) {
        disabledDrop.add(drop);
    }

    public void enableDrop(Drop drop) {
        disabledDrop.remove(drop);
    }

    public boolean isDisabledDrop(Drop drop) {
        return disabledDrop.contains(drop);
    }

    public boolean hasVip() {
        return Users.get(uniqueID).hasPermissions(UserRank.VIP);
    }

}
