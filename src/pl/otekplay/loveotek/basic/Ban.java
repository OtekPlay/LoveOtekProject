package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Ban {
    private final String nickname;
    private final String reason;
    private final long time;
    private final String banner;


    public boolean isBanned(){
        return time > System.currentTimeMillis();
    }
}
