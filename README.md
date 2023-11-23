# Password Encryption Project

# Overview
This project is a simple example of a password encryption and verification system using Spring Boot and AES encryption. Please note that using reversible encryption for passwords is not recommended for production use. This project is created for educational purposes only.

# Table of Contents
Dependencies
Project Folder Structure
Installation
Usage
Configuration
Contributing
License
Dependencies
Java 8 or later
Spring Boot
PostgreSQL
Make sure to check the pom.xml file for a complete list of dependencies.

# Project Folder Structure
```
src
|-- main
|   |-- java
|   |   |-- com.example
|   |       |-- controller
|   |       |   |-- PasswordController.java
|   |       |-- model
|   |       |   |-- User.java
|   |       |-- repository
|   |       |   |-- UserRepository.java
|   |       |-- service
|   |       |   |-- PasswordService.java
|   |       |   |-- UserService.java
|   |       |   |-- PasswordEncryptionService.java
|   |       |-- Application.java
|   |-- resources
|       |-- application.properties
```

# Installation
Clone the repository:

git clone https://github.com/your-username/password-encryption-project.git

Open the project in your preferred IDE (e.g., IntelliJ).
Configure the database in src/main/resources/application.properties.
Run the Application.java class.

# Usage
Register a new user with an encrypted password:

curl -X POST "http://localhost:8080/api/password/register?username=johndoe&password=secretpassword"

Check if a password is correct:

curl -X POST "http://localhost:8080/api/password/check?username=johndoe&password=secretpassword"

# Configuration
Database configuration: Update src/main/resources/application.properties with your database details.
Encryption key: Update the SECRET_KEY in PasswordEncryptionService.java with a secure key.

# Contributing
Feel free to contribute by opening issues or pull requests. Follow the Contribution Guidelines.

# License
This project is licensed under the MIT License.