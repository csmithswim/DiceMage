package com.csmithswim;
import java.util.*;

public class DiceNecromancer {
    public List<Player> players;
    public Console console = new Console();
    public int round = 1;
    public boolean game = true;
    public boolean turn = true;
    public int currentPlayer;

    public DiceNecromancer() {
        players = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            players.add(new Player(console.getString("Enter Necromancer's Name")));
        }
        int activePlayer = 0;
//        int opposingPlayer
        while (game) {
            System.out.println(players.get(activePlayer).getName() + ", enter 'roll' to roll your energy dice.");
            Scanner scanner = new Scanner(System.in);
            try {
                String beginGame = scanner.nextLine();
                if (!beginGame.equals("roll")) {
                    continue;
                }

                players.get(activePlayer).energy.mana++;
                players.get(activePlayer).energy.energyRoll();

                System.out.println(players.get(activePlayer).getName() + "'s energy roll");
                players.get(activePlayer).displayEnergy();
                System.out.println(players.get(activePlayer).getName() + "'s Health: " + players.get(activePlayer).health + "\nMana: " + players.get(activePlayer).energy.mana + "\nEnergy " +
                        "Level: " + players.get(activePlayer).energy.size() +
                        "\nSkeleton Army: " + players.get(activePlayer).graveyard.displayArmy());


                //Power up, create monster, monster attack, skip turn.

                if (players.get(activePlayer).energy.mana >= 6) {
                    System.out.println("Enter 'raise dead' to spawn a skeleton warrior.");
                }
                if (players.get(activePlayer).energy.mana >= 3) {
                    System.out.println("...or 'power up' to add another die to your energy dice for 3 mana.");
                }
                if (players.get(activePlayer).graveyard.army.size() > 0) {
                    System.out.println("Enter 'attack' to attack");
                } else {
                    System.out.println("...or 'skip' to skip turn.");
                }

                do {
                    String input = scanner.nextLine();
                    if (input.equals("power up".trim().toLowerCase())) {
                        players.get(activePlayer).energy.powerUp();
                        break;
                    }
                    if (input.equals("raise dead".trim().toLowerCase())) {

                        do {
                            if (players.get(activePlayer).energy.mana < 6) {
                                break;
                            }
                            System.out.println("\n" + players.get(activePlayer).getName() + "'s mana: " + players.get(activePlayer).energy.mana);
                            players.get(activePlayer).graveyard.displaySkeletonChoices(players.get(activePlayer).energy.mana);
                            System.out.println("\nEnter which level of skeleton warrior you wish to create." + " You have " + players.get(activePlayer).energy.mana + " mana.");
                            int selection = scanner.nextInt();
                            if (players.get(activePlayer).energy.mana / selection >= 2) {
                                players.get(activePlayer).graveyard.createSkeleton(selection);
                                players.get(activePlayer).energy.mana = players.get(activePlayer).energy.mana - (selection * 2);
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

                if (players.get(activePlayer).health <= 0 || players.get(Math.abs(activePlayer - 1)).health <= 0) {
                    System.out.println(players.get(activePlayer).health <= 0 ? players.get(Math.abs(activePlayer - 1)).getName() + " wins!" :
                            players.get((activePlayer)).getName() + " wins!");
                    break;
                }

                activePlayer = Math.abs(activePlayer - 1);

            } catch (InputMismatchException e) {
                System.out.println("You can only enter 'roll' to roll energy dice!\n\n");
            }
        }
        System.out.println("\n \n \n");
    }

    public void calculateBattle() {
        int playerOneArmy = players.get(currentPlayer).graveyard.army.size();
        int playerTwoArmy = players.get(Math.abs(currentPlayer - 1)).graveyard.army.size();
        String play1Name = players.get(currentPlayer).name;
        String play2Name = players.get(Math.abs(currentPlayer - 1)).name;

        if (playerOneArmy >= 1 && playerTwoArmy >= 1) {
            while (playerOneArmy >= 0 || playerTwoArmy >= 0) {
                for (int i = 0, j = 0; i < playerOneArmy && j < playerTwoArmy; i++, j++) {
                    int playerOneAttackingSkeleton = (int) (players.get(currentPlayer).graveyard.army.get(i).level * Math.random() + 1);
                    int playerTwoAttackingSkeleton = (int) (players.get(Math.abs(currentPlayer - 1)).graveyard.army.get(j).level * Math.random() + 1);


                    if (playerOneAttackingSkeleton > playerTwoAttackingSkeleton) {
                        System.out.println(play1Name + "'s level " + players.get(currentPlayer).graveyard.army.get(i).level + " wins the battle and kills " + play2Name + "'s " +
                                "level " + players.get(Math.abs(currentPlayer - 1)).graveyard.army.get(j).level + " skeleton!!!");
                        players.get(Math.abs(currentPlayer - 1)).graveyard.army.remove(j);
                        playerTwoArmy--;
                        j--;
                    } else if (playerOneAttackingSkeleton < playerTwoAttackingSkeleton) {
                        System.out.println(play2Name + "'s level " + players.get(Math.abs(currentPlayer - 1)).graveyard.army.get(j).level + " wins and kills " + play1Name + "'s " +
                                "level " + players.get(currentPlayer).graveyard.army.get(i).level + " skeleton!!!");
                        players.get(currentPlayer).graveyard.army.remove(i);
                        playerOneArmy--;
                        i--;
                    } else {
                        System.out.println("Draw!");
                        System.out.println();
                        players.get(currentPlayer).graveyard.army.remove(i);
                        players.get(Math.abs(currentPlayer - 1)).graveyard.army.remove(j);
                        playerOneArmy--;
                        playerTwoArmy--;
                        i--;
                        j--;
                    }
                }

                if (playerOneArmy == 0 || playerTwoArmy == 0) {
                    System.out.println(playerOneArmy > playerTwoArmy ? play1Name + " won the battle!" : play2Name + " won the battle!");
                    break;
                }
            }
        } else {
            if (playerOneArmy == 0) {
                int damage=0;
                for (int i = 0; i  < playerTwoArmy; i++) {
                    damage  += (int) (players.get(Math.abs(currentPlayer - 1)).graveyard.army.get(i).level * Math.random() + 1);
                }
                players.get(currentPlayer).health = players.get(currentPlayer).health - damage;
                System.out.println(play2Name + " attacked " +  play1Name + " and dealt damage to them!");
                System.out.println(players.get(currentPlayer).health);
            }
            if (playerTwoArmy == 0) {
                int damage=0;
                for (int i = 0; i  < playerOneArmy; i++) {
                    damage  += (int) (players.get(currentPlayer).graveyard.army.get(i).level * Math.random() + 1);
                }
                players.get(Math.abs(currentPlayer-1)).health = players.get(Math.abs(currentPlayer-1)).health - damage;
                System.out.println(play1Name + " attacked " +  play2Name + " and dealt damage to them!");
                System.out.println(players.get(Math.abs(currentPlayer-1)).health);
            }
        }
    }
}

























