package br.com.teknologi.financial.operation.configuration;

import br.com.teknologi.financial.operation.domain.repository.OperationRepository;
import br.com.teknologi.financial.operation.domain.service.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BeansConfig {

    @Bean
    public OperationService operationService(OperationRepository operationRepository){
        log.debug("[Configuration] ===== OperationService Bean created =====");
        return new OperationService(operationRepository);
    }

}
