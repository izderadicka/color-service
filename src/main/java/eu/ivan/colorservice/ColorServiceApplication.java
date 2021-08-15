package eu.ivan.colorservice;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import eu.ivan.colorservice.service.Color;
import eu.ivan.colorservice.service.ColorService;
import eu.ivan.colorservice.service.RandomColorService;

@SpringBootApplication
public class ColorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColorServiceApplication.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> randomColor(ColorService colorService) {
		return route(GET("/color"), req -> {
			return ok().body(colorService.generateColor(req.queryParam("name")), Color.class);
		});
	}

	@Bean
	public ColorService randomColorService() {
		return new RandomColorService();
	}

}
