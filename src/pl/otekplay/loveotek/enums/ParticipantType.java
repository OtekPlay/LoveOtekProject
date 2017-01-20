package pl.otekplay.loveotek.enums;

import pl.otekplay.loveotek.basic.UserRanking;
import pl.otekplay.loveotek.storage.RankingSettings;

public enum ParticipantType {
    DEATH,
    KILLER,
    ASSISTANT;


    public String getString(){
        switch (this){
            case DEATH:return RankingSettings.MESSAGE_PART_DEATH;
            case ASSISTANT:return RankingSettings.MESSAGE_PART_ASSIST;
            case KILLER:return RankingSettings.MESSAGE_PART_KILLER;
        }
        return "";
    }

    public void update(UserRanking ranking, int points){
        ranking.setPoints(ranking.getPoints()+points);
        switch (this){
            case DEATH:ranking.setDeaths(ranking.getDeaths()+1);return;
            case KILLER:ranking.setKills(ranking.getKills()+1);return;
            case ASSISTANT:ranking.setAssists(ranking.getAssists()+1);return;
        }
        return;
    }
}
