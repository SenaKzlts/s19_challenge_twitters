import React from 'react';
import Sidebar from './Sidebar';
import RightSidebar from './RightSidebar';
import './Bookmarks.css';

function Bookmarks() {
  const bookmarks = [
    { id: 1, user: 'twittergelistirici', content: 'Yeni Spring Boot uygulamamızı başarıyla yayına aldık! 🚀 #SpringBoot #Java', time: '2s' },
    { id: 2, user: 'kodhayati', content: 'İpucu: Her zaman anlamlı değişken isimleri kullanın. Gelecekteki kendiniz size teşekkür edecek! 💡 #Programlama #EnIyiUygulamalar', time: '1g' },
    { id: 3, user: 'teknolojihaberleri', content: 'React 19 harika yeni özelliklerle geliyor! Denemek için sabırsızlanıyorum. ⚛️', time: '3g' },
  ];

  const formatTimeAgo = (date) => {
    const seconds = Math.floor((new Date() - date) / 1000);
    if (seconds < 60) return 'şimdi';
    if (seconds < 3600) return `${Math.floor(seconds / 60)}d`;
    if (seconds < 86400) return `${Math.floor(seconds / 3600)}s`;
    return `${Math.floor(seconds / 86400)}g`;
  };

  return (
    <div className="bookmarks">
      <Sidebar />
      <div className="main-content">
        <header className="bookmarks-header">
          <h1>Yer İşaretleri</h1>
          <p>@kullanıcı</p>
        </header>
        
        <div className="bookmarks-content">
          {bookmarks.length === 0 ? (
            <div className="empty-bookmarks">
              <div className="empty-icon">📑</div>
              <h2>Henüz yer işareti yok</h2>
              <p>Tweetleri kaydederek daha sonra okuyabilirsiniz</p>
            </div>
          ) : (
            bookmarks.map((bookmark) => (
              <div key={bookmark.id} className="bookmark-item">
                <div className="bookmark-header">
                  <div className="user-avatar">
                    <span>{bookmark.user.charAt(0).toUpperCase()}</span>
                  </div>
                  <div className="user-details">
                    <span className="username">@{bookmark.user}</span>
                    <span className="bookmark-time">{bookmark.time}</span>
                  </div>
                </div>
                <div className="bookmark-content">
                  <p>{bookmark.content}</p>
                </div>
                <div className="bookmark-actions">
                  <button className="action-button">💬</button>
                  <button className="action-button">🔄</button>
                  <button className="action-button">❤️</button>
                  <button className="action-button">📤</button>
                </div>
              </div>
            ))
          )}
        </div>
      </div>
      <RightSidebar />
    </div>
  );
}

export default Bookmarks;
