package com.marshmallow.hiring.domain;

import com.marshmallow.hiring.exception.PositionOutOfSeaAreaBoundsException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.marshmallow.hiring.domain.Direction.N;
import static com.marshmallow.hiring.domain.Direction.parseNavigationInstructions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RobotTest {

    @Test
    void initializeRobot() {
        Vector area = Vector.of(5, 5);
        Vector startingPosition = Vector.of(0, 0);
        Set<Vector> oilPatches = Set.of(Vector.of(1, 1));

        Robot robot = new Robot(area, startingPosition, oilPatches);
        RobotStatus status = robot.getStatus();

        assertThat(status.getPosition()).isEqualTo(startingPosition);
        assertThat(status.getOilPatchesCleaned()).isEqualTo(0);
    }

    @Test
    void initializeRobot_oilPatchOnStartingPosition_oilPatchCleaned() {
        Vector area = Vector.of(1, 1);
        Vector startingPosition = Vector.of(0, 0);
        Set<Vector> oilPatches = Set.of(Vector.of(0, 0));

        Robot robot = new Robot(area, startingPosition, oilPatches);
        RobotStatus status = robot.getStatus();

        assertThat(status.getPosition()).isEqualTo(startingPosition);
        assertThat(status.getOilPatchesCleaned()).isEqualTo(1);
    }

    @Test
    void initializeRobot_oilPatchOutOfBounds_throwsPositionOutOfSeaAreaBoundsException() {
        Vector area = Vector.of(1, 1);
        Vector startingPosition = Vector.of(0, 0);
        Set<Vector> oilPatches = Set.of(Vector.of(1, 0));

        assertThatThrownBy(() -> new Robot(area, startingPosition, oilPatches))
                .isInstanceOf(PositionOutOfSeaAreaBoundsException.class);
    }

    @Test
    void navigate_outOfAreaBounds_throwsPositionOutOfSeaAreaBoundsException() {
        Vector area = Vector.of(1, 1);
        Vector startingPosition = Vector.of(0, 0);

        Robot robot = new Robot(area, startingPosition, Collections.emptySet());

        assertThatThrownBy(() -> robot.navigate(List.of(N)))
                .isInstanceOf(PositionOutOfSeaAreaBoundsException.class);
    }

    @Test
    void navigate_exampleUseCase_statusCorrect() {
        Vector area = Vector.of(5, 5);
        Vector startingPosition = Vector.of(1, 2);
        Set<Vector> oilPatches = Set.of(Vector.of(1, 0), Vector.of(2, 2), Vector.of(2, 3));
        List<Direction> directions = parseNavigationInstructions("NNESEESWNWW");

        Robot robot = new Robot(area, startingPosition, oilPatches).navigate(directions);
        RobotStatus status = robot.getStatus();

        Vector expectedFinalPosition = Vector.of(1, 3);
        int expectedOilPatchesCleaned = 1;
        assertThat(status.getPosition()).isEqualTo(expectedFinalPosition);
        assertThat(status.getOilPatchesCleaned()).isEqualTo(expectedOilPatchesCleaned);
    }

}
