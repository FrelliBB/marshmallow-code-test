package com.marshmallow.hiring.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.marshmallow.hiring.exception.VectorDeserializationException;
import lombok.Value;

@Value(staticConstructor = "of")
@JsonSerialize(converter = Vector.ToArrayConverter.class)
@JsonDeserialize(converter = Vector.FromArrayConverter.class)
public class Vector {

    int x;
    int y;

    public Vector add(Vector addend) {
        return Vector.of(x + addend.x, y + addend.y);
    }

    static class ToArrayConverter extends StdConverter<Vector, int[]> {

        @Override
        public int[] convert(Vector value) {
            return new int[]{value.x, value.y};
        }
    }

    static class FromArrayConverter extends StdConverter<int[], Vector> {

        @Override
        public Vector convert(int[] value) {
            if (value.length == 2) {
                return new Vector(value[0], value[1]);
            }

            throw new VectorDeserializationException(value);
        }
    }

}
