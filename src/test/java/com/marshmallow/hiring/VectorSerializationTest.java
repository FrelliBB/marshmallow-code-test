package com.marshmallow.hiring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marshmallow.hiring.domain.Vector;
import com.marshmallow.hiring.exception.VectorDeserializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JsonTest
class VectorSerializationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void serializeVector() throws Exception {
        Vector v = Vector.of(0, 0);
        String serialized = objectMapper.writeValueAsString(v);
        assertThat(serialized).isEqualTo("[0,0]");
    }

    @Test
    public void deserializeVector_validValue() throws Exception {
        String json = "[1,2]";
        Vector vector = objectMapper.readValue(json, Vector.class);
        assertThat(vector).isEqualTo(Vector.of(1, 2));
    }

    @Test
    public void deserializeVector_arrayTooShort_throwsException() {
        String json = "[1]";
        assertThatThrownBy(() -> objectMapper.readValue(json, Vector.class))
                .isInstanceOf(VectorDeserializationException.class);
    }

    @Test
    public void deserializeVector_arrayTooLong_throwsException() {
        String json = "[1,2,3]";
        assertThatThrownBy(() -> objectMapper.readValue(json, Vector.class))
                .isInstanceOf(VectorDeserializationException.class);
    }

}
