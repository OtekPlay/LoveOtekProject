package pl.otekplay.loveotek.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GuildRank {
    MEMBER(0),
    OFFICER(1),
    LEADER(2);
    private final int priority;
}
