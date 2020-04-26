package com.game.rockpaperscissors.game;

import com.game.rockpaperscissors.enums.Move;
import com.game.rockpaperscissors.players.ComputerPlayer;
import com.game.rockpaperscissors.players.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.game.rockpaperscissors.enums.Move.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
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
    void should_only_computer_score_be_incremented_when_human_player_choice_is_Rock_and_computer_choice_is_Paper() {
        //Given
        doReturn(ROCK).when(humanPlayer).getChoice();
        doReturn(PAPER).when(computerPlayer).getChoice();
        int roundNumber = 1;

        //when
        game.playRound(roundNumber);

        //then
        verify(computerPlayer).incrementScore();
        assertThat(computerPlayer.getScore(), equalTo(1));
        assertThat(humanPlayer.getScore(), equalTo(0));
    }

    @Test
    void should_not_score_be_incremented_when_both_players_have_same_choice() {
        //Given
        Move rock = ROCK;
        doReturn(rock).when(humanPlayer).getChoice();
        doReturn(rock).when(computerPlayer).getChoice();
        int roundNumber = 1;

        //when
        game.playRound(roundNumber);

        //then
        verify(computerPlayer, never()).incrementScore();
        verify(humanPlayer, never()).incrementScore();
        assertThat(computerPlayer.getScore(), equalTo(0));
        assertThat(humanPlayer.getScore(), equalTo(0));
    }

    @Test
    void should_only_human_player_score_be_incremented_when_human_player_choice_is_Scissors_and_computer_choice_is_Paper() {
        //Given
        doReturn(SCISSORS).when(humanPlayer).getChoice();
        doReturn(PAPER).when(computerPlayer).getChoice();
        int roundNumber = 1;

        //when
        game.playRound(roundNumber);

        //then
        verify(humanPlayer).incrementScore();
        assertThat(computerPlayer.getScore(), equalTo(0));
        assertThat(humanPlayer.getScore(), equalTo(1));
    }


    @Test
    void should_maxNumberOfRounds_be_3_humanPlayer_score_be_1_and_computerPlayer_score_be_1() {
        //given
        doReturn(3).when(consoleReader).readNumberOfRounds(anyString());
        doReturn(SCISSORS, ROCK, PAPER).when(humanPlayer).getChoice();
        doReturn(PAPER).when(computerPlayer).getChoice();

        //when
        game.play();

        //then
        verify(humanPlayer, times(3)).getChoice();
        verify(computerPlayer, times(3)).getChoice();
        assertThat(game.getMaximumNumberOfRounds(), equalTo(3));
        assertThat(humanPlayer.getScore(), equalTo(1));
        assertThat(computerPlayer.getScore(), equalTo(1));
    }

    @Test
    void should_maxNumberOfRounds_be_3_humanPlayer_score_be_0_and_computerPlayer_score_be_0() {
        //given
        doReturn(4).when(consoleReader).readNumberOfRounds(anyString());
        doReturn(PAPER, ROCK, SCISSORS, PAPER).when(humanPlayer).getChoice();
        doReturn(PAPER, ROCK, SCISSORS, PAPER).when(computerPlayer).getChoice();

        //when
        game.play();

        //then
        verify(humanPlayer, times(4)).getChoice();
        verify(computerPlayer, times(4)).getChoice();
        assertThat(game.getMaximumNumberOfRounds(), equalTo(4));
        assertThat(humanPlayer.getScore(), equalTo(0));
        assertThat(computerPlayer.getScore(), equalTo(0));
    }

    @Test
    void should_maxNumberOfRounds_be_0_humanPlayer_score_be_0_and_computerPlayer_score_be_0() {
        //given
        doReturn(0).when(consoleReader).readNumberOfRounds(anyString());

        //when
        game.play();

        //then
        verify(humanPlayer, never()).getChoice();
        verify(computerPlayer, never()).getChoice();
        assertThat(game.getMaximumNumberOfRounds(), equalTo(0));
        assertThat(humanPlayer.getScore(), equalTo(0));
        assertThat(computerPlayer.getScore(), equalTo(0));
    }
}
