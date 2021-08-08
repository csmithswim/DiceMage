package com.csmithswim;
//list of golems with various levels

import java.util.ArrayList;
import java.util.List;

public class Graveyard {

    List<Skeleton> army = new ArrayList<>();

    public void createSkeleton(int level) {
        Skeleton skeleton = new Skeleton(level);
        army.add(skeleton);

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
        displayArmy();
    }



    public void displayArmy() {
            String output = "|";
            for (var skeleton : army) {
                output += skeleton.level + "|";
            }
            System.out.println(output.trim()+"\n");
    }

    public void displaySkeletonChoices(int mana) {
        for (int i = 6; i < mana; i++) {
            System.out.println("Level " + i/2 + " skeleton costs " + i + " mana and has an attack of 1-" + i);
            i++;
        }
    }





}

