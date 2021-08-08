package com.csmithswim;

public class Player {
    //Declarations of deck and player's name
    public Energy energy;
    public String name;
    public int health;
    Graveyard graveyard = new Graveyard();
    Console console = new Console();

    //Constructor
    public Player(String name) {
        this.name = name;
        this.health = 21;
        energy = new Energy(6);
        this.graveyard = graveyard;
    }

    //Shuffle/roll the dice/deck
    public void roll() { energy.roll(); }

    //monster attack
    public int attack(int level) {
       int attack = (int) ((Math.random() * level + 1));
       return attack;
    }

    //Display the deck/dice
    public void displayEnergy() { energy.peek(); }

    //display army
    public void displayArmy() { graveyard.displayArmy(); }

    //Display mana
    public void displayMana() { energy.peekMana(); }

    public boolean getDecision() {
        return console.getYN("1", "2", "Do you want to begin round? Type and enter 1 if you do, 2 for no");
    }

    public String getName() {return name;}

}
