package com.hoja.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    List<Hand> hands = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

}
