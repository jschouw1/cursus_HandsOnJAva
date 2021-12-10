package com.hoja.blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomTest {
    Random sut;

    @BeforeEach
    public void beforeEach() {
        sut = new Random();
    }

    @Test
    public void nextInt_validInt_Int(){
        int result = sut.nextInt(52);
        System.out.println(result);
        assertThat(result, greaterThan(-1));
        assertThat(result, lessThan(52));
    }

    @Test
    public void nextInt_intHigherThan52_exception(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    int result = sut.nextInt(53);
                }
        );

        assertEquals("Bound must be smaller than or equal to decksize.", exception.getMessage());
    }

    @Test
    public void nextInt_intEquals0_exception(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    int result = sut.nextInt(0);
                }
        );

        assertEquals("bound must be positive", exception.getMessage());
    }
}
