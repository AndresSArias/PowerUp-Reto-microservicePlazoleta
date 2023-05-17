package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebConfiguration {

    @Bean
    public WebClient webClient  (){
        return WebClient.builder().build();
    }

    @Bean
    public UserClient userClient (final WebClient webCliente){
        WebClientAdapter clientAdapter = WebClientAdapter.forClient(webCliente);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(clientAdapter).build();
        return httpServiceProxyFactory.createClient(UserClient.class);
    }

}
