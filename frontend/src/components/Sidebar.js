import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Sidebar.css';

function Sidebar() {
  const navigate = useNavigate();
  const userName = localStorage.getItem('userName');

  const handleLogout = () => {
    localStorage.clear();
    navigate('/login');
  };

  return (
    <div className="sidebar">
      <div className="sidebar-content">
        <div className="sidebar-logo">
          <svg viewBox="0 0 24 24" className="twitter-icon">
            <path d="M23.953 4.57a10 10 0 01-2.825.775 4.958 4.958 0 002.163-2.723c-.951.555-2.005.959-3.127 1.184a4.92 4.92 0 00-8.384 4.482C7.69 8.095 4.067 6.13 1.64 3.162a4.822 4.822 0 00-.666 2.475c0 1.71.87 3.213 2.188 4.096a4.904 4.904 0 01-2.228-.616v.06a4.923 4.923 0 003.946 4.827 4.996 4.996 0 01-2.212.085 4.936 4.936 0 004.604 3.417 9.867 9.867 0 01-6.102 2.105c-.39 0-.779-.023-1.17-.067a13.995 13.995 0 007.557 2.209c9.053 0 13.998-7.496 13.998-13.985 0-.21 0-.42-.015-.63A9.935 9.935 0 0024 4.59z"/>
          </svg>
        </div>
        
        <nav className="sidebar-nav">
          <button className="nav-item active" onClick={() => navigate('/')}>
            <span className="nav-icon">🏠</span>
            <span className="nav-text">Ana Sayfa</span>
          </button>
          <button className="nav-item" onClick={() => navigate('/explore')}>
            <span className="nav-icon">🔍</span>
            <span className="nav-text">Keşfet</span>
          </button>
          <button className="nav-item" onClick={() => navigate('/notifications')}>
            <span className="nav-icon">🔔</span>
            <span className="nav-text">Bildirimler</span>
          </button>
          <button className="nav-item" onClick={() => navigate('/messages')}>
            <span className="nav-icon">✉️</span>
            <span className="nav-text">Mesajlar</span>
          </button>
          <button className="nav-item" onClick={() => navigate('/bookmarks')}>
            <span className="nav-icon">📑</span>
            <span className="nav-text">Yer İşaretleri</span>
          </button>
          <button className="nav-item" onClick={() => navigate('/profile')}>
            <span className="nav-icon">👤</span>
            <span className="nav-text">Profil</span>
          </button>
        </nav>

        <button className="tweet-button-sidebar">Tweetle</button>

        <div className="sidebar-user">
          <div className="user-avatar">
            <span>{userName ? userName.charAt(0).toUpperCase() : 'U'}</span>
          </div>
          <div className="user-details">
            <span className="user-name">{userName || 'Kullanıcı'}</span>
            <span className="user-handle">@{userName || 'kullanici'}</span>
          </div>
          <button className="logout-icon" onClick={handleLogout}>
            🚪
          </button>
        </div>
      </div>
    </div>
  );
}

export default Sidebar;
