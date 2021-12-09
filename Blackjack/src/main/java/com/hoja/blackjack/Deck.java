package com.hoja.blackjack;

import java.util.LinkedList;

public class Deck {
    LinkedList<Card> cards = new LinkedList<>();

    void shuffle() {
        cards.clear();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(suit, rank);
                cards.add(card);
            }
        }
    }

    public Card dealCard() {
        Card drawnCard = cards.poll();
        return drawnCard;
    }

}
