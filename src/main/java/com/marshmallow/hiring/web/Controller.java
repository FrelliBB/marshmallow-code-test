package com.marshmallow.hiring.web;

import com.marshmallow.hiring.domain.Direction;
import com.marshmallow.hiring.domain.Robot;
import com.marshmallow.hiring.domain.RobotStatus;
import com.marshmallow.hiring.exception.InvalidSeaAreaSizeException;
import com.marshmallow.hiring.exception.PositionOutOfSeaAreaBoundsException;
import com.marshmallow.hiring.exception.VectorDeserializationException;
import com.marshmallow.hiring.web.model.InstructionsRequest;
import com.marshmallow.hiring.web.model.InstructionsResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
public class Controller {

    @PostMapping("/instructions")
    @Operation(tags = "Instructions", summary = "Process navigation instructions")
    public InstructionsResponse instructions(@RequestBody @Valid InstructionsRequest instructions) {
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
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleKnownException(Exception ex) {
        log.warn("Exception thrown while handling request.", ex);
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleException(Exception ex) {
        log.error("Exception thrown while handling request.", ex);
        return new ApiError(ex.getMessage());
    }

    @Value
    static class ApiError {
        String error;
    }
}
