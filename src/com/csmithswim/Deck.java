package com.csmithswim;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Deck {
    //Figure out how to use treeMap

    List<Die> dice = new ArrayList<>();
    TreeMap<Integer, Integer> = new TreeMap<Integer, Integer>;

    public Deck() {
        //How many dice do players start with?

        while(dice.size() < 6) {
            dice.add(new Die());
        }

    }

    public void roll() {
        for (Die die : dice) {
            die.roll();
        }
    }

    public String displayDeck() {
        String output = "";
        for (Die die : dice) {
            output += die.faceUpValue + " ";
        }
        return output.trim();
    }

    public void createMonster() {

    }

}
