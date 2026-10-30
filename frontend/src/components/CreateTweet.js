import React, { useState } from 'react';
import './CreateTweet.css';

function CreateTweet({ onTweetCreated }) {
  const [content, setContent] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const userId = localStorage.getItem('userId');

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!content.trim()) {
      setError('Tweet cannot be empty');
      return;
    }

    if (content.length > 280) {
      setError('Tweet cannot exceed 280 characters');
      return;
    }

    setLoading(true);
    setError(null);

    try {
      const response = await fetch('http://localhost:3000/tweet', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          content,
          user: { id: userId }
        }),
      });

      if (!response.ok) {
        throw new Error('Failed to create tweet');
      }

      const newTweet = await response.json();
      setContent('');
      if (onTweetCreated) {
        onTweetCreated(newTweet);
      }
    } catch (err) {
      setError('Failed to create tweet');
    } finally {
      setLoading(false);
    }
  };

  const remainingChars = 280 - content.length;

  return (
    <div className="create-tweet">
      <form onSubmit={handleSubmit} className="tweet-form">
        <textarea
          placeholder="What's happening?"
          value={content}
          onChange={(e) => setContent(e.target.value)}
          className="tweet-input"
          maxLength={280}
          rows={3}
        />
        <div className="tweet-form-footer">
          <span className={`char-count ${remainingChars < 0 ? 'over-limit' : ''}`}>
            {remainingChars} characters remaining
          </span>
          <button
            type="submit"
            className="tweet-button"
            disabled={loading || !content.trim() || remainingChars < 0}
          >
            {loading ? 'Tweeting...' : 'Tweet'}
          </button>
        </div>
        {error && <div className="error-message">{error}</div>}
      </form>
    </div>
  );
}

export default CreateTweet;
