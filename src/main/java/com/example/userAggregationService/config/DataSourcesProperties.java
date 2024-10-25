package com.example.userAggregationService.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.data-sources")
public class DataSourcesProperties {
    private List<DataSourceConfig> dataSources;

    @Getter
    @Setter
    public static class DataSourceConfig {
        private String name;
        private String strategy;
        private String url;
        private String table;
        private String user;
        private String password;
        private Mapping mapping; // Nested class for mapping

        @Getter
        @Setter
        public static class Mapping {
            private String id;
            private String username;
            private String name;
            private String surname;
        }
    }
}
