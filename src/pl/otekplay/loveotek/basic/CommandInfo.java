package pl.otekplay.loveotek.basic;

import lombok.Getter;
import pl.otekplay.loveotek.api.commands.SubCommand;
import pl.otekplay.loveotek.enums.UserRank;

import java.util.HashMap;

@Getter
public class CommandInfo {
    private final String name;
    private final UserRank rank;
    private final String usage;
    private final String[] aliases;
    private final HashMap<String, SubCommand> subList = new HashMap<>();

    public CommandInfo(String name, UserRank rank, String usage, String... aliases) {
        this.name = name;
        this.rank = rank;
        this.usage = usage;
        this.aliases = aliases;
    }

    public CommandInfo sub(String string, SubCommand sub) {
        subList.put(string, sub);
        return this;
    }
}
