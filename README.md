# User Aggregation Service

## Overview
The User Aggregation Service is a Spring Boot application designed to manage and aggregate user data from multiple PostgreSQL databases. It provides endpoints for retrieving user information and facilitates the dynamic configuration of data sources through YAML properties.

## Table of Contents

- [Architecture](#architecture)
- [Classes](#classes)
    - [UserAggregationServiceApplication](#useraggregationserviceapplication)
    - [DynamicDataSourceConfig](#dynamicdatasourceconfig)
    - [DataSourcesProperties](#datasourcesproperties)
    - [UserController](#usercontroller)
    - [User](#user)
    - [DynamicRepositoryFactory](#dynamicrepositoryfactory)
    - [UserRepository](#userrepository)
    - [UserService](#userservice)

## Architecture
The service uses Spring Boot for rapid development and deployment. It supports multiple data sources, allowing for flexible user data aggregation from various databases. The OpenAPI specification is implemented for easy API documentation and testing.

## Classes
### UserAggregationServiceApplication
This is the main entry point of the application. It initializes the Spring context and starts the embedded server.

### DynamicDataSourceConfig
Create multiple dataSources and entity managers based on application.yml.

### DataSourcesProperties
Data representation of application.yml configuration.

### UserController
RestAPI to get all Users from all DBs. Contains nest methods:
/users - Retrieve a list of users from all DBs added in application.yml

### User
User entity class

### DynamicRepositoryFactory
Based on dataSourceName get EntityManagerFactory and create UserRepository.

### UserRepository
Simple user repo with findAll() method.

### UserService
For each DataSource create get repository from DynamicRepositoryFactory and find all users in DB. Return - List<User> allUsers



