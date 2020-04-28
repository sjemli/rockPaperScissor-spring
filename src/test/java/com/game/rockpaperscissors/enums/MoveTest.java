package com.game.rockpaperscissors.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static com.game.rockpaperscissors.enums.Move.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MoveTest {

    @Test
    public void should_enum_contain_three_values() {
        assertThat(values().length, equalTo(3));
    }

    @Test
    public void should_enum_contain_ROCK_PAPER_SCISSORS() {
        assertThat(Arrays.asList(values()), containsInAnyOrder(ROCK, SCISSORS, PAPER));
    }


    private static Stream<Arguments> movesProvider() {
        return Stream.of(
                Arguments.of(ROCK, SCISSORS),
                Arguments.of(SCISSORS, PAPER),
                Arguments.of(PAPER, ROCK)
        );
    }

    @ParameterizedTest(name = "should_{0}_beat_{1}")
    @MethodSource("movesProvider")
    void should_stronger_move_beat_the_weaker_move(Move strongerMove, Move weakerMove) {
        assertThat(strongerMove.beats(weakerMove), is(Boolean.TRUE));
    }

    @ParameterizedTest(name = "should_{1}_not_beat_{0}")
    @MethodSource("movesProvider")
    void should_weaker_move_not_beat_the_stronger_move(Move strongerMove, Move weakerMove) {
        assertThat(weakerMove.beats(strongerMove), is(Boolean.FALSE));
    }

    @ParameterizedTest(name = "should_{0}_not_beat_{0}")
    @EnumSource(value = Move.class, names =  {"ROCK", "PAPER", "SCISSORS"})
    void should_move_not_beat_the_same_move(Move move) {
        assertThat(move.beats(move), is(Boolean.FALSE));
    }


    private static Stream<Arguments> inputsProvider() {
        return Stream.of(
                Arguments.of('S', SCISSORS),
                Arguments.of('P', PAPER),
                Arguments.of('R', ROCK)
        );
    }

    @ParameterizedTest(name = "should_return_Optional_of_{1}_if_input_is_{0}")
    @MethodSource("inputsProvider")
    public void should_return_Optional_of_move_if_input_is_valid_mnemonic(Character inputChar, Move expectedMove) {

        //when
        Optional<Move> move = fromMnemonic(inputChar);
        //Then
        assertThat(move.isPresent(), equalTo(Boolean.TRUE));
        assertThat(move.get(), equalTo(expectedMove));
    }

    @Test
    public void should_return_empty_Optional_if_input_is_X() {
        //Given
        char input = 'X';
        //When
        Optional<Move> move = fromMnemonic(input);
        //Then
        assertThat(move.isPresent(), equalTo(Boolean.FALSE));
    }


}