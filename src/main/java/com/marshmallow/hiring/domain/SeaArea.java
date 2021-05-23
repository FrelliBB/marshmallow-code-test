package com.marshmallow.hiring.domain;

import com.marshmallow.hiring.exception.InvalidSeaAreaSizeException;
import lombok.Value;

import static java.util.Objects.requireNonNull;

@Value
public class SeaArea {

    Vector areaSize;

    public SeaArea(Vector areaSize) {
        requireNonNull(areaSize, "areaSize must not be null");
        requireValidAreaSize(areaSize);

        this.areaSize = areaSize;
    }

    public boolean isValidLocation(Vector location) {
        return isInDimensionRange(location.getX(), areaSize.getX()) &&
                isInDimensionRange(location.getY(), areaSize.getY());
    }

    private boolean isInDimensionRange(int value, int dimensionLength) {
        return value >= 0 && value < dimensionLength;
    }

    private static void requireValidAreaSize(Vector areaSize) {
        if (areaSize.getX() <= 0 || areaSize.getY() <= 0) {
            throw new InvalidSeaAreaSizeException(String.format("The given sea are dimensions [%s] are invalid. " +
                    "The dimensions must be at least 1 row and 1 column wide.", areaSize));
        }
    }

}
