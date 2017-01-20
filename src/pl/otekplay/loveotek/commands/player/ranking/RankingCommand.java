package pl.otekplay.loveotek.commands.player.ranking;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.*;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Rankings;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;
import pl.otekplay.loveotek.storage.RankingSettings;

public class RankingCommand implements MainCommand {
    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("ranking", UserRank.PLAYER, "ranking [NICK]", "rank", "punkty");
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        User user;
        if(args.length == 0) {
            String name = args[0];
            if (!Users.is(name)) {
                Replacer.build(GlobalSettings.MESSAGE_PLAYER_NO_EXIST).add("%name%", name).send(player);
                return;
            }
            user = Users.get(name);
        }else{
            user = Users.get(player.getUniqueId());
        }
        Ranking ranking = Rankings.get(user.getUniqueID());
        Replacer.build(RankingSettings.MESSAGE_RANKING_USER.toArray(new String[RankingSettings.MESSAGE_RANKING_USER.size()]))
                .add("%name%",user.getName())
                .add("%points%",ranking.getPoints()+"")
                .add("%kills%",ranking.getKills()+"")
                .add("%deaths%",ranking.getDeaths()+"")
                .add("%assists%",ranking.getAssists()+"").send(player);
    }
}
