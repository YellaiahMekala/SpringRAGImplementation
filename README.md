# 🚀 Enterprise Spring RAG Implementation (`SpringRAGImplementation`)

[![Java](https://img.shields.io/badge/Java-17%20%7C%2021-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3%2B-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring AI](https://img.shields.io/badge/Spring%20AI-1.0.0%2B-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-ai)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-PGVector-336791?style=for-the-badge&logo=postgresql&logoColor=white)](https://github.com/pgvector/pgvector)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](LICENSE)

A production-grade, end-to-end **Retrieval-Augmented Generation (RAG)** reference architecture and implementation built with **Spring AI**, **Spring Boot 3**, and modern enterprise GenAI integration patterns.

This repository provides industry-standard implementations for document ingestion pipelines (ETL), vector embedding stores, metadata filtering, advanced retrieval advisors, conversational memory, structured outputs, and observable AI workflows.

---

## 📌 Key Architectural Pillars & Features

### 1. 🔄 Multi-Format ETL Ingestion Pipeline
- **Document Extractors:** Multi-source ingestion leveraging `PagePdfDocumentReader`, Apache Tika (`TikaDocumentReader`), `JsonReader`, `TextReader`, and Markdown parsers.
- **Smart Chunking & Tokenization:** Context-aware document partitioning using `TokenTextSplitter` and recursive character splitters to prevent semantic fragmentation.
- **Metadata Enrichment:** Automated tag injection (e.g., `doc_id`, `category`, `tenant_id`, `timestamp`, `chunk_index`) to power structured vector queries.

### 2. 🗄️ Vector Database & High-Dimensional Search
- **PGVector (PostgreSQL):** Production HNSW (Hierarchical Navigable Small World) indexing for millisecond-latency nearest-neighbor vector similarity.
- **Metadata Filtering:** Hybrid search combining semantic similarity with SQL-like metadata predicates (e.g., `department == 'Legal' && status == 'ACTIVE'`).
- **Extensible Vector Stores:** Plug-and-play abstractions supporting PGVector, ChromaDB, Milvus, and Redis Vector Store.

### 3. 🧠 Spring AI `ChatClient` & Modular Advisors Chain
- **Fluent Orchestration:** Modern `ChatClient` builder pattern for streamlined prompt composition and context grounding.
- **`QuestionAnswerAdvisor`:** Seamless contextual retrieval injection into prompt system instructions before model evaluation.
- **`VectorStoreChatMemoryAdvisor`:** Persistent conversational memory allowing LLMs to retain context across multi-turn sessions.

### 4. 🛠️ Agentic Tool & Function Calling
- **Spring `@Bean` Functions:** Dynamic tool execution allowing the LLM to trigger real-time database queries, live stock/weather services, or internal microservice endpoints when vector context requires external grounding.

### 5. 📊 Observability & Production Guardrails
- **Tracing & Metrics:** Deep integration with Spring Boot Actuator, Micrometer, and OpenTelemetry for latency, token consumption, and embedding cost tracking.
- **Hallucination Prevention:** Strict prompt templating (`StringTemplate`) enforcing grounded context responses and explicit fallback handling.

---

## 🛠️ Technology Stack

| Category | Technologies / Frameworks |
|---|---|
| **Core Runtime** | Java 17 / Java 21, Spring Boot 3.3.x, Spring WebFlux |
| **GenAI Orchestration** | Spring AI Framework (`1.0.0+`), LangChain4j (comparative reference) |
| **Model Providers** | OpenAI (`gpt-4o`, `text-embedding-3-small`), Ollama (`llama3`, `nomic-embed-text`) |
| **Vector Storage** | PostgreSQL 16+ with `pgvector` extension, ChromaDB |
| **Observability** | Spring Boot Actuator, Micrometer, OpenTelemetry |
| **Containers & Build** | Docker, Docker Compose, Apache Maven |

---


---

## ⚙️ Prerequisites

- **Java Development Kit (JDK 17 or 21)**
- **Apache Maven 3.8+**
- **Docker Desktop** (for running PostgreSQL with `pgvector` and Ollama)
- **OpenAI API Key** *(Optional if running fully local models via Ollama)*

---

## 🚀 Getting Started

### 1. Clone the Repository
```bash

git clone [https://github.com/YellaiahMekala/SpringRAGImplementation.git](https://github.com/YellaiahMekala/SpringRAGImplementation.git)
cd SpringRAGImplementation

---

## ⚙️ Prerequisites

- **Java Development Kit (JDK 17 or 21)**
- **Apache Maven 3.8+**
- **Docker Desktop** (for running PostgreSQL with `pgvector` and Ollama)
- **OpenAI API Key** *(Optional if running fully local models via Ollama)*

---

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone [https://github.com/YellaiahMekala/SpringRAGImplementation.git](https://github.com/YellaiahMekala/SpringRAGImplementation.git)
cd SpringRAGImplementation


## 📁 Repository Structure

SpringRAGImplementation/
├── docker/
│   ├── docker-compose.yml              # PostgreSQL + pgvector & Ollama services
│   └── init-pgvector.sql               # Extension setup and vector table DDL
├── src/
│   ├── main/
│   │   ├── java/com/example/rag/
│   │   │   ├── config/                 # VectorStore, EmbeddingModel & ChatClient Beans
│   │   │   ├── controller/             # REST Endpoints (Ingestion, Search, Chat, RAG)
│   │   │   ├── dto/                    # Strongly-typed Records for Query/Response
│   │   │   ├── functions/              # LLM Tools / @Bean Function calling hooks
│   │   │   ├── pipeline/               # Document Readers, TokenTextSplitter & ETL Loaders
│   │   │   ├── service/                # RAG Orchestration, Advisor Chains & Search Service
│   │   │   └── SpringRagApplication.java
│   │   └── resources/
│   │       ├── application.yml         # Application profiles & AI model configurations
│   │       ├── documents/              # Sample PDFs, Markdown, and JSON data for ingestion
│   │       └── prompts/                # External prompt templates (.st files)
├── pom.xml
└── README.md


2. Launch Local Vector Database
Start PostgreSQL with the pgvector extension enabled:


Bash
docker-compose -f docker/docker-compose.yml up -d
3. Set Up API Keys & Environment Variables
Bash
# macOS / Linux
export OPENAI_API_KEY="your-actual-openai-api-key"


# Windows (PowerShell)
$env:OPENAI_API_KEY="your-actual-openai-api-key"
4. Build and Run the Service
Bash
mvn clean package -DskipTests
mvn spring-boot:run
The application will start on http://localhost:8080.

🧪 Key REST API Endpoints & Testing

1. Ingest Documents into Vector Store (ETL)
POST /api/v1/rag/ingest

Bash
curl -X POST http://localhost:8080/api/v1/rag/ingest \
  -H "Content-Type: application/json" \
  -d '{
    "documentPath": "documents/enterprise-policy.pdf",
    "category": "policy",
    "tenantId": "tenant-001"
  }'


2. Similarity Vector Search (Semantic Query)
POST /api/v1/rag/search

Bash
curl -X POST http://localhost:8080/api/v1/rag/search \
  -H "Content-Type: application/json" \
  -d '{
    "query": "What are the reimbursement guidelines?",
    "topK": 3,
    "similarityThreshold": 0.75
  }'


3. Context-Augmented Q&A (RAG Ask)
POST /api/v1/rag/ask

Bash
curl -X POST http://localhost:8080/api/v1/rag/ask \
  -H "Content-Type: application/json" \
  -d '{
    "question": "What is the maximum claim amount for international travel?"
  }'


4. Reactive Streaming RAG Response (SSE)
GET /api/v1/rag/stream?question=Summarize section 4 of the compliance document

```

***

👨‍💻 Author
Yellaiah Mekala

Java Full-Stack & AI Backend Integration Engineer

GitHub: @YellaiahMekala - https://www.google.com/search?q=https://github.com/YellaiahMekala

LinkedIn: @Yellaiah Mekala - https://www.linkedin.com/in/yellaiah-mekala/


