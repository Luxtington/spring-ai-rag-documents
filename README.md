# RAG Service

REST API для загрузки документов, индексирования их в Qdrant и ответов на вопросы по содержимому документов с использованием Spring AI.

## Возможности

- загрузка документов;
- разбиение текста на чанки;
- хранение эмбеддингов в Qdrant;
- хранение метаданных документов в H2;
- генерация ответа LLM только на основе найденного контекста;
- удаление документов.

---

# Запуск

## 1. Собрать приложение

```bash
mvn clean package -DskipTests
```

---

## 2. Запустить сервисы

```bash
docker compose up --build
```

Будут запущены:

- Spring Boot (порт **8080**)
- Qdrant (порты **6333**, **6334**)

После запуска API будет доступно по адресу

```
http://localhost:8080
```

---

# API

## Загрузка документа

```bash
curl -X POST http://localhost:8080/api/documents \
  -F "file=@C:/Users/<USERNAME>/Desktop/java.txt"
```

Ответ:

```json
{
  "id": "6a433c45-d8ac-4707-9ec3-9ff3eed287a1",
  "fileName": "java.txt",
  "status": "COMPLETED",
  "chunkCount": 1
}
```

---

## Получить список документов

```bash
curl http://localhost:8080/api/documents
```

Ответ:

```json
[
  {
    "id": "6a433c45-d8ac-4707-9ec3-9ff3eed287a1",
    "fileName": "java.txt",
    "status": "COMPLETED",
    "chunkCount": 1
  }
]
```

---

## Удалить документ

```bash
curl -X DELETE http://localhost:8080/api/documents/6a433c45-d8ac-4707-9ec3-9ff3eed287a1
```

После удаления:

- документ удаляется из H2;
- все его чанки удаляются из Qdrant.

---

## Задать вопрос

```bash
curl -X POST http://localhost:8080/v1/chat/completions ^
-H "Content-Type: application/json" ^
-d "{ \"model\":\"glm-4.7\", \"messages\":[{ \"role\":\"user\", \"content\":\"Кто создал Java?\" }]}"
```

Linux/macOS:

```bash
curl -X POST http://localhost:8080/v1/chat/completions \
-H "Content-Type: application/json" \
-d '{
  "model":"glm-4.7",
  "messages":[
    {
      "role":"user",
      "content":"Кто создал Java?"
    }
  ]
}'
```

Пример ответа:

```json
{
  "id": "chatcmpl-xxxx",
  "object": "chat.completion",
  "created": 1783103785,
  "model": "glm-4.7",
  "choices": [
    {
      "index": 0,
      "message": {
        "role": "assistant",
        "content": "Java была создана Джеймсом Гослингом в компании Sun Microsystems."
      },
      "finish_reason": "stop"
    }
  ]
}
```

---

# Архитектура

```
Документ
    │
    ▼
Парсер
    │
    ▼
Chunking
    │
    ▼
Embeddings
    │
    ▼
Qdrant

        Вопрос
          │
          ▼
Similarity Search
          │
          ▼
Релевантные чанки
          │
          ▼
Prompt
          │
          ▼
LLM
          │
          ▼
Ответ
```

---

# Используемые технологии

- Java 21
- Spring Boot 3
- Spring AI
- Qdrant
- H2 Database
- Docker
- Maven