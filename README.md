# Twitter Clone - Full Stack Application

A complete Twitter-like social media application built with Spring Boot backend and React frontend with PostgreSQL database. This project demonstrates modern full-stack development practices including RESTful APIs, authentication, and responsive UI design.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![React](https://img.shields.io/badge/React-18-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Testing](#testing)
- [Deployment](#deployment)
- [Contributing](#contributing)

## ✨ Features

### Authentication
- User registration with validation
- User login with email/password
- Session management with localStorage
- Protected routes

### Tweet Functionality
- Create tweets with 280 character limit
- View home feed with all tweets
- Like/unlike tweets with real-time updates
- Retweet functionality
- Comment on tweets
- Delete own tweets

### User Features
- Follow/unfollow users
- View user profiles
- User statistics (tweets, followers, following)
- Bookmarked tweets

### UI/UX
- Modern dark theme design
- Responsive layout
- Sidebar navigation
- Real-time interactions
- Turkish language support
- Twitter-like interface

## 🛠 Tech Stack

### Backend
- **Java 21** - Programming language
- **Spring Boot 3.x** - Application framework
- **Spring Data JPA** - ORM
- **Spring Security** - Security framework
- **PostgreSQL** - Database
- **Maven** - Build tool
- **Lombok** - Reduce boilerplate code
- **Jakarta Validation** - Input validation

### Frontend
- **React 18** - UI library
- **React Router** - Navigation
- **Axios** - HTTP client (fetch API)
- **CSS3** - Styling with custom dark theme

### DevOps
- **Docker** - Containerization
- **Docker Compose** - Multi-container orchestration
- **pgAdmin** - Database management

## 📁 Project Structure

```
s19_challenge_twitters/
├── src/
│   ├── main/
│   │   ├── java/com/example/twitter_challenge/
│   │   │   ├── Config/          # Configuration classes
│   │   │   ├── Controller/      # REST controllers
│   │   │   ├── DTO/             # Data Transfer Objects
│   │   │   ├── Entity/          # JPA entities
│   │   │   ├── Exception/       # Custom exceptions
│   │   │   ├── Repository/      # JPA repositories
│   │   │   ├── Service/         # Business logic
│   │   │   └── Utils/           # Utility classes
│   │   └── resources/
│   │       └── application.properties
│   └── test/                    # Unit tests
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── components/          # React components
│   │   ├── App.js              # Main app component
│   │   ├── index.js            # Entry point
│   │   └── index.css           # Global styles
│   └── package.json
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
```

## 🚀 Installation

### Prerequisites

- Docker and Docker Compose (recommended)
- OR Java 21, Maven, Node.js, npm, PostgreSQL

### Option 1: Docker (Recommended)

1. Clone the repository:
```bash
git clone <repository-url>
cd s19_challenge_twitters
```

2. Start all services:
```bash
docker-compose up -d
```

This will start:
- **PostgreSQL** on port 5433
- **Spring Boot API** on port 3000
- **React Frontend** on port 3200
- **pgAdmin** on port 5050

3. Access the application:
- Frontend: http://localhost:3200
- Backend API: http://localhost:3000
- pgAdmin: http://localhost:5050

### Option 2: Local Setup

#### Backend Setup

1. Configure PostgreSQL database:
```sql
CREATE DATABASE twitter_db;
CREATE USER twitter_user WITH PASSWORD 'twitter_password';
GRANT ALL PRIVILEGES ON DATABASE twitter_db TO twitter_user;
```

2. Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/twitter_db
spring.datasource.username=twitter_user
spring.datasource.password=twitter_password
```

3. Run the backend:
```bash
mvn spring-boot:run
```

#### Frontend Setup

1. Install dependencies:
```bash
cd frontend
npm install
```

2. Start the frontend:
```bash
npm start
```

## 📖 Usage

### Test Accounts

The application initializes with sample users (password: `password123`):

| Email | Username | Bio |
|-------|----------|-----|
| dev@twitter.com | twittergelistirici | Resmi Twitter Geliştirici Hesabı |
| news@tech.com | teknolojihaberleri | En son teknoloji haberleri ve güncellemeler |
| code@life.com | kodhayati | Programlama ipuçları ve püf noktaları |

### Navigation

- **Ana Sayfa (Home)** - View all tweets and create new ones
- **Keşfet (Explore)** - Trending topics and suggested users
- **Bildirimler (Notifications)** - Likes, retweets, and follows
- **Mesajlar (Messages)** - Direct messages
- **Yer İşaretleri (Bookmarks)** - Saved tweets
- **Profil (Profile)** - User profile and statistics

## 🔌 API Documentation

### Authentication

#### Register User
```http
POST /auth/register
Content-Type: application/json

{
  "userName": "string",
  "email": "string",
  "password": "string"
}
```

#### Login User
```http
POST /auth/login
Content-Type: application/json

{
  "email": "string",
  "password": "string"
}
```

### Tweets

#### Get All Tweets
```http
GET /tweet
```

#### Create Tweet
```http
POST /tweet
Content-Type: application/json

{
  "content": "string",
  "user": { "id": number }
}
```

#### Get Tweets by User
```http
GET /tweet/findByUserId?userId={id}
```

#### Delete Tweet
```http
DELETE /tweet/{id}?userId={id}
```

### Comments

#### Get Comments by Tweet
```http
GET /comment/findByTweetId?tweetId={id}
```

#### Create Comment
```http
POST /comment
Content-Type: application/json

{
  "commentText": "string",
  "tweet": { "id": number },
  "user": { "id": number }
}
```

### Likes

#### Get Likes by Tweet
```http
GET /like/findByTweetId?tweetId={id}
```

#### Like Tweet
```http
POST /like
Content-Type: application/json

{
  "tweet": { "id": number },
  "user": { "id": number }
}
```

#### Unlike Tweet
```http
POST /dislike?userId={id}&tweetId={id}
```

### Retweets

#### Retweet
```http
POST /retweet
Content-Type: application/json

{
  "originalTweet": { "id": number },
  "user": { "id": number }
}
```

#### Delete Retweet
```http
DELETE /retweet/{id}
```

### Followers

#### Follow User
```http
POST /api/followers/follow?followerId={id}&followingId={id}
```

#### Unfollow User
```http
POST /api/followers/unfollow?followerId={id}&followingId={id}
```

## 🗄 Database Schema

### Tables

- **users** - User accounts
- **tweets** - Tweet content
- **comments** - Tweet comments
- **likes** - Tweet likes
- **retweets** - Tweet retweets
- **followers** - User follow relationships
- **bookmarks** - Saved tweets
- **statistics** - User statistics

### Key Relationships

- User → Tweets (One-to-Many)
- User → Followers (Many-to-Many)
- Tweet → Comments (One-to-Many)
- Tweet → Likes (One-to-Many)
- Tweet → Retweets (One-to-Many)

## 🧪 Testing

### Backend Tests

Run all unit tests:
```bash
mvn test
```

Run specific test class:
```bash
mvn test -Dtest=AuthServiceTest
```

### Frontend Testing

The frontend can be tested manually by:
1. Opening http://localhost:3200
2. Registering a new account
3. Creating tweets
4. Interacting with other tweets (like, comment, retweet)
5. Testing navigation between pages

## 🐳 Docker Configuration

### Services

| Service | Port | Description |
|---------|------|-------------|
| postgres | 5433 | PostgreSQL database |
| app | 3000 | Spring Boot backend |
| pgadmin | 5050 | Database management UI |

### Environment Variables

```yaml
POSTGRES_DB: twitter_db
POSTGRES_USER: twitter_user
POSTGRES_PASSWORD: twitter_password
SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/twitter_db
SPRING_DATASOURCE_USERNAME: twitter_user
SPRING_DATASOURCE_PASSWORD: twitter_password
```

## 📦 Deployment

### Docker Deployment

```bash
docker-compose up -d --build
```

### Stopping Services

```bash
docker-compose down
```

### Viewing Logs

```bash
docker-compose logs -f app
```

## 🏗 Architecture

### Backend Architecture

- **Controller Layer** - REST API endpoints
- **Service Layer** - Business logic
- **Repository Layer** - Data access with JPA
- **Entity Layer** - Database models
- **Exception Handling** - Global error handling
- **Security** - Spring Security configuration

### Frontend Architecture

- **Component-based** - Reusable React components
- **State Management** - React hooks (useState, useEffect)
- **Routing** - React Router for navigation
- **API Integration** - Fetch API for backend communication
- **Styling** - CSS modules with dark theme

## 🎨 UI Features

- Dark theme design
- Responsive layout
- Twitter-like color scheme (#1d9bf0 primary)
- Smooth animations and transitions
- Real-time updates
- Turkish language interface
- Modern card-based layout

## 🔐 Security

- Password encryption with BCrypt
- Input validation with Jakarta Validation
- CORS configuration for frontend-backend communication
- Authorization checks (users can only modify their own content)
- SQL injection prevention with JPA

## 📝 License

This project is created for educational purposes.

## 👨‍💻 Author

Developed as a full-stack project demonstrating modern web development practices.

## 🙏 Acknowledgments

- Twitter for the design inspiration
- Spring Boot team for the excellent framework
- React team for the amazing UI library

