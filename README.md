# Customer Feedback Service 🛍️💬

A Spring Boot application for managing customer feedback for an e-commerce platform using MongoDB.

## Description 📝

This service allows customers to submit and retrieve product feedback, helping businesses gather valuable insights about their products and services. The application provides a RESTful API for managing feedback data with features like pagination, filtering, and sorting.

## Technologies Used 🛠️

- Java 11
- Spring Boot 3.0.2
- MongoDB 5.0
- Gradle 7.6
- Docker
- Lombok
- Spring Data MongoDB

## Prerequisites ⚙️

- Java 11 or higher
- Docker
- MongoDB Compass (optional, for DB visualization)

## Setup 🚀

1. Clone the repository:
```sh
git clone <repository-url>
```

2. Pull MongoDB Docker image:
```sh
docker pull mongo:5
```

3. Build the project:
```sh
./gradlew build
```

4. Run the application:
```sh
./gradlew bootRun
```

## API Endpoints 🔌

### Create Feedback
```http
POST /feedback

{
  "rating": <integer>,
  "feedback": <string, optional>,
  "customer": <string, optional>,
  "product": <string>,
  "vendor": <string>
}
```

### Get Feedback by ID
```http
GET /feedback/<id>
```

### Get All Feedback (Paginated)
```http
GET /feedback?page=<number>&perPage=<number>
```

## Response Format 📄

### Single Feedback
```json
{
  "id": "string",
  "rating": "integer",
  "feedback": "string | null",
  "customer": "string | null",
  "product": "string",
  "vendor": "string"
}
```

### Paginated Response
```json
{
  "total_documents": "long",
  "is_first_page": "boolean",
  "is_last_page": "boolean",
  "documents": [array of feedback objects]
}
```

## Database Configuration 💾

The application uses MongoDB with the following default configuration:
```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=feedback_db
```

## Testing 🧪

Run the tests using:
```sh
./gradlew test
```

## Project Structure 📁

- `src/main/java/feedbackservice/`
  - `Feedback.java` - Entity class
  - `FeedbackRepository.java` - MongoDB repository
  - `FeedbackController.java` - REST endpoints
  - `FeedbackService.java` - Business logic
  - `CustomPageResponse.java` - Pagination response wrapper
  - `FeedbackApplication.java` - Main application class
    
## Acknowledgments 🙏

- Built as part of the Hyperskill Java Backend Developer track
- Uses Spring Boot framework
- MongoDB for document storage

## Contributing 🤝

Contributions are welcome! Please feel free to submit a Pull Request.
