package com.example.userAggregationService.service;

import com.example.userAggregationService.config.DataSourcesProperties;
import com.example.userAggregationService.repository.DynamicRepositoryFactory;
import com.example.userAggregationService.model.User;
import com.example.userAggregationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private DynamicRepositoryFactory repositoryFactory;

    @Autowired
    private DataSourcesProperties dataSourcesProperties;

    public List<User> getAllUsersService() {
        List<User> allUsers = new ArrayList<>();

        for (DataSourcesProperties.DataSourceConfig config : dataSourcesProperties.getDataSources()) {
            UserRepository userRepository = repositoryFactory.createRepository(config.getName());
            allUsers.addAll(userRepository.findAll());
        }

        return allUsers;
    }
}