package com.hoja.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    public boolean bust = false;
    public int totalValue;
    List<Card> hand = new ArrayList<>();
    Deck deck = new Deck();

    void draw() {
        Card dealtCard = deck.dealCard();
        hand.add(dealtCard);
        System.out.printf("%nDealt: %s.%n", dealtCard.toString());
        promptCurrentHand();
        calculateTotalValue();
    }

    void promptCurrentHand() {
        System.out.println("Current Hand:");
        for (Card card : hand) {
            System.out.println(card.toString());
        }
    }

    void calculateTotalValue() {
        totalValue = 0;
        for (Card card : hand) {
            if (!card.isAce) {
                totalValue += card.value;
            }
        }
        for (Card card : hand) {
            if (card.isAce && (totalValue+11) < 21) {
                totalValue += card.value;
            } else if (card.isAce) {
                card.value = 1;
                totalValue += card.value;
            }
        }

        System.out.printf("Current Total: %s%n", String.valueOf(totalValue));
    }
}
