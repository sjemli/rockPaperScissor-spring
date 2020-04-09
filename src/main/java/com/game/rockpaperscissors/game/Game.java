package com.game.rockpaperscissors.game;


import com.game.rockpaperscissors.enums.Move;
import com.game.rockpaperscissors.enums.Result;
import com.game.rockpaperscissors.players.Player;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.game.rockpaperscissors.enums.Result.*;

//https://codereview.stackexchange.com/questions/90501/rock-paper-scissors
@Component
public class Game {

    public static final String CONTINUE_GAME_MESSAGE = "**** Would you like to continue ? Y - N ***";
    private final Player humanPlayer;
    private final Player computerPlayer;
    private final ConsoleReader consoleReader;
    private int numberOfRounds;

    public Game(Player humanPlayer, Player computerPlayer, ConsoleReader consoleReader) {
        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
        this.consoleReader = consoleReader;
        numberOfRounds = 0;
    }

    public void playRound() {
        Move playerMove = humanPlayer.getChoice();
        Move computerMove = computerPlayer.getChoice();
        Result result = getRoundResult(playerMove, computerMove);
        updateWinnerScore(result);
        System.out.println(result);
        numberOfRounds++;
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


    private String getFinalResultMessage() {
        String finalResultMessage = "Player ==> " + humanPlayer.getScore() + " Computer ==> " + computerPlayer
                .getScore() + " after " + numberOfRounds + " rounds";
        return finalResultMessage;
    }

    public void play() {
        char keepPlaying = 'Y';
        while (keepPlaying == 'Y') {
            playRound();
            keepPlaying = consoleReader.readPlayerInput(CONTINUE_GAME_MESSAGE);
        }
        String finalResultMessage = getFinalResultMessage();
        System.out.println(finalResultMessage);
    }
}
