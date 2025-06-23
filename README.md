User PC Component Management System is a simple Java console application that allows managing users, their computers, and the components of those computers. The project uses JPA/Hibernate with PostgreSQL for data persistence.

Overview
This application enables managing user information (name, address, phone, age) and allows operations on computers and their components assigned to those users.
It supports basic CRUD operations such as adding, listing, searching, updating, and deleting users. Additionally, users’ computers and their components can be viewed in detail.

Key Features
- User Management: Add, list, search (by name, address, age range), update, and delete users.
- Computer and Component Management: List computers and components belonging to a user. Search users by component type.
- Statistics and Reporting: Find the most used component type. List users ordered by the number of computers they own. Calculate average components per computer and component type distribution.
- Database: Uses PostgreSQL for relational data persistence.

Technologies Used
- Jakarta Persistence API (JPA) 3.0
- Hibernate ORM 6.6
- PostgreSQL 42.7
- Lombok
- Gradle (build tool)
