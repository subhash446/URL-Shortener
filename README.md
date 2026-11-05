# 🔗 BlinkURL — URL Shortener

<div align="center">

![BlinkURL Banner](url-shortener-react/public/images/img2.png)

**A full-stack URL Shortener application built with Spring Boot & React**

[![Live Demo](https://img.shields.io/badge/Live%20Demo-Vercel-black?style=for-the-badge&logo=vercel)](https://url-shortener-spring-boot.vercel.app)
[![Backend API](https://img.shields.io/badge/Backend%20API-Render-46E3B7?style=for-the-badge&logo=render)](https://url-shortener-sb-xovc.onrender.com)
[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-61DAFB?style=for-the-badge&logo=react&logoColor=black)](https://react.dev/)
[![Tests](https://img.shields.io/badge/Tests-25%20passing-brightgreen?style=for-the-badge&logo=junit5)](url-shortner-sb/src/test/java/com/url/shortener/util/Base62UtilTest.java)

</div>

---

## 📌 Table of Contents

- [About](#-about)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [API Endpoints](#-api-endpoints)
- [Getting Started](#-getting-started)
- [Environment Variables](#-environment-variables)
- [Testing](#-testing)
- [Deployment](#-deployment)
- [Project Structure](#-project-structure)

---

## 🧾 About

**BlinkURL** (also known as Linklytics) is a full-stack URL shortening service that lets users create short, memorable links and track their performance. Users can register, log in, shorten URLs, and monitor click analytics — all from a clean, responsive dashboard.

> 🌐 **Live App**: [url-shortener-spring-boot.vercel.app](https://url-shortener-spring-boot.vercel.app)

---

## ✨ Features

- 🔐 **User Authentication** — Secure JWT-based register & login
- ✂️ **URL Shortening** — Generate short URLs using Base62 encoding from DB-generated IDs
- 📊 **Analytics Dashboard** — Track click counts per URL with date-range filtering
- 📅 **Click Event History** — View detailed click events with timestamps
- 🔁 **Auto Redirect** — Short URLs redirect to original URLs with HTTP 302
- 🌐 **Subdomain Routing** — Supports both `/s/:slug` and subdomain-based routing
- 🐳 **Dockerized Backend** — Ready-to-deploy Docker image for the Spring Boot backend
- ☁️ **Cloud Deployed** — Frontend on Vercel, Backend on Render

---

## 🛠 Tech Stack

### Backend
| Technology | Description |
|---|---|
| **Java 21** | Core language |
| **Spring Boot 4.0.3** | Application framework |
| **Spring Security** | Authentication & Authorization |
| **Spring Data JPA** | ORM / Database layer |
| **JWT (jjwt 0.13.0)** | Stateless token-based auth |
| **MySQL / PostgreSQL** | Relational database (configurable) |
| **Lombok** | Boilerplate reduction |
| **Docker** | Containerization |
| **Maven** | Build tool |

### Frontend
| Technology | Description |
|---|---|
| **React 18** | UI framework |
| **Vite** | Build tool & dev server |
| **Tailwind CSS** | Utility-first styling |
| **MUI (Material UI)** | Component library |
| **React Router v7** | Client-side routing |
| **React Query** | Server state management |
| **Axios** | HTTP client |
| **Chart.js + react-chartjs-2** | Analytics charts |
| **React Hook Form** | Form management |
| **Framer Motion** | Animations |
| **React Hot Toast** | Notifications |

---

## 🏗 Architecture

```
┌─────────────────────────────────────────────────────────┐
│                      Client (React)                      │
│          Vercel → url-shortener-spring-boot.vercel.app   │
└────────────────────────┬────────────────────────────────┘
                         │  HTTPS / REST API (Axios)
                         ▼
┌─────────────────────────────────────────────────────────┐
│               Spring Boot Backend (Java 21)              │
│           Render → url-shortener-sb-xovc.onrender.com    │
│                                                          │
│   ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│   │ AuthController│  │UrlMapCtrl   │  │RedirectCtrl  │  │
│   └──────┬───────┘  └──────┬───────┘  └──────┬───────┘  │
│          │                 │                  │          │
│   ┌──────▼─────────────────▼──────────────────▼───────┐  │
│   │             Service Layer + JPA Repos              │  │
│   └──────────────────────┬────────────────────────────┘  │
└─────────────────────────-│──────────────────────────────┘
                           │  JDBC
                           ▼
              ┌────────────────────────┐
              │   MySQL / PostgreSQL    │
              └────────────────────────┘
```

---

## 📡 API Endpoints

### Auth — `/api/auth`
| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| `POST` | `/api/auth/public/register` | Public | Register a new user |
| `POST` | `/api/auth/public/login` | Public | Login and receive JWT token |

### URLs — `/api/urls`
| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| `POST` | `/api/urls/shorten` | 🔒 Authenticated | Create a shortened URL |
| `GET` | `/api/urls/myurls` | 🔒 Authenticated | Get all URLs for logged-in user |
| `GET` | `/api/urls/analytics/{shortUrl}?startDate=&endDate=` | 🔒 Authenticated | Get click events for a specific URL |
| `GET` | `/api/urls/totalClicks?startDate=&endDate=` | 🔒 Authenticated | Get total clicks grouped by date |

### Redirect
| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| `GET` | `/{shortUrl}` | Public | Redirect to original URL (HTTP 302) |

---

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Node.js 18+
- MySQL or PostgreSQL
- Maven

---

### 1. Clone the Repository

```bash
git clone https://github.com/subhash446/URL-Shortener.git
cd URL-Shortener
```

---

### 2. Backend Setup (`url-shortner-sb`)

```bash
cd url-shortner-sb
```

Create a `.env` file (see [Environment Variables](#-environment-variables) below), then run:

```bash
./mvnw spring-boot:run
```

The backend will start at `http://localhost:8080`.

---

### 3. Frontend Setup (`url-shortener-react`)

```bash
cd url-shortener-react
npm install
npm run dev
```

The frontend will start at `http://localhost:5173`.

---

### 4. Run with Docker (Backend Only)

```bash
cd url-shortner-sb
docker build -t blinkurl-backend .
docker run -p 8080:8080 --env-file .env blinkurl-backend
```

---

## 🔐 Environment Variables

### Backend — `url-shortner-sb/.env`

```env
DATABASE_URL=jdbc:mysql://localhost:3306/url_shortener
DATABASE_USERNAME=your_db_username
DATABASE_PASSWORD=your_db_password
DATABASE_DIALECT=org.hibernate.dialect.MySQLDialect

JWT_SECRET=your_super_secret_jwt_key

FRONTEND_URL=http://localhost:5173
```

> For PostgreSQL, change `DATABASE_URL` to `jdbc:postgresql://...` and `DATABASE_DIALECT` to `org.hibernate.dialect.PostgreSQLDialect`.

---

### Frontend — `url-shortener-react/.env`

```env
VITE_BACKEND_URL=http://localhost:8080
VITE_REACT_FRONT_END_URL=http://localhost:5173
VITE_REACT_SUBDOMAIN=http://localhost:5173
```

---

## 🧪 Testing

The project includes **JUnit 5** unit tests for core business logic.

### Running Tests

```bash
cd url-shortner-sb
./mvnw test
```

### Test Coverage

| Test Class | Tests | What is covered |
|---|---|---|
| [`Base62UtilTest`](url-shortner-sb/src/test/java/com/url/shortener/util/Base62UtilTest.java) | **25** | encode, decode, round-trip, edge cases & error handling |

**Key scenarios tested:**
- ✅ `encode(0)` → `"0"`, `encode(61)` → `"Z"`, `encode(62)` → `"10"`
- ✅ Parameterised checks across known ID→code pairs
- ✅ `decode` correctly reverses all encoded values
- ✅ Round-trip: `decode(encode(id)) == id` for multiple IDs
- ✅ `encode(null)` / `encode(-1)` → `IllegalArgumentException`
- ✅ `decode("")` / `decode(null)` / invalid chars → `IllegalArgumentException`

---

## ☁️ Deployment

| Component | Platform | URL |
|-----------|----------|-----|
| **Frontend** | [Vercel](https://vercel.com) | [url-shortener-spring-boot.vercel.app](https://url-shortener-spring-boot.vercel.app) |
| **Backend** | [Render](https://render.com) | [url-shortener-sb-xovc.onrender.com](https://url-shortener-sb-xovc.onrender.com) |

---

## 📁 Project Structure

```
URL-Shortener/
│
├── url-shortner-sb/                  # Spring Boot Backend
│   ├── src/main/java/com/url/shortener/
│   │   ├── controller/               # REST Controllers
│   │   │   ├── AuthController.java
│   │   │   ├── UrlMappingController.java
│   │   │   └── RedirectController.java
│   │   ├── models/                   # JPA Entities
│   │   │   ├── User.java
│   │   │   ├── UrlMapping.java
│   │   │   └── ClickEvent.java
│   │   ├── service/                  # Business Logic
│   │   │   ├── UserService.java
│   │   │   ├── UrlMappingService.java
│   │   │   └── UserDetailsServiceImpl.java
│   │   ├── repository/               # JPA Repositories
│   │   ├── security/                 # JWT + Spring Security
│   │   │   ├── WebSecurityConfig.java
│   │   │   └── jwt/
│   │   └── dtos/                     # Data Transfer Objects
│   ├── Dockerfile
│   └── pom.xml
│
└── url-shortener-react/              # React Frontend
    ├── src/
    │   ├── components/               # UI Components
    │   │   ├── LandingPage.jsx
    │   │   ├── Dashboard/
    │   │   ├── LoginPage.jsx
    │   │   ├── RegisterPage.jsx
    │   │   └── ShortenUrlPage.jsx
    │   ├── api/api.js                # Axios instance
    │   ├── contextApi/               # Global State (Context API)
    │   ├── hooks/                    # Custom Hooks
    │   └── utils/                    # Constants & Helpers
    ├── package.json
    └── vite.config.js
```

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

---

<div align="center">
  Made with ❤️ by <a href="https://github.com/subhash446">subhash446</a>
</div>
