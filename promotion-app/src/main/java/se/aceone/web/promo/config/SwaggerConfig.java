package se.aceone.web.promo.config;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Bean constructor for Swagger2.
	 * 
	 * @return Swagger2 Docket api.
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
		        .directModelSubstitute(OffsetDateTime.class, String.class)
				.directModelSubstitute(ZonedDateTime.class, String.class)
				.select()
				.apis(RequestHandlerSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("se.aceone.web"))
				.paths(PathSelectors.any())
				.build();
		

	}
}
