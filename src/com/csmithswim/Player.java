package com.csmithswim;

public class Player {
    //Declarations of deck and player's name
    public Energy energy;
    public String name;
    Console console = new Console();

    //Constructor
    public Player(String name) {
        this.name = name;
        energy = new Energy(6);
    }

    //Shuffle/roll the dice/deck
    public void roll() { energy.roll(); }

    //Display the deck/dice
    public void displayEnergy() { energy.peek(); }

    //Display mana
    public void displayMana() { energy.peekMana(); }

    public boolean getDecision() {
        return console.getYN("1", "2", "Do you want to begin round? Type and enter 1 if you do, 2 for no");
    }

    public String getName() {return name;}

}
