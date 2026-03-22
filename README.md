# 📈 stock-trade-sandbox

Sandbox for an automated stock trading system.  
Built to explore and validate core technologies before applying them to a team capstone project.

---

## ✨ Features

- Auto buy/sell order execution via KIS API
- Hourly market data fetching with scheduled tasks
- AI model integration via Spring → FastAPI bridge
- React (TypeScript) frontend connected to Spring REST API

---

## 🛠 Tech Stack

| Layer | Tech |
|-------|------|
| Backend | Spring Boot 3, Spring Data JPA, Spring Scheduler |
| AI Bridge | FastAPI (Python) |
| Frontend | React, TypeScript |
| Database | MySQL |
| Infra | GitHub Actions, Docker Compose |

---

## 📁 Project Structure
```
stock-trade-sandbox/
├── backend/          # Spring Boot
├── ai-bridge/        # FastAPI (Python)
├── frontend/         # React + TypeScript
└── docs/             # API specs, architecture diagrams
```

---

## 🌿 Branch Strategy

| Branch | Description |
|--------|-------------|
| `main` | Stable code only |
| `feat/#1-spring-fastapi-bridge` | Spring → FastAPI communication |
| `feat/#2-scheduled-data-fetch` | Hourly data fetching with @Scheduled |
| `feat/#3-kis-trading-api` | Auto buy/sell via KIS API |
| `feat/#4-react-spring-connect` | React (TS) + Spring REST API integration |

---

## 🚀 Getting Started

> 🚧 Setup instructions will be added as each feature is completed.

### Requirements
- Java 17
- Python 3.10+
- Node.js 18+
- MySQL 8.0

### Run (coming soon)
```bash
# backend
# ai-bridge  
# frontend
```

---

## 📌 Note

This is a personal learning sandbox.  
Each branch is independently runnable and documented for team reference.