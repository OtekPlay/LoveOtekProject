package pl.otekplay.loveotek.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.main.Rankings;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class GuildRanking implements Ranking {
    private final String tag;
    public Guild getGuild(){
        return Guilds.tag(tag);
    }

    @Override
    public int getPoints() {
        int points = 0;
        Guild guild = getGuild();
        for(UUID uuid:guild.getMembers().keySet()){
            points = points + Rankings.get(uuid).getPoints();
        }
        return points/guild.getMembers().size();
    }
    @Override
    public int getKills() {
        int kills = 0;
        Guild guild = getGuild();
        for(UUID uuid:guild.getMembers().keySet()){
            kills = kills + Rankings.get(uuid).getKills();
        }
        return kills/guild.getMembers().size();
    }

    @Override
    public int getDeaths() {
        int deaths = 0;
        Guild guild = getGuild();
        for(UUID uuid:guild.getMembers().keySet()){
            deaths = deaths + Rankings.get(uuid).getDeaths();
        }
        return deaths;
    }

    @Override
    public int getAssists() {
        int assists = 0;
        Guild guild = getGuild();
        for(UUID uuid:guild.getMembers().keySet()){
            assists = assists + Rankings.get(uuid).getAssists();
        }
        return assists;
    }

    @Override
    public int getPlace() {
        return Rankings.place(this);
    }
}
