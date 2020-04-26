package com.game.rockpaperscissors.game;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleReader {

    private final Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    public char readPlayerMove(String inputMessage) {
        char inputChar = 0;
        while (inputChar == 0) {
            System.out.println(inputMessage);
            String nextLine = scanner.nextLine();
            if (!nextLine.isEmpty()) {
                inputChar = nextLine.toUpperCase().charAt(0);
            }
        }
        return inputChar;
    }

    public int readNumberOfRounds(String message) {

        System.out.println(message);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println(message);
        }
        int numberOfRounds = scanner.nextInt();
        if (scanner.hasNextLine()) scanner.nextLine();
        return numberOfRounds;
    }
}