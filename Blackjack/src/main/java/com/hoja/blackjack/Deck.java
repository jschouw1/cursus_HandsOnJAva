package com.hoja.blackjack;

import java.util.LinkedList;

public class Deck {
    LinkedList<Card> cards = new LinkedList<>();

    void shuffle() {
        LinkedList<Card> newCards = new LinkedList<>();
        Random random = new Random();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(suit, rank);
                newCards.add(card);
            }
        }
        cards.clear();
        int newDeckSize = newCards.size();
        for (int i=0; i<newDeckSize-1; i++) {
            int nextCard = random.nextInt(newCards.size());
            cards.add(newCards.remove(nextCard));
        }
        cards.add(newCards.get(0));
    }

    public Card dealCard() {
        return cards.poll();
    }

}
