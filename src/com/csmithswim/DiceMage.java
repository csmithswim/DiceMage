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
            System.out.println(players.get(currentPlayer).getName() + ", enter 'r' to roll");
            Scanner scanner = new Scanner(System.in);
            try {
                char beginGame = scanner.next().charAt(0);
                if (beginGame != 'r') {
                    continue;
                }

        players.get(currentPlayer).energy.mana++;
        players.get(currentPlayer).energy.energyRoll();

                System.out.println(players.get(currentPlayer).getName() + "'s energy roll");
                players.get(currentPlayer).displayEnergy();
                System.out.println(players.get(currentPlayer).getName() + "'s Health: " + players.get(currentPlayer).health + "\nMana: " + players.get(currentPlayer).energy.mana + "\nEnergy " +
                        "Level: " +  players.get(currentPlayer).energy.size() +
                        "\nGolem level : " + players.get(currentPlayer).golemLevel + "\n\n");

                if (players.get(currentPlayer).energy.mana >= 6) {
                    System.out.println("Enter 1 to to buy a level " + players.get(currentPlayer).energy.mana /3+  " golem for " + players.get(currentPlayer).energy.mana/3 + " " +
                            "mana?");
                }
                if (players.get(currentPlayer).energy.mana >= 8) {
                    System.out.println("Enter 2 to power up.");
                }
                if (players.get(currentPlayer).energy.mana < 6) {
                    System.out.println("You do not have enough mana to do anything, type 's' to skip turn");
                }

                do {
                    String input = scanner.nextLine();
                    if (input.equals("2")) {
                        players.get(currentPlayer).energy.powerUp();
                        break;
                    }
                    if (input.equals("1")) {
                        players.get(currentPlayer).createGolem(players.get(currentPlayer).energy.mana);
                        break;
                    }
                    if (input.equals("s")) {
                        break;
                    }
                    if (input.equals("3")) {
                        if (players.get(1).golemLevel == 0) {
                            System.out.println("Player " + players.get(currentPlayer).name + "'s level " + players.get(currentPlayer).golemLevel + "golem attacked " + players.get(0).name);
                            players.get(0).health = players.get(currentPlayer).golemAttack(players.get(currentPlayer).golemLevel) - players.get(1).health;
                            System.out.println(players.get(1).getName() + "lost " + players.get(currentPlayer).golemAttack(players.get(currentPlayer).golemLevel) + " health.");
                            break;
                        }
//                                players.get(currentPlayer).golemAttack(players.get(currentPlayer).golemLevel);
                    }
                } while (true);

                currentPlayer = Math.abs(currentPlayer - 1);
                continue;
            } catch (InputMismatchException e) {
                System.out.println("You can only enter 'roll' to roll energy dice!\n\n");
            }
        }
            System.out.println("\n \n \n");
    }

}




















