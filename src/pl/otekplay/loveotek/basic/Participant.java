package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.otekplay.loveotek.enums.ParticipantType;
import pl.otekplay.loveotek.main.Rankings;
import pl.otekplay.loveotek.main.Users;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class Participant {
    private final UUID uniqueID;
    private final ParticipantType participantType;
    private final int damage, percetage, deservePoints;

    public User getUser(){
        return Users.get(uniqueID);
    }

    public UserRanking getRanking(){
        return Rankings.get(uniqueID);
    }
}
