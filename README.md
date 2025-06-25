Project documentation


Project Overview

  This is a Spring Boot application that creates users and pets and stores them in a MySQL database.
  It also implements a buying process where each registered user attempts to purchase a pet that is available — meaning the pet has no owner and the user has enough budget to afford it.
  The application also stores logs for each purchase attempt, including the number of users who successfully bought a pet and those who failed to make a purchase.

Database Setup 

  For the needs of the application I created a MySQL database. The database initialization script can be found in SQLScripts/InitialScript.sql .

  In the application.properties file, the spring.datasource.url is already provided. You only need to enter your own spring.datasource.username and spring.datasource.password values.

API

  The backend exposes both RESTful APIs and a GraphQL API. 

  REST API Endpoints 
  ![image](https://github.com/user-attachments/assets/93a73cc6-ed96-43db-8507-561aff6fea9b)


  GraphQL

  In addition to standard REST endpoints, this application also includes a GraphQL API built with Spring for GraphQL. This enables more flexible and efficient data retrieval and mutation.
  
  The schema is defined in the resources/graphql/schema.graphqls file.
  
  GraphQL queries and mutations are handled by custom Resolver classes, defined in com.example.petstoreapp.graphql folder.
  
  Accessing the GraphQL API: 
  You can send GraphQL queries and mutations by sending POST requests to: http://localhost:8080/graphql

Running the Application

    1. When starting the application for first time, execute the provided SQL script (InitialScript.sql) to create the necessary database and tables.
    
    2. Configure database credentials in application.properties.
    
    3. Run the Spring Boot application.

    4.Application can be tested using Postman
