package com.hoja.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HandTest {
    Hand sut;

    @BeforeEach
    public void beforeEach() {
        sut = new Hand();
        sut.deck.shuffle();
    }

    @Test
    public void draw_drawCard_handSizeOne() {
        sut.draw();
        assertThat(sut.hand.size(), equalTo(1));
    }
}
