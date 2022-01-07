package br.com.teknologi.financial.operation.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.adapter.HttpWebHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
@Slf4j
public class LocaleConfig {

    List<Locale> LOCALES = Arrays.asList(
            new Locale("pt"),
            new Locale("en"));

    @Bean
    public HttpHandler acceptLanguageHeaderHttpHandler(ApplicationContext applicationContext) {

        HttpHandler delegate = WebHttpHandlerBuilder.applicationContext(applicationContext).build();

        return new HttpWebHandlerAdapter((HttpWebHandlerAdapter) delegate) {

            @Override
            protected ServerWebExchange createExchange(ServerHttpRequest request, ServerHttpResponse response) {
                ServerWebExchange serverWebExchange = super.createExchange(request, response);
                LocaleContext localeContext = serverWebExchange.getLocaleContext();
                Locale requestedLocale = localeContext.getLocale();

                if (requestedLocale != null &&
                        LOCALES.stream().anyMatch(locale -> locale.getLanguage().equals(requestedLocale.getLanguage()))) {
                    LocaleContextHolder.setLocaleContext(localeContext);
                } else {
                    LocaleContextHolder.setLocaleContext(Locale::getDefault);
                }

                return serverWebExchange;
            }

        };
    }

    @PostConstruct
    public void init(){
        log.debug("[Configuration] ===== Locale Bean created =====");
    }

}
