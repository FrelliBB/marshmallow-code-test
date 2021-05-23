package com.marshmallow.hiring.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.marshmallow.hiring.domain.Vector;
import com.marshmallow.hiring.exception.VectorDeserializationException;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class VectorConverters {

    public static final class ToArrayConverter extends StdConverter<Vector, int[]> {

        @Override
        public int[] convert(Vector value) {
            return new int[]{value.getX(), value.getY()};
        }
    }

    public static final class FromArrayConverter extends StdConverter<int[], Vector> {

        @Override
        public Vector convert(int[] value) {
            if (value.length == 2) {
                return Vector.of(value[0], value[1]);
            }

            throw new VectorDeserializationException(value);
        }
    }

}
