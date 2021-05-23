package com.marshmallow.hiring.domain;

import lombok.Value;

@Value
public class RobotStatus {
    Vector position;
    int oilPatchesCleaned;
}
