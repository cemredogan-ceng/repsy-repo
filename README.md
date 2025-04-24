# Repsy Project - Custom Maven Repository Management

This project is a technical assignment for the Junior FullStack Developer role.  
It implements a custom Maven repository manager for a fictional programming language called **Repsy**.

---

##  Tech Stack

- Java 17  
- Spring Boot 3  
- PostgreSQL  
- Docker  
- Maven Wrapper  
- JUnit 5 + Mockito  

---

##  Acceptance Criteria Checklist

| Feature                    | Description                                                           | Status  |
|----------------------------|-----------------------------------------------------------------------|---------|
| REST API                   | Repsy-compatible endpoints                                            | ✅     |
| Upload & Download          | Handles `.jar` and `.pom` artifact uploads/downloads                  | ✅     |
| Storage Strategy 1         | File system-based storage                                             | ✅     |
| Storage Strategy 2         | Object storage abstraction with `ObjectStorage` interface             | ✅     |
| Metadata Storage           | Artifact metadata saved in PostgreSQL                                 | ✅     |
| Unit Tests                 | Upload logic tested with JUnit and Mockito                            | ✅     |
| Containerization           | Dockerized application and database setup                             | ✅     |
| README                     | Documentation in English with implementation overview                 | ✅     |

---

##  API Endpoints

- `POST /api/repsy/upload`  
  Uploads a `.jar` or `.pom` file with metadata.

- `GET /api/repsy/download`  
  Downloads artifacts based on `groupId`, `artifactId`, and `version`.

---

##  Project Structure
src/ ├── main/ │ ├── java/com/repsy/ │ │ ├── controller/ # REST endpoints │ │ ├── service/ # Business logic │ │ ├── storage/ # Storage strategies │ │ └── config/ # Configuration │ └── resources/ # application.properties ├── test/ │ └── java/com/repsy/ # Unit tests


---

##  How to Run Locally

1. **Clone the repo**
   git clone https://github.com/cemredogan-ceng/repsy-repo.git

2.**Build the project**
   ./mvnw clean install

3.**Start with Docker**
   docker-compose up

4.**API Access**
http://localhost:8080/api/repsy


## Author
Cemre Doğan


