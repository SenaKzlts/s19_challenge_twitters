import React from 'react';
import Sidebar from './Sidebar';
import RightSidebar from './RightSidebar';
import './Notifications.css';

function Notifications() {
  const notifications = [
    { id: 1, type: 'like', user: 'twittergelistirici', text: 'tweetini beğendi', time: '2s' },
    { id: 2, type: 'retweet', user: 'teknolojihaberleri', text: 'tweetini retweetledi', time: '5d' },
    { id: 3, type: 'follow', user: 'kodhayati', text: 'seni takip etmeye başladı', time: '1g' },
    { id: 4, type: 'comment', user: 'twittergelistirici', text: 'tweetine yorum yaptı: "Harika bir paylaşım!"', time: '2g' },
    { id: 5, type: 'like', user: 'kodhayati', text: 'tweetini beğendi', time: '3g' },
    { id: 6, type: 'follow', user: 'teknolojihaberleri', text: 'seni takip etmeye başladı', time: '5g' },
  ];

  const getIcon = (type) => {
    switch(type) {
      case 'like': return '❤️';
      case 'retweet': return '🔄';
      case 'follow': return '👤';
      case 'comment': return '💬';
      default: return '🔔';
    }
  };

  return (
    <div className="notifications">
      <Sidebar />
      <div className="main-content">
        <header className="notifications-header">
          <h1>Bildirimler</h1>
        </header>
        
        <div className="notifications-content">
          <div className="notifications-tabs">
            <button className="tab active">Tümü</button>
            <button className="tab">Bahsedilenler</button>
          </div>

          {notifications.map((notification) => (
            <div key={notification.id} className="notification-item">
              <div className="notification-icon">
                {getIcon(notification.type)}
              </div>
              <div className="notification-content">
                <div className="user-avatar-small">
                  <span>{notification.user.charAt(0).toUpperCase()}</span>
                </div>
                <div className="notification-text">
                  <span className="notification-user">@{notification.user}</span>
                  <span className="notification-action">{notification.text}</span>
                </div>
                <div className="notification-time">{notification.time}</div>
              </div>
            </div>
          ))}
        </div>
      </div>
      <RightSidebar />
    </div>
  );
}

export default Notifications;
