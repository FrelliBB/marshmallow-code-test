package com.marshmallow.hiring.exception;

import java.util.Arrays;

public class VectorDeserializationException extends RuntimeException {
    public VectorDeserializationException(int[] value) {
        super("Cannot deserialize Vector from array " + Arrays.toString(value) + ". Valid vectors must only contain two " +
                "integer values");
    }
}
