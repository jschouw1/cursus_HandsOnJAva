// Broken by progress day5 => NEEDS FIXING
//package com.hoja.blackjack;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.LinkedList;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;
//
//public class HandTest {
//    Hand sut;
//    DeckMock deck;
//
//    @BeforeEach
//    public void beforeEach() {
//        var cards = new LinkedList<Card>();
//        deck = new DeckMock(cards);
//        sut = new Hand(deck, "Mock");
//    }
//
//    @Test
//    public void draw_drawCard_handSizeOne() {
//        deck.cards.add(new Card(Suit.SPADES, Rank.ACE));
//        sut.draw();
//        assertThat(sut.hand.size(), equalTo(1));
//    }
//
//    @Test
//    public void draw_drawUntilBust_bustEqualsTrue() {
//        deck.cards.add(new Card(Suit.SPADES, Rank.ACE));
//        deck.cards.add(new Card(Suit.SPADES, Rank.NINE));
//        deck.cards.add(new Card(Suit.SPADES, Rank.KING));
//        deck.cards.add(new Card(Suit.HEARTS, Rank.KING));
//        sut.draw();
//        sut.draw();
//        sut.draw();
//        sut.draw();
//        assertThat(sut.bust, equalTo(true));
//    }
//
//    @Test
//    public void draw_drawDoubleAce_totalValueEqualsTwelve() {
//        deck.cards.add(new Card(Suit.SPADES, Rank.ACE));
//        deck.cards.add(new Card(Suit.CLUBS, Rank.ACE));
//        sut.draw();
//        sut.draw();
//        assertThat(sut.totalValue, equalTo(12));
//    }
//
//    @Test
//    public void draw_drawAceKing_resultsInNatural() {
//        deck.cards.add(new Card(Suit.SPADES, Rank.ACE));
//        deck.cards.add(new Card(Suit.CLUBS, Rank.KING));
//        sut.draw();
//        sut.draw();
//        assertThat(sut.natural, equalTo(true));
//    }
//
//    class DeckMock extends Deck {
//        private LinkedList<Card> cards = new LinkedList<>();
//
//        DeckMock(LinkedList<Card> cards) {
//            this.cards = cards;
//        }
//
//        @Override
//        public Card dealCard() {
//            return this.cards.poll();
//        }
//
//        @Override
//        public void shuffle() {
//            //Voor test geen shuffle
//        }
//    }
//}
