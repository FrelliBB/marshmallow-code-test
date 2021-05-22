package com.marshmallow.hiring.web;


import com.marshmallow.hiring.domain.Vector;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Value
public class InstructionsRequest {

    Vector areaSize;
    Vector startingPosition;

    @NotNull
    List<Vector> oilPatches;

    @NotNull
    @Pattern(regexp = "^[NESW]*$")
    String navigationInstructions;

}
