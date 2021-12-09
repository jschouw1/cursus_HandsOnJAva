package com.hoja.blackjack;

public class Card {
    protected Suit suit;
    protected Rank rank;
    protected int value;
    protected boolean isAce = false;

    protected Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = rank.value();
        this.isAce = (rank == Rank.ACE);
    }

    @Override
    public String toString() {
        return String.format("%s of %s", rank, suit);
    }
}
