package com.game.rockpaperscissors.players;

import static com.game.rockpaperscissors.enums.Move.*;

import com.game.rockpaperscissors.enums.Move;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComputerPlayerTest {

    @Mock
    private Random randomMock;
    @InjectMocks
    private ComputerPlayer computerPlayer;


    private static Stream<Arguments> argumentsProvider() {
        return Stream.of(
                Arguments.of(35, SCISSORS),
                Arguments.of(91, PAPER),
                Arguments.of(6, ROCK)
        );
    }

    @ParameterizedTest(name = "should_choice_be_{1}_when_random_number_is_{0}")
    @MethodSource("argumentsProvider")
    public void should_computer_choice_depend_on_random_number_mod_3(int generatedNumber, Move expectedChoice) {
        //Given
        when(randomMock.nextInt()).thenReturn(generatedNumber);
        //When
        Move computerPlayerChoice = computerPlayer.getChoice();
        //Then
        assertThat(computerPlayerChoice, equalTo(expectedChoice));
        assertThat(computerPlayerChoice, equalTo(values()[generatedNumber % 3]));


    }

    @Test
    public void should_player_score_be_incremented() {
        assertThat(computerPlayer.getScore(), equalTo(0));
        computerPlayer.incrementScore();
        assertThat(computerPlayer.getScore(), equalTo(1));
    }

}