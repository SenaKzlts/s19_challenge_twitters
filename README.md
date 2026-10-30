# Twitter API Challenge

A Spring Boot backend with React frontend implementing Twitter-like functionality.

## Project Structure

- **Backend**: Spring Boot application running on port 3000
- **Frontend**: React application running on port 3200
- **Database**: PostgreSQL

## Backend Setup

### Option 1: Docker (Recommended)

#### Prerequisites
- Docker
- Docker Compose

#### Running with Docker
```bash
docker-compose up -d
```

This will start:
- **PostgreSQL** database on port 5433 (mapped to container port 5432)
- **Spring Boot app** on port 3000 (mapped to container port 8080)
- **pgAdmin** on port 5050 for database management

#### Stopping Docker
```bash
docker-compose down
```

#### Database Access via pgAdmin
- URL: http://localhost:5050
- Email: admin@example.com
- Password: admin123
- Host: postgres
- Port: 5432
- Database: twitter_db
- Username: twitter_user
- Password: twitter_password

### Option 2: Local Setup

#### Prerequisites
- Java 21
- Maven
- PostgreSQL

#### Configuration
The database configuration is in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/twitter_db
spring.datasource.username=twitter_user
spring.datasource.password=twitter_password
```

Update these values to match your PostgreSQL setup.

#### Running the Backend
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:3000`

## Frontend Setup

### Prerequisites
- Node.js
- npm

### Installation
```bash
cd frontend
npm install
```

### Running the Frontend
```bash
npm start
```

The frontend will start on `http://localhost:3200`

## API Endpoints

### Authentication
- `POST /auth/register` - Register a new user
- `POST /auth/login` - Login a user

### Tweets
- `GET /tweet` - Get all tweets (home feed)
- `POST /tweet` - Create a tweet
- `GET /tweet/findByUserId?userId={id}` - Get all tweets by user ID
- `GET /tweet/findById?id={id}` - Get tweet by ID
- `PUT /tweet/{id}?userId={id}` - Update a tweet (only owner)
- `DELETE /tweet/{id}?userId={id}` - Delete a tweet (only owner)

### Comments
- `GET /comment/findByTweetId?tweetId={id}` - Get all comments for a tweet
- `POST /comment` - Create a comment
- `PUT /comment/{id}?userId={id}` - Update a comment (only owner)
- `DELETE /comment/{id}?userId={id}` - Delete a comment (owner or tweet owner)

### Likes
- `GET /like/findByTweetId?tweetId={id}` - Get all likes for a tweet
- `POST /like` - Like a tweet
- `POST /dislike?userId={id}&tweetId={id}` - Remove a like

### Retweets
- `POST /retweet` - Retweet a tweet
- `DELETE /retweet/{id}` - Delete a retweet

### Followers
- `POST /api/followers/follow?followerId={id}&followingId={id}` - Follow a user
- `POST /api/followers/unfollow?followerId={id}&followingId={id}` - Unfollow a user
- `GET /api/followers` - Get all followers
- `GET /api/followers/{id}` - Get follower by ID

## CORS Configuration

CORS is configured to allow requests from `http://localhost:3200` (the React frontend). This resolves the CORS error that would occur when the frontend tries to access the backend API.

## Running Tests

Run the unit tests:
```bash
mvn test
```

## Features Implemented

### Backend
- ✅ Entity validation with Jakarta Validation
- ✅ Controller/Service/Repository layered architecture
- ✅ Global Exception Handling
- ✅ Spring Security with basic configuration
- ✅ Authorization logic (only owners can update/delete their content)
- ✅ CORS configuration for React frontend
- ✅ Unit tests for TweetService, AuthService, and CommentsService
- ✅ GET endpoints for fetching all tweets, comments, and likes
- ✅ Follow/Unfollow functionality

### Frontend
- ✅ React Router for navigation between pages
- ✅ Login and Register pages with authentication
- ✅ Home Feed with all tweets
- ✅ Tweet creation with character limit (280)
- ✅ Like/Unlike functionality with visual feedback
- ✅ Comment functionality with inline input and display
- ✅ Retweet functionality with visual feedback
- ✅ Follow/Unfollow users from tweet cards
- ✅ Twitter-like modern UI with sidebar navigation
- ✅ Responsive design with Twitter color scheme
- ✅ Real-time updates (likes, comments, retweets)
