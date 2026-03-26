<img width="640" height="382" alt="image" src="https://github.com/user-attachments/assets/40717b51-4392-4e60-ae66-dd1ba4103c73" />

# 📈 stock-trade-sandbox

> **KR** | 주식 자동매매 시스템 기술 검증용 샌드박스입니다.  
> 팀 종합설계 프로젝트 적용 전, 핵심 기술을 사전 검증하고 실습한 레포입니다.

> **EN** | A sandbox repository for validating core technologies of an automated stock trading system.  
> Built to explore and verify key implementations before applying them to a team capstone project.

---

## ✅ 구현 완료 | Implemented Features

| 기능 | 설명 |
|------|------|
| KIS API 토큰 발급 | 한국투자증권 모의투자 OAuth2 액세스 토큰 발급 및 캐싱 |
| 잔고 조회 | 모의투자 계좌 잔고 및 출금가능금액 조회 |
| 현재가 조회 | 종목코드 기반 실시간 현재가 조회 |
| 자동 매수/매도 | 가격 조건 기반 자동 주문 실행 |
| 1시간 주기 스케줄러 | @Scheduled로 1시간마다 현재가 조회 및 조건 체크 |
| React 프론트엔드 | 주식 가격 대시보드 + 매수/매도 버튼 UI |
| Spring ↔ React 연동 | CORS 설정 + REST API 연결 |

---

## 🛠 Tech Stack

| Layer | Tech |
|-------|------|
| Backend | Spring Boot 3, Spring Data JPA, Spring Scheduler, WebFlux |
| Frontend | React 18, TypeScript |
| Database | MySQL 8.0 (Docker) |
| External API | 한국투자증권 KIS Open API (모의투자) |
| Infra | Docker Compose, GitHub Actions |
| Language | Java 21, TypeScript |

---

## 📁 Project Structure
```
stock-trade-sandbox/
├── src/
│   └── main/java/com/sandbox/stock_trade/
│       ├── kis/
│       │   ├── KisConfig.java               # API 키 설정값 바인딩
│       │   ├── KisTokenClient.java          # 액세스 토큰 발급 및 캐싱
│       │   ├── KisTokenResponse.java        # 토큰 응답 DTO
│       │   ├── KisStockClient.java          # 잔고/현재가 조회
│       │   ├── KisBalanceResponse.java      # 잔고 응답 DTO
│       │   ├── KisCurrentPriceResponse.java # 현재가 응답 DTO
│       │   ├── KisOrderClient.java          # 매수/매도 주문 실행
│       │   ├── KisOrderRequest.java         # 주문 요청 DTO
│       │   ├── KisOrderResponse.java        # 주문 응답 DTO
│       │   ├── KisController.java           # REST API 엔드포인트
│       │   └── AutoTradingScheduler.java    # 1시간 주기 자동매매 스케줄러
│       ├── CorsConfig.java                  # CORS 설정
│       └── StockTradeApplication.java
├── frontend/
│   └── src/
│       ├── api/
│       │   └── kisApi.ts                    # Spring API 호출 함수 모음
│       ├── components/
│       │   ├── StockCard.tsx                # 종목 가격 카드 컴포넌트
│       │   └── TradePanel.tsx               # 매수/매도 버튼 패널
│       └── App.tsx                          # 메인 화면 (잔고 + 현재가 + 거래)
├── docker-compose.yml
└── README.md
```

---

## 🚀 Getting Started

### 사전 준비 | Requirements

- Java 21
- Node.js 18+
- Docker Desktop
- 한국투자증권 계좌 + KIS Developers APP KEY/SECRET ([발급받기](https://apiportal.koreainvestment.com))

---

### 1. 설정 파일 생성 | Configuration

`src/main/resources/application.yml` 파일을 직접 생성해야 합니다.  
(보안상 `.gitignore`에 포함되어 있어 레포에 올라가지 않습니다.)

`application-example.yml`을 참고해서 아래 내용으로 만들어주세요:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/stock_trade
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true

kis:
  app-key: 발급받은_APP_KEY
  app-secret: 발급받은_APP_SECRET
  base-url: https://openapivts.koreainvestment.com:29443
```

---

### 2. MySQL 실행 | Run MySQL
```bash
docker-compose up -d
```

---

### 3. 백엔드 실행 | Run Backend

IntelliJ에서 `StockTradeApplication.java` 실행  
또는 터미널에서:
```bash
./gradlew bootRun
```

백엔드 주소: `http://localhost:8080`

---

### 4. 프론트엔드 실행 | Run Frontend
```bash
cd frontend
npm install
npm start
```

프론트엔드 주소: `http://localhost:3000`

---

## 🌐 API Endpoints

| Method | URL | 설명 |
|--------|-----|------|
| GET | `/kis/token` | 액세스 토큰 발급 |
| GET | `/kis/balance?accountNo=&productCode=` | 잔고 조회 |
| GET | `/kis/price?stockCode=` | 현재가 조회 |
| POST | `/kis/trade/buy` | 삼성전자 1주 매수 |
| POST | `/kis/trade/sell` | 삼성전자 1주 매도 |
| POST | `/kis/trade/trigger` | 자동매매 조건 수동 체크 |

---

## 🌿 Branch Strategy

| Branch | 설명 |
|--------|------|
| `main` | 안정 버전만 병합 |
| `feat/#1-kis-token` | KIS 액세스 토큰 발급 |
| `feat/#3-kis-balance-price` | 잔고/현재가 조회 |
| `feat/#6-scheduled-auto-trading` | 자동매매 스케줄러 |
| `feat/#8-react-frontend` | React 프론트엔드 |

---

## ⚠️ 주의사항 | Notes

- 이 레포는 **모의투자 전용**입니다. `base-url`이 `openapivts`로 설정되어 있어 실제 계좌에 영향을 주지 않습니다.
- KIS API는 토큰 발급 제한이 있습니다. 짧은 시간 안에 토큰 발급을 너무 많이 요청할 경우, 새 토큰이 발급되지 않습니다.
- 주식 시장 운영시간(09:00~15:30) 외에는 현재가가 변동되지 않습니다.
- APP KEY/SECRET은 절대 커밋하지 마세요.

---

## 📌 참고 | References

- [KIS Developers API 문서](https://apiportal.koreainvestment.com)
