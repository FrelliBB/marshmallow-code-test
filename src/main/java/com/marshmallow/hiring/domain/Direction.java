package com.marshmallow.hiring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.marshmallow.hiring.domain.Vector.vector;
import static java.util.stream.Collectors.toList;

@Getter
@RequiredArgsConstructor
public enum Direction {

    N(vector(0, 1)),
    E(vector(1, 0)),
    S(vector(0, -1)),
    W(vector(-1, 0));

    private final Vector vector;

    public static List<Direction> parseNavigationInstructions(String navigationInstructions) {
        return navigationInstructions.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .map(Direction::valueOf)
                .collect(toList());
    }
}
