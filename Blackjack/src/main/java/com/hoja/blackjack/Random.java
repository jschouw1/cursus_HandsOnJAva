package com.hoja.blackjack;

public class Random {
    private final java.util.Random innerRandom = new java.util.Random();

    public int nextInt(int bound) {
        if (bound > 52) {
            throw new IllegalArgumentException("Bound must be smaller than or equal to decksize.");
        }

        return innerRandom.nextInt(bound);
    }

}
