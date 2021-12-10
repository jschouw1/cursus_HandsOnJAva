package com.hoja.blackjack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Casino {
    static Inquirer inquirer = new Inquirer();
    static List<String> answers = new ArrayList<>();
    static Deck deck;
    static int turn;
    static int numberActiveHands = 0;
    static boolean active = true;

    public static void playBlackjack() {
        List<Player> players = new LinkedList<>();
        //Initialize game
        answers.clear();
        int numberOfPlayers = Integer.parseInt(inquirer.askOpen("How many players"));
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = inquirer.askOpen(String.format("Player %s,what is your name?", i+1));
            players.add(new Player(name));
            System.out.printf("Welcome %s%n", name);
        }
        while (active){
            turn = -1;
            if (deck == null){
                deck = new Deck();
            }
            deck.shuffle();

            //Give players their hand
            for (Player player : players) {
                player.hands.add(new Hand());
                numberActiveHands++;
            }

            //Deal in players
            while (turn < 1) {
                for (Player player : players) {
                    for (Hand hand : player.hands){
                        hand.draw(deck.dealCard());
                    }
                }
                turn++;
            }

            //Determine Natural hands
            for (Player player : players) {
                for (Hand hand : player.hands) {
                    hand.determineNatural();
                }
            }

            //While game is active
            while (numberActiveHands > 0) {
                for (Player player : players) {
                    for (Hand hand : player.hands) {
                        if (hand.active && !hand.natural && !hand.bust && !hand.stand){
                            System.out.printf("%n%n%n%nCurrent player: %s%n", player.name);
                            hand.promptCurrentHand();
                            String action = inquirer.ask(String.format("%s, what is your next action?", player.name), List.of("Hit", "Stand"));
                            switch (action) {
                                case "Hit":
                                    hand.draw(deck.dealCard());
                                    break;
                                case "Stand":
                                    hand.stand = true;
                                    System.out.printf("%s, your final total is: %s.%n", player.name, hand.totalValue);
                                    break;
                            }
                        } else if (hand.active) {
                            hand.active = false;
                            numberActiveHands--;
                        }
                    }
                }
            }
            answers.clear();
            answers.add("Yes");
            answers.add("No");
            String action = inquirer.ask("Want to play again?", answers);
            switch (action) {
                case "Yes":
                    break;
                case "No":
                    System.out.printf("Thank you for playing, goodbye.%n");
                    active = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Casino.");
        answers.clear();
        answers.add("Yes");
        answers.add("No");
        String response = inquirer.ask("New game of Blackjack?", answers);
        switch (response) {
            case "No":
                System.out.println("Then what are you doing here?!");
                System.exit(0);
            case "Yes":
                playBlackjack();
                break;
        }

    }
}
