# PokemonApp
This application, created on Springboot(JAVA), allows the user to fetch details about pokemon.

# About
A user can query the following data:
1. Five heaviest pokemon (http://localhost:8080/api/v1/pokemon/list?sortBy=weight&sortDir=DESC&page=0&size=5)
2. Five tallest pokemon (http://localhost:8080/api/v1/pokemon/list?sortBy=height&sortDir=DESC&page=0&size=5)
3. Five most experienced pokemon (from base experience) (http://localhost:8080/api/v1/pokemon/list?sortBy=base_experience&sortDir=DESC&page=0&size=5)
It allows sortable fields (weight, height, pokemonId, name, baseExperience) in either DESC or ASC order. The parameters page and size together are used for pagination. Default values of each parameter are sortBy=pokemonId, sortDir=ASC, page=0, size=2000. If no query parameters are passed (http://localhost:8080/api/v1/pokemon/list), a list of all pokemon ordered by their pokemonID in ASC will be returned.

# Features
1. Security using Basic auth (username: apiUser, password: password)
2. Access to DB (/h2-console) (username: sa, password: password)
3. Configuration to swagger (/swagger-ui.index.html) and api-docs (/v3/api-docs)
4. Error handling
5. Pagination
6. Code coverage

# Installation
Clone the repository: 
git clone https://github.com/mariaJoseph5/PokemonApp.git
cd PokemonApp

Build the repository: 
mvn clean install

# Tests
Run tests using command: mvn test

# Usage
Run code using command: mvn spring-boot:run

# Technologies Used
1. Java 17
2. Spring Boot 3.4.6
3. Maven
4. Mockito
5. JUnit 4 and 5

# Improvements
1. Creating a persist DB rather than on application load would be one of the essential tasks for improving this application. As of right now, I am calling the fetch pokemon API on load of the application and adding data to the database on inital load.
2. Using more secure authentication like JWT can ensure better security. For now, I have used Basic auth to authenticate the user.
3. Add more endpoints to allow all CRUD operations
4. Allow more searchable fields
