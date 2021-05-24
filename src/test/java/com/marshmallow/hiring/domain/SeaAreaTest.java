package com.marshmallow.hiring.domain;

import com.marshmallow.hiring.exception.InvalidSeaAreaSizeException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static com.marshmallow.hiring.domain.Vector.vector;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("unused")
class SeaAreaTest {

    private static List<Arguments> invalidAreaSizes_arguments() {
        return List.of(
                Arguments.of(vector(0, 0)),
                Arguments.of(vector(0, 1)),
                Arguments.of(vector(1, 0))
        );
    }

    private static List<Arguments> isValidLocation_arguments() {
        return List.of(
                Arguments.of(vector(5, 10), vector(0, 0), true),
                Arguments.of(vector(5, 10), vector(4, 9), true),
                Arguments.of(vector(5, 10), vector(4, 0), true),
                Arguments.of(vector(5, 10), vector(0, 9), true),
                Arguments.of(vector(5, 10), vector(-1, 0), false),
                Arguments.of(vector(5, 10), vector(0, -1), false),
                Arguments.of(vector(5, 10), vector(5, 9), false),
                Arguments.of(vector(5, 10), vector(4, 10), false)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidAreaSizes_arguments")
    public void invalidAreaSizes_throwsInvalidSeaAreaSizeException(Vector location) {
        assertThatThrownBy(() -> new SeaArea(location))
                .isInstanceOf(InvalidSeaAreaSizeException.class);
    }

    @ParameterizedTest
    @MethodSource("isValidLocation_arguments")
    public void isValidLocation_isValidIfWithinSeaAreSize(Vector areaSize, Vector location, boolean isValid) {
        SeaArea seaArea = new SeaArea(areaSize);
        assertThat(seaArea.isValidLocation(location)).isEqualTo(isValid);
    }

}
