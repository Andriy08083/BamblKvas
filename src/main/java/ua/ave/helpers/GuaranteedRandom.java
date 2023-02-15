package ua.ave.helpers;

import java.util.Random;

public class GuaranteedRandom extends Random {

    private final int chance;
    private int tries;
    private boolean gotZero;

    public GuaranteedRandom(int chance) {
        this.chance = chance;
    }

    @Override
    public int nextInt() {
        tries++;
        int num = super.nextInt(chance);
        if (num == 0) {
            if (!gotZero) {
                gotZero = true;
                return 0; // Always use 0 as guaranteed number
            }
        }
        if (tries >= chance) {
            tries = 0;
            if (!gotZero) {
                return 0; // Always use 0 as guaranteed number
            } else {
                gotZero = false;
            }
        }
        return 1;
    }
}
