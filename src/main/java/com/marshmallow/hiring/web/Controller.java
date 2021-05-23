package com.marshmallow.hiring.web;

import com.marshmallow.hiring.domain.Direction;
import com.marshmallow.hiring.domain.Robot;
import com.marshmallow.hiring.domain.RobotStatus;
import com.marshmallow.hiring.exception.InvalidSeaAreaSizeException;
import com.marshmallow.hiring.exception.PositionOutOfSeaAreaBoundsException;
import com.marshmallow.hiring.exception.VectorDeserializationException;
import com.marshmallow.hiring.web.model.InstructionsRequest;
import com.marshmallow.hiring.web.model.InstructionsResponse;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class Controller {

    @PostMapping("/instructions")
    public InstructionsResponse instructions(@RequestBody InstructionsRequest instructions) {
        log.info("Handling request: [" + instructions + "]");
        Robot robot = new Robot(instructions.getAreaSize(), instructions.getStartingPosition(), instructions.getOilPatches());
        List<Direction> directions = Direction.parseNavigationInstructions(instructions.getNavigationInstructions());

        RobotStatus result = robot.navigate(directions).getStatus();
        return new InstructionsResponse(result.getPosition(), result.getOilPatchesCleaned());
    }

    @ExceptionHandler(value = {
            InvalidSeaAreaSizeException.class,
            PositionOutOfSeaAreaBoundsException.class,
            VectorDeserializationException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ApiError> handleKnownException(RuntimeException ex) {
        log.warn("Exception thrown while handling request.", ex);
        return ResponseEntity.badRequest().body(new ApiError(ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(RuntimeException ex) {
        log.error("Exception thrown while handling request.", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(ex.getMessage()));
    }

    @Value
    static class ApiError {
        String error;
    }
}
