import React from 'react';
import Sidebar from './Sidebar';
import RightSidebar from './RightSidebar';
import './Explore.css';

function Explore() {
  const trendingTopics = [
    { category: 'Teknoloji', topic: '#SpringBoot', tweets: '25.4B' },
    { category: 'Programlama', topic: '#ReactJS', tweets: '18.2B' },
    { category: 'Teknoloji', topic: '#Docker', tweets: '12.8B' },
    { category: 'Programlama', topic: '#TypeScript', tweets: '9.5B' },
    { category: 'Yapay Zeka', topic: '#MachineLearning', tweets: '8.1B' },
    { category: 'Teknoloji', topic: '#JavaScript', tweets: '7.3B' },
    { category: 'Programlama', topic: '#Python', tweets: '6.9B' },
    { category: 'Yapay Zeka', topic: '#AI', tweets: '5.4B' },
  ];

  return (
    <div className="explore">
      <Sidebar />
      <div className="main-content">
        <header className="explore-header">
          <h1>Keşfet</h1>
        </header>
        
        <div className="explore-content">
          <div className="search-bar-large">
            <input type="text" placeholder="Twitter'da Ara" className="search-input-large" />
          </div>

          <div className="trending-section-large">
            <h2>Gündemdekiler</h2>
            {trendingTopics.map((trend, index) => (
              <div key={index} className="trend-item-large">
                <div className="trend-category">{trend.category} · Gündem</div>
                <div className="trend-topic">{trend.topic}</div>
                <div className="trend-tweets">{trend.tweets} Tweet</div>
              </div>
            ))}
          </div>

          <div className="suggested-section">
            <h2>Önerilen Hesaplar</h2>
            <div className="suggested-user-card">
              <div className="user-avatar-large">
                <span>T</span>
              </div>
              <div className="user-info-large">
                <div className="user-name-large">twittergelistirici</div>
                <div className="user-handle-large">@twittergelistirici</div>
                <div className="user-bio">Resmi Twitter Geliştirici Hesabı</div>
              </div>
              <button className="follow-btn-large">Takip Et</button>
            </div>
            <div className="suggested-user-card">
              <div className="user-avatar-large">
                <span>K</span>
              </div>
              <div className="user-info-large">
                <div className="user-name-large">kodhayati</div>
                <div className="user-handle-large">@kodhayati</div>
                <div className="user-bio">Programlama ipuçları ve püf noktaları</div>
              </div>
              <button className="follow-btn-large">Takip Et</button>
            </div>
          </div>
        </div>
      </div>
      <RightSidebar />
    </div>
  );
}

export default Explore;
