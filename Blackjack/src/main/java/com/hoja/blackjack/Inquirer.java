package com.hoja.blackjack;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Inquirer {
    private boolean waitingForAnswer;
    private String response;

    String ask(String question, List<String> choices) {

        System.out.println(question);
        for (String choice : choices) {
            System.out.println(choice);
        }

        Scanner scanner = new Scanner(System.in);
        waitingForAnswer = true;
        while (waitingForAnswer) {
            response = scanner.nextLine();
            if (!choices.contains(response)) {
                System.out.printf("%s is an invalid response, try again.%n", response);
            } else {
                waitingForAnswer = false;
            }
        }
        return response;
    }

    String askOpen(String question) {
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        waitingForAnswer = true;
        while (waitingForAnswer) {
            response = scanner.nextLine();
            if (Objects.equals(response, "")) {
                System.out.println("Must type a name, try again.");
            } else {
                waitingForAnswer = false;
            }
        }
        return response;
    }
}
