package com.game.rockpaperscissors.players;

import com.game.rockpaperscissors.enums.Move;
import com.game.rockpaperscissors.game.ConsoleReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HumanPlayerTest {

    @Mock
    private ConsoleReader consoleReader;
    @InjectMocks
    private HumanPlayer humanPlayer;


    @Test
    public void should_return_SCISSOR_hen_input_is_s() {
        //Given
        when(consoleReader.readPlayerMove(anyString())).thenReturn('S');
        //when
        Move playerMove = humanPlayer.getChoice();
        //then
        assertThat(playerMove, equalTo(Move.SCISSORS));
    }

    @Test
    public void should_return_ROCK_hen_input_is_r() {
        //Given
        when(consoleReader.readPlayerMove(anyString())).thenReturn('R');
        //when
        Move playerMove = humanPlayer.getChoice();
        //then
        assertThat(playerMove, equalTo(Move.ROCK));
    }

    @Test
    public void should_return_PAPER_hen_input_is_p() {
        //Given
        when(consoleReader.readPlayerMove(anyString())).thenReturn('P');
        //when
        Move playerMove = humanPlayer.getChoice();
        //then
        assertThat(playerMove, equalTo(Move.PAPER));
    }

}