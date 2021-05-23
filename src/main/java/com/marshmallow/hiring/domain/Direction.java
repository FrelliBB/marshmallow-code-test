package com.marshmallow.hiring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@RequiredArgsConstructor
public enum Direction {

    N(Vector.of(0, 1)),
    E(Vector.of(1, 0)),
    S(Vector.of(0, -1)),
    W(Vector.of(-1, 0));

    private final Vector vector;

    public static List<Direction> parseNavigationInstructions(String navigationInstructions) {
        return navigationInstructions.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .map(Direction::valueOf)
                .collect(toList());
    }
}
