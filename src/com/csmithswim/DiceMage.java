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
                        "\nGolem level : " + players.get(currentPlayer).golemLevel + "\n\n");


                //Power up, create monster, monster attack, skip turn.

                if (players.get(currentPlayer).golemLevel > 0 ) {
                    System.out.println("Enter 'attack' to attack");
                }
                if (players.get(currentPlayer).energy.mana >= 6) {
                    System.out.println("Enter 'spawn golem' to to buy a level " + players.get(currentPlayer).energy.mana /3+  " golem for " + players.get(currentPlayer).energy.mana/3 +
                            " " +
                            "mana?");
                }
                if (players.get(currentPlayer).energy.mana >= 8) {
                    System.out.println("Enter 'power up' to power up.");
                }
                if (players.get(currentPlayer).energy.mana < 6) {
                    System.out.println("You do not have enough mana to do anything, type 'skip' to skip turn");
                }

                do {
                    String input = scanner.nextLine();
                    if (input.equals("power up".trim().toLowerCase())) {
                        players.get(currentPlayer).energy.powerUp();
                        break;
                    }
                    if (input.equals("spawn golem".trim().toLowerCase())) {
                        players.get(currentPlayer).createGolem(players.get(currentPlayer).energy.mana);
                        break;
                    }
                    if (input.equals("skip".trim().toLowerCase())) {
                        break;
                    }
                    if (input.equals("attack".trim().toLowerCase())) {
                        calculateVictor();
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




    public void calculateVictor() {

        int currentPlayersAttack = players.get(currentPlayer).golemAttack(players.get(currentPlayer).golemLevel);
        int opposingPlayersAttack = players.get(Math.abs(currentPlayer - 1)).golemAttack(players.get(Math.abs(currentPlayer - 1)).golemLevel);

        System.out.println(currentPlayersAttack > opposingPlayersAttack ?
                players.get(currentPlayer).getName() + " wins the battle and inflects " + currentPlayersAttack + " damage to " + players.get(Math.abs(currentPlayer - 1)).getName() :
                players.get(Math.abs(currentPlayer - 1)).getName() + " wins the battle and inflects " + currentPlayersAttack + " damage to " + players.get(currentPlayer).getName());

        if (currentPlayersAttack > opposingPlayersAttack) {
            players.get(Math.abs(currentPlayer - 1)).health = players.get(Math.abs(currentPlayer - 1)).health - currentPlayersAttack;
        } else if (currentPlayersAttack == opposingPlayersAttack) {

            System.out.println("Draw!");
        } else {
            players.get(currentPlayer).health = players.get(currentPlayer).health - currentPlayersAttack;
        }
    }
}

























