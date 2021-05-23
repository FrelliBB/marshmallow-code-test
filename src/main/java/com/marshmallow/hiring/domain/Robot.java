package com.marshmallow.hiring.domain;

import com.marshmallow.hiring.exception.PositionOutOfSeaAreaBoundsException;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;

@ToString
@AllArgsConstructor(access = PRIVATE)
public class Robot {

    private final SeaArea seaArea;
    private final Vector position;
    private final OilCleaner oilCleaner;

    public Robot(Vector areaSize, Vector startingPosition, Set<Vector> oilPatchLocations) {
        requireNonNull(areaSize, "areaSize must not be null");
        requireNonNull(startingPosition, "startingPosition must not be null");
        requireNonNull(oilPatchLocations, "oilPatchLocations must not be null");

        this.seaArea = new SeaArea(areaSize);

        checkValidPosition(startingPosition);
        this.position = startingPosition;

        oilPatchLocations.forEach(this::checkValidPosition);
        this.oilCleaner = new OilCleaner(oilPatchLocations).cleanLocation(startingPosition);
    }

    public Robot navigate(List<Direction> directions) {
        requireNonNull(directions, "directions must not be null");

        Robot robot = this;
        for (Direction direction : directions) {
            robot = robot.navigate(direction);
        }
        return robot;
    }

    private Robot navigate(Direction direction) {
        Vector newPosition = position.add(direction.getVector());
        checkValidPosition(newPosition);

        OilCleaner updatedOilCleaner = oilCleaner.cleanLocation(newPosition);
        return new Robot(seaArea, newPosition, updatedOilCleaner);
    }

    public RobotStatus getStatus() {
        return new RobotStatus(position, oilCleaner.getOilPatchesCleaned());
    }

    private void checkValidPosition(Vector position) {
        if (!seaArea.isValidLocation(position)) {
            throw new PositionOutOfSeaAreaBoundsException(seaArea, position);
        }
    }
}
