package br.com.teknologi.financial.operation.configuration;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        log.debug("[Configuration] ===== ModelMapper Bean created =====");
        return new ModelMapper();
    }

}
