package com.game.rockpaperscissors.enums;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ResultTest {

    @Test
    void values() {
        assertThat(Result.values().length , equalTo(3));
    }
}