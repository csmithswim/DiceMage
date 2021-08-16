package com.csmithswim;
//list of golems with various levels

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graveyard {
    List<Skeleton> army = new ArrayList<>();

    public void createSkeleton(int level) {
        Skeleton skeleton = new Skeleton(level);
        System.out.println("You raised a level " + level + " skeleton warrior!");
        army.add(skeleton);

        //To sort skeletons in descending order
        for (int i = 0; i < army.size(); i++) {
            for (int j = 0; j < army.size(); j++) {

                if (army.get(i).level <= army.get(j).level) {
                    continue;
                } if (army.get(i).level >= army.get(j).level) {
                    Skeleton strongerSkeleton = army.get(i);
                    Skeleton weakerSkeleton = army.get(j);

                    army.set(i, weakerSkeleton);
                    army.set(j, strongerSkeleton);
                }
            }
        }
    }


    public String displayArmy() {
            String output = "";
            for (var skeleton : army) {
                output += " level " + skeleton.level + " |";
            }
           return output.trim()+"\n\n";
    }

    public void displaySkeletonChoices(int mana) {
        System.out.println("\n");
            for (int i = 6; i <= mana; i++) {
                System.out.println("Level          Mana Cost          Attack Range");
                System.out.println(" " + i/2 + "               " + i + "                 1-" + i);
                i++;
            }
    }
}

