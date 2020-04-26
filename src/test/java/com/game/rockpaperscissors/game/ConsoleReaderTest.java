package com.game.rockpaperscissors.game;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ConsoleReaderTest {


    @Test
    public void should_get_first_letter_uppercase_from_input_string() {
        //Given
        String input = "seif sf";
        String inputMessage ="";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ConsoleReader consoleReader = new ConsoleReader();

        //When
        char readChar = consoleReader.readPlayerMove(inputMessage);
        //Then
        assertThat(readChar, equalTo('S'));
    }

    @Test
    public void should_numberOfRounds_be_equal_to_55() {
        //Given
        String input = "55";
        String inputMessage = "";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ConsoleReader consoleReader = new ConsoleReader();

        //When
        int numberOfRounds = consoleReader.readNumberOfRounds(inputMessage);
        //Then
        assertThat(numberOfRounds, equalTo(55));
    }
}