package com.example.userAggregationService.repository;

import com.example.userAggregationService.config.DynamicDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class DynamicRepositoryFactory {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DynamicDataSourceConfig dynamicDataSourceConfig;

    public UserRepository createRepository(String dataSourceName) {
        // Retrieve the EntityManagerFactory for the specified data source
        LocalContainerEntityManagerFactoryBean entityManagerFactory = dynamicDataSourceConfig.getEntityManagerFactory(dataSourceName);

        if (entityManagerFactory == null) {
            throw new IllegalArgumentException("No EntityManagerFactory found for data source: " + dataSourceName);
        }

        // Create a repository factory for the specific data source
        JpaRepositoryFactory factory = new JpaRepositoryFactory(entityManagerFactory.getObject().createEntityManager());

        // Return a new instance of the UserRepository
        return factory.getRepository(UserRepository.class);
    }
}