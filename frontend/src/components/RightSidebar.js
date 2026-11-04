import React from 'react';
import './RightSidebar.css';

function RightSidebar() {
  const trendingTopics = [
    { category: 'Technology', topic: '#SpringBoot', tweets: '25.4K' },
    { category: 'Programming', topic: '#ReactJS', tweets: '18.2K' },
    { category: 'Technology', topic: '#Docker', tweets: '12.8K' },
    { category: 'Programming', topic: '#TypeScript', tweets: '9.5K' },
    { category: 'AI', topic: '#MachineLearning', tweets: '8.1K' },
  ];

  const suggestedUsers = [
    { name: 'twitterdev', handle: '@twitterdev', bio: 'Official Twitter Developer account' },
    { name: 'technews', handle: '@technews', bio: 'Latest technology news and updates' },
    { name: 'codinglife', handle: '@codinglife', bio: 'Programming tips and tricks' },
  ];

  return (
    <div className="right-sidebar">
      <div className="search-bar">
        <input type="text" placeholder="Twitter'da Ara" className="search-input" />
      </div>

      <div className="trending-section">
        <h3>Sizin İçin Gündem</h3>
        {trendingTopics.map((trend, index) => (
          <div key={index} className="trend-item">
            <div className="trend-category">{trend.category}</div>
            <div className="trend-topic">{trend.topic}</div>
            <div className="trend-tweets">{trend.tweets} tweet</div>
          </div>
        ))}
        <button className="show-more">Daha fazla göster</button>
      </div>

      <div className="who-to-follow-section">
        <h3>Kimi Takip Etmeli</h3>
        {suggestedUsers.map((user, index) => (
          <div key={index} className="user-item">
            <div className="user-avatar-small">
              <span>{user.name.charAt(0).toUpperCase()}</span>
            </div>
            <div className="user-info-small">
              <div className="user-name-small">{user.name}</div>
              <div className="user-handle-small">{user.handle}</div>
            </div>
            <button className="follow-btn-small">Takip Et</button>
          </div>
        ))}
        <button className="show-more">Daha fazla göster</button>
      </div>
    </div>
  );
}

export default RightSidebar;
