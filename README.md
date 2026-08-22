# 🍽️ G&N Food

> A full-stack restaurant management web application — public website + admin dashboard, built with Spring Boot.

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-336791?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?logo=docker&logoColor=white)
![Status](https://img.shields.io/badge/status-live-brightgreen)

**🌐 Live Demo:** [gnfood.duckdns.org](http://gnfood.duckdns.org)

---

## ✨ Features

### 🧑‍🍳 Public Website
- 🏠 Home, About, Menu, Events, Chefs, Gallery, Contact pages
- 📅 Table reservation system
- 💬 Customer testimonials

### 🔐 Admin Panel
- 🍔 Manage menu categories & products
- 👨‍🍳 Manage chefs, events, and gallery
- ⭐ Manage testimonials & reservations
- 🖼️ Image upload support

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| 🧩 Backend | Java 17, Spring Boot, Spring Security, Spring Data JPA (Hibernate) |
| 🗄️ Database | PostgreSQL |
| 🎨 View | Thymeleaf |
| 🔄 Mapping | MapStruct |
| 📦 Containerization | Docker & Docker Compose |
| ☁️ Deployment | Oracle Cloud (Always Free) + Nginx reverse proxy |

---

## 🚀 Running Locally with Docker

```bash
git clone https://github.com/gnaghiyeva/Food-project-MVC.git
cd Food-project-MVC
cp .env.example .env
docker compose up -d --build
```

🔗 App will be available at `http://localhost:9595`

---

## 📁 Project Structure
