# 🎵 Musify Backend API

## 📌 Overview
**Musify Backend** is the server-side application that powers the Musify music platform.  
It provides RESTful APIs for managing albums, songs, and media resources used by the Musify Admin and User frontend applications.

The backend handles data storage, business logic, and API communication between the frontend applications and the database.

This project is built using **Spring Boot** and integrates with **MongoDB** to manage music-related data efficiently.

---

## 🚀 Features

- 🎵 Album management APIs
- 🎧 Song upload and management
- 📂 Media storage handling
- 🔗 REST API integration with frontend applications
- ⚡ Fast and scalable backend using Spring Boot
- 🗄 MongoDB database integration
- 🔒 Structured backend architecture

---

## 🛠 Tech Stack

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data MongoDB

### Database
- MongoDB

### Tools
- Maven
- Postman (API testing)

---

## 📂 Project Structure

```
Musify_Backend
│
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── musify
│       │           ├── controller
│       │           ├── service
│       │           ├── repository
│       │           └── model
│       │
│       └── resources
│           ├── application.properties
│
├── pom.xml
└── README.md
```

---

## ⚙️ Installation

Clone the repository:

```bash
git clone https://github.com/RajatPandey10/Musify_Backend.git
```

Navigate to the project directory:

```bash
cd Musify_Backend
```

Build the project:

```bash
mvn clean install
```

---

## ▶️ Usage

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

The backend server will start on:

```
http://localhost:8080
```

---

## 🔗 Related Repositories

### Admin Dashboard
Frontend admin panel for managing albums and songs.

```
https://github.com/RajatPandey10/Musify_Frontend_Admin

```

---

### User Application
Frontend user interface for browsing and streaming music.

```
https://github.com/RajatPandey10/Musify_Frontend_Users
```

---

## 🌍 API Example

Example API endpoint:

```
GET /api/albums
```

Returns the list of available music albums.

---

## 🔮 Future Improvements

- JWT Authentication
- User management system
- Playlist APIs
- Music recommendation system
- Cloud storage for media files

---

## 👨‍💻 Author

**Rajat Pandey**

🎓 Computer Science Student  
💻 Interested in Web Development and Java Backend  

GitHub:  
https://github.com/RajatPandey10
