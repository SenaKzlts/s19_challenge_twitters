import React, { useState, useEffect } from 'react';
import './UserTweets.css';

function UserTweets() {
  const [userId, setUserId] = useState('');
  const [tweets, setTweets] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchTweets = async () => {
    if (!userId) {
      setError('Please enter a user ID');
      return;
    }

    setLoading(true);
    setError(null);

    try {
      const response = await fetch(`http://localhost:3000/tweet/findByUserId?userId=${userId}`);
      
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      setTweets(data);
    } catch (err) {
      setError(err.message);
      console.error('Error fetching tweets:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetchTweets();
  };

  return (
    <div className="UserTweets">
      <h2>User Tweets</h2>
      <form onSubmit={handleSubmit} className="search-form">
        <input
          type="number"
          placeholder="Enter User ID"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
          className="user-input"
        />
        <button type="submit" className="search-button">
          Get Tweets
        </button>
      </form>

      {error && <div className="error-message">Error: {error}</div>}

      {loading && <div className="loading">Loading tweets...</div>}

      <div className="tweets-container">
        {tweets.length > 0 ? (
          tweets.map((tweet) => (
            <div key={tweet.id} className="tweet-card">
              <div className="tweet-content">
                <strong>User ID:</strong> {tweet.user?.id || 'N/A'}<br />
                <strong>Content:</strong> {tweet.content}<br />
                <strong>Created At:</strong> {tweet.createdAt ? new Date(tweet.createdAt).toLocaleString() : 'N/A'}
              </div>
            </div>
          ))
        ) : (
          !loading && !error && <div className="no-tweets">No tweets found</div>
        )}
      </div>
    </div  >
  );
}

export default UserTweets;
