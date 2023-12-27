package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity)
    {
         //enable security conf in api gateway using spring securuty in webflux
        // crsrf disabled as we are only using http call
        //onlu /eureka/** are permitted
        //rest everyhing authenticated using jwt
        serverHttpSecurity.csrf()
                .disable()
                .authorizeExchange(exchange ->  exchange.pathMatchers("/eureka/**").permitAll()
                        .anyExchange().authenticated()
                ).oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);

        return serverHttpSecurity.build();
    }
}
