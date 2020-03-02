package br.unisinos.apps4business.users.configuration;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    public MapperFacade mapperFacade(){
        return new DefaultMapperFactory.Builder().build().getMapperFacade();
    }
}
