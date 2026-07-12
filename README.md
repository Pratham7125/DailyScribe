# 📔 DailyScribe

> A secure Journal Management REST API built using Spring Boot, MongoDB Atlas, Redis, Kafka, Spring Security, and Swagger UI.

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![MongoDB](https://img.shields.io/badge/MongoDB-Atlas-47A248?style=for-the-badge&logo=mongodb)](https://www.mongodb.com/atlas)
[![Redis](https://img.shields.io/badge/Redis-Cloud-DC382D?style=for-the-badge&logo=redis)](https://redis.io/)
[![Apache Kafka](https://img.shields.io/badge/Kafka-Confluent-231F20?style=for-the-badge&logo=apachekafka)](https://www.confluent.io/)
[![Railway](https://img.shields.io/badge/Hosted_on-Railway-0B0D0E?style=for-the-badge&logo=railway)](https://railway.app/)
[![Swagger](https://img.shields.io/badge/API-Swagger-85EA2D?style=for-the-badge&logo=swagger)](https://swagger.io/)

---

## 🚀 Live Demo

### 🌐 Application

https://dailyscribe-production.up.railway.app

### 📘 Swagger API Documentation

https://dailyscribe-production.up.railway.app/swagger-ui/index.html

---

# 📖 About

DailyScribe is a secure RESTful Journal Management application that enables users to create, update, manage, and organize personal journal entries.

The application is built using Spring Boot following production-ready backend development practices including authentication, cloud database integration, caching, asynchronous messaging, and API documentation.

---

# ✨ Features

### 👤 User Management

- User Registration
- Secure Authentication using Spring Security (Basic Authentication)
- Update User Profile
- Delete User Account

### 📔 Journal Management

- Create Journal Entries
- View All Personal Entries
- View Entry by ID
- Update Existing Entries
- Delete Journal Entries

### ☁ External Integrations

- Weather API Integration
- MongoDB Atlas Cloud Database
- Redis Cloud Caching
- Kafka Event Streaming
- Email Notifications

### 🔐 Security

- Spring Security
- Password Encryption (BCrypt)
- Protected APIs
- Role-Based Access

### 📑 Documentation

- Interactive Swagger UI
- OpenAPI 3 Documentation
- Request/Response Examples

---

# 🛠 Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| MongoDB Atlas | Cloud Database |
| Redis Cloud | Caching |
| Apache Kafka | Event Streaming |
| Maven | Dependency Management |
| Swagger / OpenAPI | API Documentation |
| Railway | Deployment Platform |
| Gmail SMTP | Email Service |

---

# 🏗 Architecture

```
                +----------------------+
                |      Swagger UI      |
                +----------+-----------+
                           |
                           |
                    Spring Security
                           |
        +------------------+------------------+
        |                                     |
   User APIs                           Journal APIs
        |                                     |
        +---------------+---------------------+
                        |
                  Service Layer
                        |
      +---------+-------+--------+---------+
      |         |                |         |
 MongoDB     Redis Cache      Kafka    Weather API
   Atlas                        |
                             Email Service
```

---

# 📂 Project Structure

```
src
 ├── config
 ├── controller
 ├── dto
 ├── entity
 ├── enums
 ├── repository
 ├── scheduler
 ├── service
 ├── api
 └── resources
```

---

# 📌 API Endpoints

## Public APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/public/create-user` | Register New User |

---

## User APIs

| Method | Endpoint |
|---------|----------|
| GET | `/user` |
| PUT | `/user` |

---

## Journal APIs

| Method | Endpoint |
|---------|----------|
| POST | `/journal` |
| GET | `/journal` |
| GET | `/journal/id/{id}` |
| PUT | `/journal/id/{id}` |
| DELETE | `/journal/id/{id}` |

---

## Admin APIs

| Method | Endpoint |
|---------|----------|
| GET | `/admin/all-users` |

---

# 🔐 Authentication

This project uses **Spring Security Basic Authentication**.

Example

Username

```
john
```

Password

```
password
```

Swagger automatically prompts for authentication before accessing protected APIs.

---

# ⚙ Environment Variables

The application requires the following environment variables.

```
MONGODB_URI

WEATHER_API_KEY

MAIL_USERNAME

MAIL_PASSWORD

REDIS_URL

KAFKA_BOOTSTRAP_SERVERS

KAFKA_JAAS_CONFIG

KAFKA_CLIENT_ID
```

---

# 🚀 Running Locally

Clone the repository

```bash
git clone https://github.com/Pratham7125/DailyScribe.git
```

Navigate into the project

```bash
cd DailyScribe
```

Configure environment variables.

Run the application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

# 📸 Screenshots

### Swagger UI

<img width="1917" height="892" alt="image" src="https://github.com/user-attachments/assets/df489ad3-0f32-455e-b057-3858efbf5a80" />


### MongoDB Atlas

<img width="1917" height="910" alt="image" src="https://github.com/user-attachments/assets/67b58f45-d411-4903-8659-f4278f738736" />


### Railway Deployment

<img width="1917" height="897" alt="image" src="https://github.com/user-attachments/assets/388a949d-5b59-4004-8088-6081388b6052" />


---

# 🌟 Future Enhancements

- JWT Authentication
- Refresh Tokens
- Docker Support
- GitHub Actions CI/CD
- Kubernetes Deployment
- Unit & Integration Testing
- User Profile Images
- Journal Search
- AI-based Sentiment Analysis Dashboard

---

# 👨‍💻 Author

**Pratham Chauhan**

Software Engineer

GitHub

https://github.com/Pratham7125

LinkedIn

https://www.linkedin.com/in/pratham-chauhan/

---

# ⭐ If you found this project useful

Please consider giving it a **Star ⭐** on GitHub.
