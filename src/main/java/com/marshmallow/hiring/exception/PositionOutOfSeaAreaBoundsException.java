package com.marshmallow.hiring.exception;

import com.marshmallow.hiring.domain.SeaArea;
import com.marshmallow.hiring.domain.Vector;

public class PositionOutOfSeaAreaBoundsException extends RuntimeException {

    public PositionOutOfSeaAreaBoundsException(SeaArea area, Vector position) {
        super(position + " is not a valid position in " + area);
    }
}
