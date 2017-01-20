package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.otekplay.loveotek.enums.DepositType;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class Deposit {
    private final UUID uniqueID;
    private int pearls = 0;
    private int golden = 0;
    private int kox = 0;


    public void take(DepositType type){
        switch (type){
            case KOX:this.setKox(getKox()-1);break;
            case PEARL:this.setPearls(getPearls()-1);break;
            case GOLDEN:this.setGolden(getGolden()-1);break;
        }
    }
    public boolean canTake(DepositType type){
        switch (type){
            case KOX:return getKox() != 0;
            case PEARL:return getPearls() != 0;
            case GOLDEN:return getGolden() != 0;
        }
        return false;
    }

}
