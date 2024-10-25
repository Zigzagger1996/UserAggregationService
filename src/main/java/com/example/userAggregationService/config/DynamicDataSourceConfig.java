package com.example.userAggregationService.config;



import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(DataSourcesProperties.class)
public class DynamicDataSourceConfig {

    @Autowired
    private DataSourcesProperties dataSourcesProperties;

    private final Map<String, DataSource> dataSourceMap = new HashMap<>();
    private final Map<String, LocalContainerEntityManagerFactoryBean> entityManagerFactoryMap = new HashMap<>();
    private final Map<String, PlatformTransactionManager> transactionManagerMap = new HashMap<>();

    @PostConstruct
    public void init() {
        //TODO: NPE when getDataSource(). For some reason configuration file doesn't visible.
        for (DataSourcesProperties.DataSourceConfig config : dataSourcesProperties.getDataSources()) {
            DataSource dataSource = DataSourceBuilder.create()
                    .driverClassName("org.postgresql.Driver")
                    .url(config.getUrl())
                    .username(config.getUser())
                    .password(config.getPassword())
                    .build();
            dataSourceMap.put(config.getName(), dataSource);

            // Create EntityManagerFactory
            LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
            emFactory.setDataSource(dataSource);
            emFactory.setPackagesToScan("com.example.userAggregationService.model");
            emFactory.setPersistenceUnitName(config.getName());
            emFactory.afterPropertiesSet();

            entityManagerFactoryMap.put(config.getName(), emFactory);

            // Create TransactionManager
            JpaTransactionManager transactionManager = new JpaTransactionManager(emFactory.getObject());
            transactionManagerMap.put(config.getName(), transactionManager);
        }
    }

    @Bean
    public Map<String, DataSource> dataSources() {
        return dataSourceMap;
    }

    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(String name) {
        return entityManagerFactoryMap.get(name);
    }

    public PlatformTransactionManager getTransactionManager(String name) {
        return transactionManagerMap.get(name);
    }
}