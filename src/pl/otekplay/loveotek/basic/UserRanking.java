package pl.otekplay.loveotek.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.otekplay.loveotek.main.Rankings;
import pl.otekplay.loveotek.main.Users;

import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
public class UserRanking implements Ranking {
    private final UUID uniqueID;
    private int points;
    private int kills;
    private int deaths;
    private int assists;


    public User getUser(){
        return Users.get(uniqueID);
    }

    @Override
    public int getPlace() {
        return Rankings.place(this);
    }
}
