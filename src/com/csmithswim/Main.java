package com.csmithswim;

public class Main {
    //Monster is a single die limited by sides.

    //Every turn gets 1 mana
    //Generate mana, # of dice - 2
    //Create a monster, roll a single monster or multiple monsters


    public static void main(String[] args) {
        Deck playerOneDeck = new Deck();
        Deck playerTwoDeck = new Deck();

        playerOneDeck.roll();
        playerTwoDeck.roll();

        System.out.println(playerOneDeck.displayDeck());
        System.out.println(playerTwoDeck.displayDeck());

    }
}
