package com.game.rockpaperscissors.game;

import com.game.rockpaperscissors.enums.Move;
import com.game.rockpaperscissors.players.ComputerPlayer;
import com.game.rockpaperscissors.players.HumanPlayer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import static com.game.rockpaperscissors.enums.Move.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameTest {

    @Spy
    private HumanPlayer humanPlayer;
    @Spy
    private ComputerPlayer computerPlayer;
    @Mock
    private ConsoleReader consoleReader;
    private Game game;

    @BeforeEach
    void init() {
        game = new Game(humanPlayer, computerPlayer, consoleReader);
    }

    @Test
    void testplayRound() {
        //Given
        doReturn(ROCK).when(humanPlayer).getChoice();
        doReturn(PAPER).when(computerPlayer).getChoice();

        //when
        game.playRound();

        //then
        verify(computerPlayer).incrementScore();
        assertThat(computerPlayer.getScore(), equalTo(1));
        assertThat(humanPlayer.getScore(), equalTo(0));

    }

    @Test
    void testplayRound2() {
        //Given
        doReturn(SCISSORS).when(humanPlayer).getChoice();
        doReturn(PAPER).when(computerPlayer).getChoice();

        //when
        game.playRound();

        //then
        verify(humanPlayer).incrementScore();
        assertThat(computerPlayer.getScore(), equalTo(0));
        assertThat(humanPlayer.getScore(), equalTo(1));
    }


    @Test
    void should_numberOfRounds_be_3_humanPlayer_score_be_1_and_computerPlayer_score_be_1() {
        doReturn('Y', 'Y', 'N').when(consoleReader).readPlayerInput(anyString());
        doReturn(SCISSORS, ROCK, PAPER).when(humanPlayer).getChoice();
        doReturn(PAPER).when(computerPlayer).getChoice();

        game.play();
        assertThat(game.getNumberOfRounds(), equalTo(3));
        assertThat(humanPlayer.getScore(), equalTo(1));
        assertThat(computerPlayer.getScore(), equalTo(1));
    }

    @Test
    void should_numberOfRounds_be_3_humanPlayer_score_be_0_and_computerPlayer_score_be_0() {
        doReturn('Y', 'Y', 'N').when(consoleReader).readPlayerInput(anyString());
        doReturn(PAPER, ROCK, SCISSORS).when(humanPlayer).getChoice();
        doReturn(PAPER, ROCK, SCISSORS).when(computerPlayer).getChoice();

        game.play();
        assertThat(game.getNumberOfRounds(), equalTo(3));
        assertThat(humanPlayer.getScore(), equalTo(0));
        assertThat(computerPlayer.getScore(), equalTo(0));
    }
}
