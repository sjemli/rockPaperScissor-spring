package com.game.rockpaperscissors.enums;

public enum Result {
    WIN("You won!"), LOSS("You lost!"), TIE("It's a Tie");

    private final String message;

    Result(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
