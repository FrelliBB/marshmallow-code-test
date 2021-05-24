package com.marshmallow.hiring.web.model;


import com.marshmallow.hiring.domain.Vector;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Value
public class InstructionsRequest {

    @NotNull
    Vector areaSize;

    @NotNull
    Vector startingPosition;

    @NotNull
    Set<Vector> oilPatches;

    @NotNull
    @Pattern(regexp = "^[NESW]*$")
    String navigationInstructions;

}
