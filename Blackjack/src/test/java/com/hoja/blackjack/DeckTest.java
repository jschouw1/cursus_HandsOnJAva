package com.hoja.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeckTest {
    Deck sut;

    @BeforeEach
    public void beforeEach() {
        sut = new Deck();
    }

    @Test
    public void shuffle_noCardStart_52CardEnd() {
        sut.shuffle();
        assertThat(sut.cards.size(), equalTo(52));
    }

    @Test
    public void shuffle_startWithOneCard_52CardEnd() {
        sut.cards.add(new Card(Suit.SPADES, Rank.ACE));
        sut.shuffle();
        assertThat(sut.cards.size(), equalTo(52));
    }

    @Test
    public void dealCard_emptyDeck_null() {
        Card dealtCard = sut.dealCard();
        assertThat(dealtCard, nullValue());
    }

    @Test
    public void dealCard_52cardDeck_51CardDeck() {
        sut.shuffle();
        Card drawnCard = sut.dealCard();
        assertThat(sut.cards.size(), equalTo(51));
        assertThat(drawnCard.suit, equalTo(Suit.HEARTS));
        assertThat(drawnCard.rank, equalTo(Rank.ACE));
    }
}
