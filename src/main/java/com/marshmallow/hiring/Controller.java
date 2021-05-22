package com.marshmallow.hiring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Controller {

    @PostMapping("/instructions")
    public InstructionsResponse instructions(@RequestBody InstructionsRequest instructions) {
        log.info("Processing instructions: [{}]", instructions);
        return new InstructionsResponse(new int[]{1, 3}, 1);
    }

}
