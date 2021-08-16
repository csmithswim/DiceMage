package com.csmithswim;
import java.util.*;

public class Energy {
    public List<Die> energy;
    int mana;

    public Energy(int diceNum) {
        energy = new ArrayList<>();
        mana = 9;
        for (int counter = 0; counter < diceNum; counter++) {
            energy.add(new Die());
        }
    }

    public int energyRoll(){
        Random rand = new Random();
        for (var die : energy) {
            die.roll(rand);
        }
        HashMap<Integer, Integer> energyMap = new HashMap<>();
        for (var die  : energy) {
            if (energyMap.containsKey(die.getValue())) {
                energyMap.put(die.getValue(), energyMap.get(die.getValue()) + 1);
                continue;
            }
            energyMap.put(die.getValue(), 1);
        }

        //iterate over energyMap to find any values that are >= 3, if >= then add mana.
        for (Map.Entry<Integer, Integer> entry : energyMap.entrySet()) {
            switch (entry.getValue()) {
                case 3: {
                    mana++;
                    System.out.println("You got 1 additional mana!");
                    break;
                }
                case 4: {
                    mana = mana + 2;
                    System.out.println("You got 2 additional mana!");
                    break;
                }
                case 5: {
                    mana = mana + 3;
                    System.out.println("You got 3 additional mana!");
                    break;
                }
                case 6: {
                    mana = mana + 4;
                    System.out.println("You got 4 additional mana!");
                    break;
                }
            }
        }
        return mana;
    }

    public void peekMana() {
        System.out.println(String.valueOf(mana));
    }

    public void powerUp() {
        energy.add(0,new Die());
        mana = mana - 3;
    }

    public void peek() {
        String output = "|";
        for (var die : energy) {
            output += die.getValue() + "|";
        }
        System.out.println(output.trim()+"\n");
    }

    public void roll() {
        Random rand = new Random();
        for (var die : energy) {
            die.roll(rand);
        }
    }

    public int size() {
        return energy.size();
    }

    public int countValue(int value) {
        int count = 0;
        for (var die : energy) {
            if (die.getValue() == value) count++;
        }
        return count;
    }
}
