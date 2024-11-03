* US1 - Persisting Data


| Aspect               | Details                                                                                                                                                                                |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Problem**          | The need to support multiple data models and DBMS to enhance the flexibility and adaptability of the library management system.                                                        |
| **Summary of Solution** | Implement a data abstraction layer that allows configuration and use of various DBMSs (such as SQL Server and MongoDB) and data models.                                                |
| **Factors**          | Different data types and operational requirements necessitate support for multiple databases; DBMS choice may depend on factors like cost, performance, and support for transactions or real-time operations. |
| **Solution**         | Use patterns like Repository to isolate business logic from data access code, enabling DBMS changes without impacting other system parts.                                              |
| **Motivation**       | Flexibility to choose or switch storage technology as needed without significant refactoring costs or downtime.                                                                        |
| **Alternatives**     | Exclusive use of a polyglot DBMS or an ORM that supports multiple databases, though there might be specific limitations with each technology.                                          |
| **Pending Issues**   | Performance and security evaluation for each integrated DBMS; ensuring data integrity and consistency across different systems.                                                        |
