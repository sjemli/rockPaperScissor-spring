package com.game.rockpaperscissors.players;

import com.game.rockpaperscissors.enums.Move;
import com.game.rockpaperscissors.game.ConsoleReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HumanPlayer extends Player {

    private static final String HUMAN_MOVE_INPUT_MESSAGE = "Enter move code : R => Rock, P => Paper, S => Scissors";
    private final ConsoleReader consoleReader;

    @Autowired
    public HumanPlayer(ConsoleReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public HumanPlayer() {
        this(new ConsoleReader());
    }

    @Override
    public Move getChoice() {

        Optional<Move> move = Optional.empty();
        while (!move.isPresent()) {
            move = Move.fromMnemonic(consoleReader.readPlayerMove(HUMAN_MOVE_INPUT_MESSAGE));
        }
        return move.get();
    }


}
