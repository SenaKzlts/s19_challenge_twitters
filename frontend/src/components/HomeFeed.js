import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import CreateTweet from './CreateTweet';
import Sidebar from './Sidebar';
import RightSidebar from './RightSidebar';
import './HomeFeed.css';

function HomeFeed() {
  const [tweets, setTweets] = useState([]);
  const [likedTweets, setLikedTweets] = useState(new Set());
  const [likeCounts, setLikeCounts] = useState({});
  const [retweetedTweets, setRetweetedTweets] = useState(new Set());
  const [followingUsers, setFollowingUsers] = useState(new Set());
  const [comments, setComments] = useState({});
  const [showCommentInput, setShowCommentInput] = useState({});
  const [commentText, setCommentText] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const userId = localStorage.getItem('userId');

  const formatTimeAgo = (date) => {
    const seconds = Math.floor((new Date() - date) / 1000);
    
    if (seconds < 60) return 'now';
    if (seconds < 3600) return `${Math.floor(seconds / 60)}m`;
    if (seconds < 86400) return `${Math.floor(seconds / 3600)}h`;
    if (seconds < 604800) return `${Math.floor(seconds / 86400)}d`;
    return date.toLocaleDateString();
  };

  useEffect(() => {
    if (!userId) {
      navigate('/login');
      return;
    }
    fetchTweets();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [userId, navigate]);

  const fetchTweets = async () => {
    try {
      const response = await fetch('http://localhost:3000/tweet');
      if (!response.ok) {
        throw new Error('Failed to fetch tweets');
      }
      const data = await response.json();
      setTweets(data);
      
      const likedSet = new Set();
      const likeCounts = {};
      for (const tweet of data) {
        try {
          const likesResponse = await fetch(`http://localhost:3000/like/findByTweetId?tweetId=${tweet.id}`);
          if (likesResponse.ok) {
            const likes = await likesResponse.json();
            likeCounts[tweet.id] = likes.length;
            const userLiked = likes.some(like => like.user?.id === parseInt(userId));
            if (userLiked) {
              likedSet.add(tweet.id);
            }
          }
        } catch (err) {
          console.error('Error fetching likes:', err);
        }
      }
      setLikedTweets(likedSet);
      setLikeCounts(likeCounts);
      
      const commentsData = {};
      for (const tweet of data) {
        try {
          const commentsResponse = await fetch(`http://localhost:3000/comment/findByTweetId?tweetId=${tweet.id}`);
          if (commentsResponse.ok) {
            const tweetComments = await commentsResponse.json();
            commentsData[tweet.id] = tweetComments;
          }
        } catch (err) {
          console.error('Error fetching comments:', err);
        }
      }
      setComments(commentsData);
    } catch (err) {
      setError('Tweetler yüklenemedi');
    } finally {
      setLoading(false);
    }
  };

  const handleTweetCreated = (newTweet) => {
    setTweets([newTweet, ...tweets]);
  };

  const handleLike = async (tweetId) => {
    try {
      if (likedTweets.has(tweetId)) {
        await fetch(`http://localhost:3000/dislike?userId=${userId}&tweetId=${tweetId}`, {
          method: 'POST',
        });
        setLikedTweets(prev => {
          const newSet = new Set(prev);
          newSet.delete(tweetId);
          return newSet;
        });
        setLikeCounts(prev => ({
          ...prev,
          [tweetId]: (prev[tweetId] || 0) - 1
        }));
      } else {
        await fetch('http://localhost:3000/like', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            tweet: { id: tweetId },
            user: { id: parseInt(userId) }
          }),
        });
        setLikedTweets(prev => new Set([...prev, tweetId]));
        setLikeCounts(prev => ({
          ...prev,
          [tweetId]: (prev[tweetId] || 0) + 1
        }));
      }
    } catch (err) {
      console.error('Error handling like:', err);
    }
  };

  const handleComment = async (tweetId) => {
    const text = commentText[tweetId];
    if (!text || !text.trim()) {
      return;
    }

    try {
      const response = await fetch('http://localhost:3000/comment', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          commentText: text,
          tweet: { id: tweetId },
          user: { id: parseInt(userId) }
        }),
      });

      if (response.ok) {
        const newComment = await response.json();
        setComments(prev => ({
          ...prev,
          [tweetId]: [...(prev[tweetId] || []), newComment]
        }));
        setCommentText(prev => ({ ...prev, [tweetId]: '' }));
        setShowCommentInput(prev => ({ ...prev, [tweetId]: false }));
      }
    } catch (err) {
      console.error('Error creating comment:', err);
    }
  };

  const toggleCommentInput = (tweetId) => {
    setShowCommentInput(prev => ({ ...prev, [tweetId]: !prev[tweetId] }));
  };

  const handleRetweet = async (tweetId) => {
    try {
      if (retweetedTweets.has(tweetId)) {
        await fetch(`http://localhost:3000/retweet/${tweetId}`, {
          method: 'DELETE',
        });
        setRetweetedTweets(prev => {
          const newSet = new Set(prev);
          newSet.delete(tweetId);
          return newSet;
        });
      } else {
        await fetch('http://localhost:3000/retweet', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            originalTweet: { id: tweetId },
            user: { id: parseInt(userId) }
          }),
        });
        setRetweetedTweets(prev => new Set([...prev, tweetId]));
      }
    } catch (err) {
      console.error('Error handling retweet:', err);
    }
  };

  const handleFollow = async (targetUserId) => {
    try {
      if (followingUsers.has(targetUserId)) {
        await fetch(`http://localhost:3000/api/followers/unfollow?followerId=${userId}&followingId=${targetUserId}`, {
          method: 'POST',
        });
        setFollowingUsers(prev => {
          const newSet = new Set(prev);
          newSet.delete(targetUserId);
          return newSet;
        });
      } else {
        await fetch(`http://localhost:3000/api/followers/follow?followerId=${userId}&followingId=${targetUserId}`, {
          method: 'POST',
        });
        setFollowingUsers(prev => new Set([...prev, targetUserId]));
      }
    } catch (err) {
      console.error('Error handling follow:', err);
    }
  };

  const handleLogout = () => {
    localStorage.clear();
    navigate('/login');
  };

  if (loading) {
    return <div className="loading-container">Yükleniyor...</div>;
  }

  return (
    <div className="home-feed">
      <Sidebar />
      <div className="main-content">
        <header className="home-header">
          <h1>Ana Sayfa</h1>
        </header>
        
        <div className="feed-container">
          <CreateTweet onTweetCreated={handleTweetCreated} />
          
          {error && <div className="error-message">{error}</div>}
          
          {tweets.length === 0 ? (
            <div className="no-tweets">
              <h2>Henüz tweet yok</h2>
              <p>İlk tweeti sen at!</p>
            </div>
          ) : (
            tweets.map((tweet) => (
              <div key={tweet.id} className="tweet-card">
                <div className="tweet-header">
                  <div className="user-info">
                    <div className="user-avatar">
                      <span>{tweet.user?.userName ? tweet.user.userName.charAt(0).toUpperCase() : 'U'}</span>
                    </div>
                    <div className="user-details">
                      <span className="username">@{tweet.user?.userName || 'Unknown'}</span>
                      <span className="tweet-date">
                        {tweet.createdAt ? formatTimeAgo(new Date(tweet.createdAt)) : ''}
                      </span>
                    </div>
                  </div>
                  {tweet.user?.id !== parseInt(userId) && (
                    <button
                      className={`follow-button ${followingUsers.has(tweet.user?.id) ? 'following' : ''}`}
                      onClick={() => handleFollow(tweet.user?.id)}
                    >
                      {followingUsers.has(tweet.user?.id) ? 'Takip Ediliyor' : 'Takip Et'}
                    </button>
                  )}
                </div>
                <div className="tweet-content">
                  <p>{tweet.content}</p>
                </div>
                <div className="tweet-actions">
                  <button 
                    className="action-button comment-button"
                    onClick={() => toggleCommentInput(tweet.id)}
                  >
                    <span>💬</span>
                    <span>Yorum Yap</span>
                    {comments[tweet.id] && comments[tweet.id].length > 0 && (
                      <span className="action-count">{comments[tweet.id].length}</span>
                    )}
                  </button>
                  <button 
                    className={`action-button retweet-button ${retweetedTweets.has(tweet.id) ? 'retweeted' : ''}`}
                    onClick={() => handleRetweet(tweet.id)}
                  >
                    <span>🔄</span>
                    <span>{retweetedTweets.has(tweet.id) ? 'Retweetlendi' : 'Retweet'}</span>
                  </button>
                  <button 
                    className={`action-button like-button ${likedTweets.has(tweet.id) ? 'liked' : ''}`}
                    onClick={() => handleLike(tweet.id)}
                  >
                    <span>{likedTweets.has(tweet.id) ? '❤️' : '🤍'}</span>
                    <span>{likeCounts[tweet.id] || 0}</span>
                  </button>
                </div>
                
                {showCommentInput[tweet.id] && (
                  <div className="comment-section">
                    <div className="comment-input-wrapper">
                      <textarea
                        placeholder="Yorum yaz..."
                        value={commentText[tweet.id] || ''}
                        onChange={(e) => setCommentText(prev => ({ ...prev, [tweet.id]: e.target.value }))}
                        className="comment-input"
                        rows={2}
                      />
                      <button 
                        className="comment-submit-button"
                        onClick={() => handleComment(tweet.id)}
                      >
                        Gönder
                      </button>
                    </div>
                    {comments[tweet.id] && comments[tweet.id].length > 0 && (
                      <div className="comments-list">
                        {comments[tweet.id].map((comment) => (
                          <div key={comment.id} className="comment-item">
                            <span className="comment-user">@{comment.user?.userName || 'Unknown'}</span>
                            <span className="comment-text">{comment.commentText}</span>
                          </div>
                        ))}
                      </div>
                    )}
                  </div>
                )}
              </div>
            ))
          )}
        </div>
      </div>
      <RightSidebar />
    </div>
  );
}

export default HomeFeed;
