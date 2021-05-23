package com.marshmallow.hiring.domain;

import com.marshmallow.hiring.exception.InvalidSeaAreaSizeException;
import lombok.ToString;

@ToString
public class SeaArea {

    private final Vector areaSize;

    SeaArea(Vector areaSize) {
        requireValidAreaSize(areaSize);
        this.areaSize = areaSize;
    }

    private static void requireValidAreaSize(Vector areaSize) {
        if (areaSize.getX() <= 0 || areaSize.getY() <= 0) {
            throw new InvalidSeaAreaSizeException(String.format("The given sea are dimensions [%s] are invalid. " +
                    "The dimensions must be at least 1 row and 1 column wide.", areaSize));
        }
    }

    public boolean isValidLocation(Vector location) {
        return isInDimensionRange(location.getX(), areaSize.getX()) &&
                isInDimensionRange(location.getY(), areaSize.getY());
    }

    private boolean isInDimensionRange(int value, int dimensionLength) {
        return value >= 0 && value < dimensionLength;
    }

}
