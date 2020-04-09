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
        char readChar = consoleReader.readPlayerInput(inputMessage);
        //Then
        assertThat(readChar, equalTo('S'));
    }

}