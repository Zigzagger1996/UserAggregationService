package com.example.userAggregationService;

import com.example.userAggregationService.config.DataSourcesProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DataSourcesProperties.class)
public class UserAggregationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAggregationServiceApplication.class, args);
	}

}
