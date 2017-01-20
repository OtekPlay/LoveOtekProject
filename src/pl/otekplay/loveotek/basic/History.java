package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.otekplay.loveotek.enums.ParticipantType;
import pl.otekplay.loveotek.storage.RankingSettings;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class History {
    private final List<Participant> participants;
    private final long dateFight;

    public Participant getDeath() {
        return participants.stream().filter(participant -> participant.getParticipantType().equals(ParticipantType.DEATH)).findFirst().orElse(null);
    }

    public Participant getKiller() {
        return participants.stream().filter(participant -> participant.getParticipantType().equals(ParticipantType.KILLER)).findFirst().orElse(null);
    }

    public void broadcast() {
        Participant death = getDeath();
        Participant killer = getKiller();
        Replacer.build(RankingSettings.MESSAGE_FORMAT_DEATH).add("%name%", death.getUser().getName()).add("%points%", death.getDeservePoints() + "").add("%damage%", death.getDamage() + "").add("%percetage%", death.getPercetage() + "").broadcast();
        Replacer.build(RankingSettings.MESSAGE_FORMAT_KILLER).add("%name%", killer.getUser().getName()).add("%points%", killer.getDeservePoints() + "").add("%damage%", killer.getDamage() + "").add("%percetage%", killer.getPercetage() + "").broadcast();
        for (Participant pant : participants) {
            if (pant.getParticipantType() != ParticipantType.ASSISTANT) {
                continue;
            }
            Replacer.build(RankingSettings.MESSAGE_FORMAT_ASSIST).add("%name%", pant.getUser().getName()).add("%points%", pant.getDeservePoints() + "").add("%damage%", pant.getDamage() + "").add("%percetage%", pant.getPercetage() + "").broadcast();
        }
    }
}
