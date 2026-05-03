# EventSphere 🌍

## 📖 About the project

This project is a web service for organizing local events (meetups, sports meetings).  
The system allows you to:

- create events;
- manage the list of participants;
- use interactive maps to select locations;
- Automatically track weather conditions to plan outdoor activities.

---

## 🛠 Technology stack

- **Framework:** Spring Boot (MVC, Security, Data JPA)
- **Language:** Java 17+
- **Database:** PostgreSQL (migrations via Liquibase / Flyway)
- **Build System:** Maven
- **Frontend:** HTML5, CSS3, JavaScript (AJAX), Thymeleaf / JSP
- **API Integrations:** Weather API (OpenWeather / Yandex Weather), Maps API

---

## 🏗 Architecture

The project is based on the principles of **SOLID** and **MVC**.

### Basic structure:

```text
Controller -> Service -> Repository
```
