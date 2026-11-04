import React from 'react';
import Sidebar from './Sidebar';
import RightSidebar from './RightSidebar';
import './Messages.css';

function Messages() {
  const conversations = [
    { id: 1, user: 'twittergelistirici', lastMessage: 'Merhaba! Nasılsın?', time: '2s', unread: 2 },
    { id: 2, user: 'teknolojihaberleri', lastMessage: 'Yeni teknoloji haberlerini gördün mü?', time: '5d', unread: 0 },
    { id: 3, user: 'kodhayati', lastMessage: 'Bu kod çok iyi!', time: '1g', unread: 1 },
  ];

  return (
    <div className="messages">
      <Sidebar />
      <div className="main-content">
        <header className="messages-header">
          <h1>Mesajlar</h1>
        </header>
        
        <div className="messages-content">
          <div className="search-bar-messages">
            <input type="text" placeholder="Ara" className="search-input-messages" />
          </div>

          <div className="conversations-list">
            {conversations.map((conversation) => (
              <div key={conversation.id} className="conversation-item">
                <div className="conversation-avatar">
                  <span>{conversation.user.charAt(0).toUpperCase()}</span>
                  {conversation.unread > 0 && <div className="unread-badge">{conversation.unread}</div>}
                </div>
                <div className="conversation-info">
                  <div className="conversation-header">
                    <span className="conversation-user">{conversation.user}</span>
                    <span className="conversation-time">{conversation.time}</span>
                  </div>
                  <div className="conversation-message">{conversation.lastMessage}</div>
                </div>
              </div>
            ))}
          </div>

          <div className="empty-state">
            <div className="empty-icon">✉️</div>
            <h2>Mesajlarınızı buradan seçin</h2>
            <p>Sol taraftan bir kişi seçerek mesajlaşmaya başlayın</p>
          </div>
        </div>
      </div>
      <RightSidebar />
    </div>
  );
}

export default Messages;
