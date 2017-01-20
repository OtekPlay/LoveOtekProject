package pl.otekplay.loveotek.commands.guild.normal.subs;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.basic.GuildRanking;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.main.Rankings;
import pl.otekplay.loveotek.storage.GuildSettings;
import pl.otekplay.loveotek.storage.RankingSettings;

public class RankArgCommand implements SubCommand {
    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        String tag = args[0];
        if(!Guilds.isTag(tag)){
            Replacer.build(GuildSettings.MESSAGE_GUILD_NO_EXIST).add("%tag%",tag).send(player);
            return;
        }
        GuildRanking ranking = Rankings.get(tag);
        Replacer.build(RankingSettings.MESSAGE_RANKING_GUILD.toArray(new String[RankingSettings.MESSAGE_RANKING_GUILD.size()]))
                .add("%tag%",ranking.getTag())
                .add("%points%",ranking.getPoints()+"")
                .add("%kills%",ranking.getKills()+"")
                .add("%deaths%",ranking.getDeaths()+"")
                .add("%assists%",ranking.getAssists()+"").send(player);
    }
}
