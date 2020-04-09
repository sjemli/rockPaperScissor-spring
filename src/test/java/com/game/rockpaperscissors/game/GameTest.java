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
        doReturn(Move.ROCK).when(humanPlayer).getChoice();
        doReturn(Move.PAPER).when(computerPlayer).getChoice();

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
        doReturn(Move.SCISSORS).when(humanPlayer).getChoice();
        doReturn(Move.PAPER).when(computerPlayer).getChoice();

        //when
        game.playRound();

        //then
        verify(humanPlayer).incrementScore();
        assertThat(computerPlayer.getScore(), equalTo(0));
        assertThat(humanPlayer.getScore(), equalTo(1));

    }
}
