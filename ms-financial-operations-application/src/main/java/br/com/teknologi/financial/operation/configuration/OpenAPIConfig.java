package br.com.teknologi.financial.operation.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${application.name}") String name,
            @Value("${application.description}") String description,
            @Value("${application.version}") String version) {

        log.debug("[Configuration] ===== OpenAPI Bean created =====");

        return new OpenAPI()
                .info(new Info().title(name).version(version).description(description));
    }

}
