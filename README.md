# Kafka Market Data Demo

A **Kafka demo project** built to study real-time financial data pipelines.  
In just 2 days, I implemented a **Producer → Kafka → Consumer → Service → REST API** architecture using Spring Boot and Docker.  
The goal is to experience asynchronous messaging with Kafka by ingesting external live market data (via Finnhub API).

---

## 📌 Project Overview
- **Purpose**: Learn and demonstrate Kafka-based real-time streaming architecture
- **Components**:
  - **Producer**: Fetches market data from Finnhub API and publishes to a Kafka topic
  - **Consumer**: Subscribes to Kafka topic and stores data in an in-memory service cache
  - **REST API (Spring Boot)**: Exposes the latest data through simple endpoints
- **Note**:
  - The current REST endpoints make it easy to test, but behind the scenes, the data flows through the full Kafka pipeline.  
  - This is a lightweight setup intended for learning purposes.

---

## 🏗 Architecture
```
[ Finnhub API ] ---> [ Producer (Spring Boot) ] ---> [ Kafka (Docker) ] ---> [ Consumer (Spring Boot) ] ---> [ Service Cache ] ---> [ Endpoint ]
```

---

## ⚙️ Tech Stack
- **Language**: Kotlin
- **Framework**: Spring Boot 3.x
- **Messaging**: Apache Kafka 3.6 (via Docker)
- **Infrastructure**: Docker, AWS Lightsail
- **Data Source**: Finnhub API (free tier key)

---

## 🚀 How to Run

### 1. Start Kafka
```bash
docker-compose up -d
```
- Zookeeper → port 2181  
- Kafka Broker → port 9092  

### 2. Build & Run Spring Boot
```bash
./gradlew bootJar
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
```

### 3. Call the API
```bash
curl "http://localhost:8080/api/fetch?symbol=AAPL"
```

Example response:
```json
{
  "symbol": "AAPL",
  "price": 252.31,
  "high": 255.74,
  "low": 251.04,
  "open": 255.22,
  "previousClose": 254.43,
  "timestamp": "2025-09-25T12:57:29.698223Z"
}
```

---

## 📊 Roadmap
- [ ] Add frontend (React-based dashboard) for real-time visualization  
- [ ] Implement gRPC endpoints in addition to REST API  
- [ ] Experiment with in-memory cache / Redis for scalability  
- [ ] Automate deployment with GitHub Actions + AWS Lightsail  

---

## ✨ Learning Points
- Running **Kafka as Docker containers** and integrating with Spring Boot  
- Implementing a **decoupled Producer/Consumer/Service** architecture  
- Demonstrating how REST APIs can be backed by Kafka messaging pipelines  
- Preparing a foundation for real-world extensions (gRPC, caching, CI/CD, frontend dashboard)  

---

## 📸 Demo Screenshots / Logs
Example logs while consuming from Kafka:
```
INFO  o.a.kafka.clients.NetworkClient : [Consumer clientId=consumer-market-data-group-1, groupId=market-data-group] Subscribed to topic(s): market-data
INFO  c.b.market.Consumer             : Received MarketData(symbol=AAPL, price=252.31, ...)
```

Example API call:
```
curl "http://localhost:8080/api/fetch?symbol=AAPL"
```
