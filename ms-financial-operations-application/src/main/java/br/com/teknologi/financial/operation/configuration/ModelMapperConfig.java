package br.com.teknologi.financial.operation.configuration;

import br.com.teknologi.financial.operation.rest.converter.BasicOperationRequestToOperationConverter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new BasicOperationRequestToOperationConverter());

        log.debug("[Configuration] ===== ModelMapper bean created =====");

        return modelMapper;
    }

}