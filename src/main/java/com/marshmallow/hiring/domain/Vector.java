package com.marshmallow.hiring.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Value;

import static com.marshmallow.hiring.jackson.VectorConverters.FromArrayConverter;
import static com.marshmallow.hiring.jackson.VectorConverters.ToArrayConverter;

@Value(staticConstructor = "of")
@JsonSerialize(converter = ToArrayConverter.class)
@JsonDeserialize(converter = FromArrayConverter.class)
public class Vector {

    int x;
    int y;

    public Vector add(Vector addend) {
        return Vector.of(x + addend.x, y + addend.y);
    }

}
