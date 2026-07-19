# Landscape Management System

Desktop application for managing landscape service orders built with **Java Swing**, **JDBC**, and **MySQL** following a layered architecture and software engineering
best practices.

## Project Description

**Landscape Management System** is a Java-based desktop application designed to manage landscaping service orders. Users can create, edit, search, sort, 
and delete customer records while automatically calculating project costs based on yard type and dimensions.

Although originally developed as a university project, the application has been significantly refactored into a maintainable and extensible application using
enterprise software design principles including layered architecture, dependency injection, repository abstraction, generic interfaces, transaction management, 
custom exception handling, and automated testing.

## Business Problem

The original application tightly coupled the user interface, business logic, and database access, making it difficult to maintain, test, and extend.

This project was redesigned to demonstrate professional software engineering practices by separating responsibilities into independent layers and 
introducing reusable architectural components.

## Core features

- **Add Customer**: Allows customers to be added to the database for persistant storage.

- **Retrieve all Customers**: Gets all customers from the database.

- **Search Customers**: Searches customers based on name or address attributes and returns the result.

- **Sort Customers**: Sorts customers within a JTable based on name, yard type, and total cost.

- **Update Customer**: Updates a customer entry in the database based on customer ID.

- **Delete Customer**: Deletes a customer entry in the database based on customer ID.

- **Calculate Total Cost**: Uses business rules to calculate the cost of a customers order based on yard type and dimensions.

- **Export to CSV**: Action that reads customer information from a JTable and writes the contents to a CSV file.

- **Input Validation**: Validate user input for accurate entries.

- **Externalize Configuration**: Database configuration properties are read from an external file using an input stream.

- **Logging**: Log database activities for traceability and troubleshooting purposes.

## Software Architecture

The **Landscape Management System** consists of presentation, business logic, data storage, and database layers that have different responsibilities.

- **GUI**: Responsible for creating and displaying the JFrames components as well as displaying messages to the user.

- **Customer Service**: Layer that bridges communciations from the GUI to a repository. Built in such a way that can work with other repositories.

- **Customer Repository**:  Layer that provides an implementation for the data access layer.

- **Customer DB**: The data access layer communications with MySQL database via JDBC connection to perform CRUD operations.

- **MySQL**: Serves as a persistent storage container for all customer records.

## Design Principles

| Principle              | Implementation                   |
| ---------------------- | -------------------------------- |
| Layered Architecture   | GUI → Service → Repository       |
| Dependency Injection   | Constructor Injection in GUI and Service layers     |
| Repository Pattern     | CustomerRepository               |
| Transaction Template   | Reusable transaction management  |
| Custom Exceptions      | ApplicationException hierarchy   |
| Strategy Pattern       | Implementation for different sorting methods using Comparator and Collections        |
| Singleton Pattern      | ConfigLoader provides one instance throughout application lifecycle                  |
| DAO Pattern            | CustomerDB                       |
| Generic Programming    | TransactionTemplate works with multiple target domain objects            |
| Separation of Concerns | Business logic isolated from GUI |
| Composition Root       | AppContainer wires up dependencies                  |
| Logging                | Centralized AppLogger            |
| Configuration          | External ConfigLoader for database properties           |

## Technologies

- Apache NetBeans IDE 29
- Java 25
- JDBC
- MySQL
- JUnit 4
- Mockito Core 4.11.0
## Testing Support

The **Landscape Management System** implements **JUnit** testing using **Mockito** objects to isolate the customer service layer from repository implementation. 
Utilized **argument captors**, **repository interaction verification**, **object assertions**, and **custom exception simulations** to validate CRUD operations and business rules.

### Features Tested

- Add Customer
- Get all Customers
- Search Customers by Name or Address
- Update Customer
- Delete Customer
- Customer not Found Exception
- Database Exception

## Screenshots

### Welcome Panel
<img width="689" height="435" alt="image" src="https://github.com/user-attachments/assets/6a1886dc-5800-49c2-946b-e4847f2caee3" />

### Information Panel
<img width="691" height="410" alt="image" src="https://github.com/user-attachments/assets/9e5e2dfc-6024-4282-aec0-1225e59b39e0" />

### Customer Table Panel
<img width="689" height="412" alt="image" src="https://github.com/user-attachments/assets/e7b35011-a22c-4f83-97ad-f855b2a2d16e" />

### Customer List Panel
<img width="692" height="412" alt="image" src="https://github.com/user-attachments/assets/640ed9d5-81c7-400e-a9ed-812090c8eb0e" />

## Architecture Evolution

| Original Project               | Current Version             |
| ------------------------------ | --------------------------- |
| GUI directly accessed database | Layered Architecture        |
| Tight coupling                 | Dependency Injection        |
| JDBC inside GUI                | Repository Pattern          |
| No transaction management      | Generic TransactionTemplate |
| Generic Exceptions             | Custom Exception Hierarchy  |
| No automated tests             | JUnit + Mockito             |
| Hardcoded dependencies         | Composition Root            |
| Hardcoded configuration        | External configuration      |
| Basic Customer Management      | Search, Sort, Update, CSV Export, Input Validation |

