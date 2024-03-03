package org.common.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {

    @Bean
    private ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    private ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
