package com.marshmallow.hiring;


import lombok.Value;

@Value
public class InstructionsRequest {

    int[] areaSize;
    int[] startingPosition;
    int[][] oilPatches;
    String navigationInstructions;

}
