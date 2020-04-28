package com.game.rockpaperscissors.enums;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.game.rockpaperscissors.enums.Result.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

class ResultTest {

    @Test
    void values() {
        assertThat(Result.values().length, equalTo(3));
    }

    @Test
    public void should_Result_enum_contain_WIN_LOSS_TIE() {
        assertThat(Arrays.asList(Result.values()), containsInAnyOrder(WIN, LOSS, TIE));
    }

    @Test
    public void should_Result_have_the_right_message() {
        assertThat(WIN.toString(), equalTo("You won!"));
        assertThat(LOSS.toString(), equalTo("You lost!"));
        assertThat(TIE.toString(), equalTo("It's a Tie"));

    }
}