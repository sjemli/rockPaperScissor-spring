package com.game.rockpaperscissors.game;


import com.game.rockpaperscissors.enums.Move;
import com.game.rockpaperscissors.enums.Result;
import com.game.rockpaperscissors.players.Player;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

import static com.game.rockpaperscissors.enums.Result.*;

@Component
public class Game {

    private static final String MAXIMUM_NUMBER_OF_ROUNDS_MESSAGE = "**** How many rounds do you want to play? : ***";

    private final Player humanPlayer;
    private final Player computerPlayer;
    private final ConsoleReader consoleReader;
    private int maximumNumberOfRounds;

    public Game(Player humanPlayer, Player computerPlayer, ConsoleReader consoleReader) {
        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
        this.consoleReader = consoleReader;
        maximumNumberOfRounds = 0;
    }

    void playRound(int roundNumber) {
        Move playerMove = humanPlayer.getChoice();
        Move computerMove = computerPlayer.getChoice();
        Result result = getRoundResult(playerMove, computerMove);
        updateWinnerScore(result);
        System.out.printf("Round Number = %s , You chose %s , Computer chose %s %n %s %n", roundNumber, playerMove, computerMove, result);
    }

    private void updateWinnerScore(Result result) {
        if (result == WIN) {
            humanPlayer.incrementScore();
        } else if (result == LOSS) {
            computerPlayer.incrementScore();
        }
    }

    private Result getRoundResult(Move playerMove, Move computerMove) {
        if (computerMove == playerMove) return TIE;
        else if (playerMove.beats(computerMove)) return WIN;
        else return LOSS;
    }

    public void play() {
        maximumNumberOfRounds = consoleReader.readNumberOfRounds(MAXIMUM_NUMBER_OF_ROUNDS_MESSAGE);
        System.out.printf("You will play %s rounds %n", maximumNumberOfRounds);

        IntStream.rangeClosed(1, maximumNumberOfRounds).forEach(this::playRound);

        System.out.printf("Final Score :%n Player ==> %s  ,Computer ==> %s , after %s rounds", humanPlayer.getScore(),
                computerPlayer.getScore(), maximumNumberOfRounds);
    }

    int getMaximumNumberOfRounds() {
        return maximumNumberOfRounds;
    }

}
