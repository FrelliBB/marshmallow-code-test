package com.marshmallow.hiring.web.model;

import com.marshmallow.hiring.domain.Vector;
import lombok.Value;

@Value
public class InstructionsResponse {

    Vector finalPosition;
    int oilPatchesCleaned;

}
