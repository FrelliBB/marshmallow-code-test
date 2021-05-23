package com.marshmallow.hiring.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.marshmallow.hiring.domain.Direction.*;

class DirectionTest {

    @Test
    public void parseNavigationInstructions() {
        List<Direction> directions = Direction.parseNavigationInstructions("NESWWESN");
        Assertions.assertThat(directions).containsExactly(N, E, S, W, W, E, S, N);
    }

}