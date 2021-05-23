package com.marshmallow.hiring.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unused")
class VectorTest {

    private static List<Arguments> addVectors_arguments() {
        return List.of(
                Arguments.of(Vector.of(0, 0), Vector.of(0, 0), Vector.of(0, 0)),
                Arguments.of(Vector.of(0, 0), Vector.of(1, 0), Vector.of(1, 0)),
                Arguments.of(Vector.of(0, 0), Vector.of(0, 1), Vector.of(0, 1)),
                Arguments.of(Vector.of(0, 0), Vector.of(0, -1), Vector.of(0, -1)),
                Arguments.of(Vector.of(0, 0), Vector.of(-1, 0), Vector.of(-1, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("addVectors_arguments")
    void addVectors(Vector v1, Vector v2, Vector expectedSum) {
        assertThat(v1.add(v2)).isEqualTo(expectedSum);
    }

}