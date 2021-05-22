package com.marshmallow.hiring.web;

import com.marshmallow.hiring.domain.Vector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class Controller {

    @PostMapping("/instructions")
    public InstructionsResponse instructions(@Valid @RequestBody InstructionsRequest instructions) {
        log.info("Processing instructions: [{}]", instructions);
        return new InstructionsResponse(Vector.of(1, 3), 1);
    }

}
