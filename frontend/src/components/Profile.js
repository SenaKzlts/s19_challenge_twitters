import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from './Sidebar';
import RightSidebar from './RightSidebar';
import './Profile.css';

function Profile() {
  const navigate = useNavigate();
  const userName = localStorage.getItem('userName') || 'kullanıcı';
  const userEmail = localStorage.getItem('userEmail') || 'user@example.com';
  
  const [tweets, setTweets] = useState([
    { id: 1, content: 'İlk tweetim! 🎉', time: '2s' },
    { id: 2, content: 'React öğreniyorum, çok eğlenceli! ⚛️', time: '1g' },
  ]);

  const stats = {
    tweets: 156,
    following: 234,
    followers: 567,
  };

  const handleLogout = () => {
    localStorage.clear();
    navigate('/login');
  };

  return (
    <div className="profile">
      <Sidebar />
      <div className="main-content">
        <div className="profile-header">
          <div className="profile-banner"></div>
          <div className="profile-info">
            <div className="profile-avatar-large">
              <span>{userName.charAt(0).toUpperCase()}</span>
            </div>
            <div className="profile-details">
              <h1>{userName}</h1>
              <p className="profile-handle">@{userName}</p>
              <p className="profile-email">{userEmail}</p>
              <p className="profile-bio">Merhaba! Ben bir yazılım geliştiricisiyim. 💻</p>
            </div>
            <button className="edit-profile-btn">Profili Düzenle</button>
          </div>
        </div>

        <div className="profile-stats">
          <div className="stat-item">
            <span className="stat-number">{stats.tweets}</span>
            <span className="stat-label">Tweet</span>
          </div>
          <div className="stat-item">
            <span className="stat-number">{stats.following}</span>
            <span className="stat-label">Takip Edilen</span>
          </div>
          <div className="stat-item">
            <span className="stat-number">{stats.followers}</span>
            <span className="stat-label">Takipçi</span>
          </div>
        </div>

        <div className="profile-tabs">
          <button className="tab active">Tweetler</button>
          <button className="tab">Yanıtlar</button>
          <button className="tab">Medya</button>
          <button className="tab">Beğeniler</button>
        </div>

        <div className="profile-tweets">
          {tweets.map((tweet) => (
            <div key={tweet.id} className="tweet-item">
              <div className="tweet-avatar">
                <span>{userName.charAt(0).toUpperCase()}</span>
              </div>
              <div className="tweet-content">
                <div className="tweet-header">
                  <span className="tweet-user">{userName}</span>
                  <span className="tweet-handle">@{userName}</span>
                  <span className="tweet-time">· {tweet.time}</span>
                </div>
                <p>{tweet.content}</p>
                <div className="tweet-actions">
                  <button className="action-btn">💬</button>
                  <button className="action-btn">🔄</button>
                  <button className="action-btn">❤️</button>
                  <button className="action-btn">📤</button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
      <RightSidebar />
    </div>
  );
}

export default Profile;
