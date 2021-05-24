package com.marshmallow.hiring;

import com.marshmallow.hiring.domain.Vector;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import org.springdoc.core.SpringDocUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public OpenAPI openAPI() {
        SpringDocUtils.getConfig().replaceWithSchema(Vector.class,
                new ArraySchema()
                        .items(new IntegerSchema())
                        .minLength(2)
                        .maxLength(2)
                        .description("An integer array representing 2D co-ordinates in a grid or the grid size.")
                        .example("[1, 3]"));

        return new OpenAPI()
                .info(new Info()
                        .title("M4R-5H Oil Cleaner")
                        .description("M4R-5H Oil Cleaner implementation for the Marshmallow backend engineer code test.")
                );
    }

}
