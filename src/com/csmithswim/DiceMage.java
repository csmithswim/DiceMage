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

    public DiceMage() {
        players = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            players.add(new Player(console.getString("Enter Wizard's Name")));

        }
        int playNum = 0;
        while (game) {
            System.out.println(players.get(playNum).getName() + ", type 1 to roll energy dice.");
            Scanner scanner = new Scanner(System.in);
            try {
                int beginGame = scanner.nextInt();
                if (beginGame != 1) {
                    continue;
                }
                if (playNum == 0) {
                    players.get(playNum).energy.mana++;
                    players.get(playNum).energy.energyRoll();
                    System.out.println(players.get(playNum).getName() + "'s energy roll");
                    players.get(playNum).displayEnergy();
                    System.out.println(players.get(playNum).getName() + "'s Health: 5\n" + "Mana: " + players.get(playNum).energy.mana + "\nEnergy Level: " +  players.get(playNum).energy.size() +
                            "\nMonsters: \n\n");
                    if (players.get(playNum).energy.mana >= 8) {
                            System.out.println("Do you want to power up for 8 mana? Enter \"y\" if so.");
                        do {
                            String input = scanner.nextLine();
                            if (input.equals("y")) {
                                players.get(playNum).energy.powerUp();
                                break;
                            }
                        } while (true);

                    }
                    playNum = 1;
                } else {
                    players.get(playNum).energy.mana++;
                    players.get(playNum).energy.energyRoll();
                    System.out.println(players.get(playNum).getName() + "'s energy roll:   ");
                    players.get(playNum).displayEnergy();
                    System.out.println(players.get(playNum).getName() + "'s Health: 5\n" + "Mana: " + players.get(1).energy.mana + "\nEnergy Level: " +  players.get(playNum).energy.size() +
                            "\nMonsters: ");
                    if (players.get(playNum).energy.mana >= 8) {
                        System.out.println("Do you want to power up for 8 mana? Enter \"y\" if so.");
                        do {
                            String input = scanner.nextLine();
                            if (input.equals("y")) {
                                players.get(playNum).energy.powerUp();
                                break;
                            }
                        } while (true);

                    }
                    System.out.println("\n \n \n");
                    playNum = 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("You can only enter 1 to roll energy dice!\n\n");
            }
        }
            System.out.println("\n \n \n");
            round++;
    }


}













