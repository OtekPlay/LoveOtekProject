package pl.otekplay.loveotek.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
@RequiredArgsConstructor
@Getter
public class Attacker {
    private final UUID uniqueID;
    private int damageDone;
    private long lastAttack;

    public void attack(int damage) {
        this.damageDone = this.damageDone + damage;
        this.lastAttack = System.currentTimeMillis();
    }



}
