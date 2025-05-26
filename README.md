# PokemonApp
This application created on Springboot allows the user to fetch certain data about pokemon.

# About
A user can query the following data
1. Five heaviest pokemon
2. Five tallest pokemon
3. Five more experienced pokemon (from base experience)

# Features
1. Security using Basic auth
2. Access to DB (/h2-console)
3. Access to swagger (/swagger-ui.index.html)
4. Error handling

# Installation
Clone the repository: git clone https://github.com/mariaJoseph5/PokemonApp.git
                      cd PokemonApp
Build the repository: mvn clean install

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
1. Create a persist DB rather than on application load
2. Use more secure authentication like JWT
3. Add more endpoints to allow all CRUD operations
4. Allow more searchable fields
