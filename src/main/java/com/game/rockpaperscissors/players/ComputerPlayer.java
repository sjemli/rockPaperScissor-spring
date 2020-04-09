package com.game.rockpaperscissors.players;


import com.game.rockpaperscissors.enums.Move;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ComputerPlayer extends Player {

    private final Random random;


    public ComputerPlayer() {
        this.random = new Random();
    }

    public ComputerPlayer(Random random) {
        this.random = random;
    }


    @Override
    public Move getChoice() {
        int randomMove = Math.abs(random.nextInt() % 3);
        return Move.values()[randomMove];
    }
}
