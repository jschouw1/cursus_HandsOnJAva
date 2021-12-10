package com.hoja.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public boolean bust = false;
    public boolean natural = false;
    public boolean stand = false;
    public boolean active = true;
    int totalValue;
    List<Card> hand = new ArrayList<>();

    void draw(Card dealtCard) {
        hand.add(dealtCard);
        System.out.printf("%nDealt: %s.%n", dealtCard.toString());
        calculateTotalValue();
    }

    void promptCurrentHand() {
        System.out.println("Current Hand:");
        for (Card card : hand) {
            System.out.println(card.toString());
        }
        System.out.printf("Current Total: %s%n", totalValue);
    }

    void calculateTotalValue() {
        totalValue = 0;
        for (Card card : hand) {
            if (!card.isAce) {
                totalValue += card.value;
            }
        }
        for (Card card : hand) {
            if (card.isAce) {
                if ((totalValue+11) > 21) {
                    card.value = 1;
                }
                totalValue += card.value;
            }
        }

        System.out.printf("New Total: %s%n", totalValue);

        if (totalValue > 21) {
            bust = true;
            System.out.printf("BUST%n");
        }
    }

    void determineNatural() {
        if (totalValue == 21) {
            System.out.printf("NATURAL!%n");
            natural = true;
        }
    }
}
