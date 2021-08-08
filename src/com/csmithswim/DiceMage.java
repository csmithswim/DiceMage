package com.csmithswim;
import java.util.*;

//Monster is a single die limited by sides.
//Dice represent 'power', you can increase your power

//Every turn gets 1 mana
//Generate mana, # of dice - 2
//Create a monster, roll a single monster or multiple monsters


/*
TODO
[] implement first part of runGame method to add both players, initialize their names/health/mana/energy and start game and end game when player's health is == 0
[] implement runRound method that facilitates battle that takes account of monsters, health, mana and energy
[] implement runTown method that allows players to level up their energy levels, buy monsters, attack
 */
public class DiceMage {
    public List<Player> players;
    public Console console = new Console();
    public int round = 1;
    public boolean game = true;
    public boolean turn = true;
    public int currentPlayer;

    public DiceMage() {
        players = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            players.add(new Player(console.getString("Enter Wizard's Name")));
        }
        int currentPlayer = 0;
        while (game) {
            System.out.println(players.get(currentPlayer).getName() + ", enter 'roll' to roll");
            Scanner scanner = new Scanner(System.in);
            try {
                String beginGame = scanner.nextLine();
                if (!beginGame.equals("roll")) {
                    continue;
                }

        players.get(currentPlayer).energy.mana++;
        players.get(currentPlayer).energy.energyRoll();

                System.out.println(players.get(currentPlayer).getName() + "'s energy roll");
                players.get(currentPlayer).displayEnergy();
                System.out.println(players.get(currentPlayer).getName() + "'s Health: " + players.get(currentPlayer).health + "\nMana: " + players.get(currentPlayer).energy.mana + "\nEnergy " +
                        "Level: " +  players.get(currentPlayer).energy.size() +
                        "\nSkeleton army size : " + players.get(currentPlayer).graveyard.army.size() + "\n\n");


                //Power up, create monster, monster attack, skip turn.

                if (players.get(currentPlayer).energy.mana >= 6) {
                    System.out.println("Enter 'raise dead' to spawn a skeleton warrior");
                }
                if (players.get(currentPlayer).energy.mana >= 8) {
                    System.out.println("Enter 'power up' to power up.");
                }
                if (players.get(currentPlayer).energy.mana < 6) {
                    System.out.println("You do not have enough mana to do anything, type 'skip' to skip turn");
                }
                if (players.get(currentPlayer).graveyard.army.size() > 0 ) {
                    System.out.println("Enter 'attack' to attack");
                } else {
                    System.out.println("Enter 'skip' to skip turn");
                }

                do {
                    String input = scanner.nextLine();
                    if (input.equals("power up".trim().toLowerCase())) {
                        players.get(currentPlayer).energy.powerUp();
                        break;
                    }
                    if (input.equals("raise dead".trim().toLowerCase())) {

                    do {
                        System.out.println("\nEnter which level of skeleton warrior you wish to create. Type 2 to end selection.");
                        players.get(currentPlayer).graveyard.displaySkeletonChoices(players.get(currentPlayer).energy.mana);
                        int selection = scanner.nextInt();
                        if (selection == 2) {
                            break;
                        }
                        if (players.get(currentPlayer).energy.mana / selection >=  2) {
                        players.get(currentPlayer).graveyard.createSkeleton(selection);
                        players.get(currentPlayer).energy.mana = players.get(currentPlayer).energy.mana - (selection * 2);
                        } else {
                            System.out.println("Sorry you do not have enough mana for that skeleton, try another selection.");

                        }

                    } while (true);
                    break;
                    }

                    if (input.equals("skip".trim().toLowerCase())) {
                        break;
                    }
                    if (input.equals("attack".trim().toLowerCase())) {
                        calculateBattle();
                        break;
                    }
                } while (true);

                currentPlayer = Math.abs(currentPlayer - 1);

                if (players.get(currentPlayer).health <= 0 || players.get(Math.abs(currentPlayer-1)).health <= 0) {
                    System.out.println(players.get(currentPlayer).health <= 0 ? players.get(Math.abs(currentPlayer-1)).getName() + " wins!" :
                            players.get((currentPlayer)).getName() + " wins!");
                    break;
                }
                continue;
            } catch (InputMismatchException e) {
                System.out.println("You can only enter 'roll' to roll energy dice!\n\n");
            }
        }
            System.out.println("\n \n \n");
    }



    //
    public void calculateBattle() {
        for(int i = 0 ; i < players.get(currentPlayer).graveyard.army.size() ; i++){


            Skeleton skeleton = (Skeleton) players.get(currentPlayer).graveyard.army.get(i);
            System.out.println(skeleton.level);
        }

        for(int i = 0 ; i < players.get(Math.abs(currentPlayer-1)).graveyard.army.size() ; i++){
            Skeleton skeleton = (Skeleton) players.get(Math.abs(currentPlayer-1)).graveyard.army.get(i);
            System.out.println(skeleton.level);
        }

    }
}


























