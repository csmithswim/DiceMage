package com.csmithswim;

import java.util.Random;

public class Die {
    public int sides=6;
    public int value=1;

    public Die() {
        sides = 6;
        value = 1;
    }

    public void roll(Random rand){
        value = rand.nextInt(sides) +1;
    }

    public int getValue(){
        return value;
    }

}
