package pl.otekplay.loveotek.commands.guild.normal;

import org.bukkit.entity.Player;
import pl.otekplay.loveotek.api.commands.MainCommand;
import pl.otekplay.loveotek.basic.CommandInfo;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.commands.guild.normal.subs.*;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.storage.GuildSettings;

public class GuildCommand implements MainCommand {

    @Override
    public CommandInfo getDefaultInfo() {
        return new CommandInfo("gildie", UserRank.PLAYER, "/g", "g", "gildie", "guilds", "f", "factions")
                .sub("zaloz", new CreateArgCommand())
                .sub("wyjdz",new LeaveArgCommand())
                .sub("awansuj",new PromoteArgCommand())
                .sub("dolacz",new JoinArgCommand())
                .sub("zapros",new AddArgCommand())
                .sub("info",new InfoArgCommand())
                .sub("wyrzuc",new RemoveArgCommand())
                .sub("dom",new HomeArgCommand())
                .sub("lider",new LeaderArgCommand())
                .sub("wojna",new WarArgCommand())
                .sub("sojusz",new AllyArgCommand())
                .sub("pvp",new PvPArgCommand())
                .sub("ustawdom",new SetHomeArgCommand())
                .sub("rank",new RankArgCommand())
                .sub("powieksz",new EnlargeArgCommand())
                .sub("zamknij",new CloseArgCommand())
                .sub("anuluj",new CancelArgCommand())
                .sub("top",new TopArgCommand())
                .sub("degraduj",new DemoteArgCommand());
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        Replacer.build(GuildSettings.MESSAGE_GUILD_LIST_COMMANDS.toArray(new String[]{})).send(player);
    }
}
