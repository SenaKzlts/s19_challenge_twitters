import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Register.css';

function Register() {
  const [userName, setUserName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const response = await fetch('http://localhost:3000/auth/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ userName, email, password }),
      });

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText || 'Registration failed');
      }

      const user = await response.json();
      localStorage.setItem('userId', user.id);
      localStorage.setItem('userEmail', email);
      localStorage.setItem('userName', userName);
      navigate('/');
    } catch (err) {
      setError(err.message || 'Kayıt başarısız. E-posta veya kullanıcı adı zaten kullanımda olabilir.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="register-container">
      <div className="register-card">
        <div className="register-header">
          <h2>Kayıt Ol</h2>
          <p>Bugün Twitter'a Katılın</p>
        </div>
        <form onSubmit={handleRegister} className="register-form">
          <input
            type="text"
            placeholder="Kullanıcı Adı"
            value={userName}
            onChange={(e) => setUserName(e.target.value)}
            className="register-input"
            required
            minLength={3}
          />
          <input
            type="email"
            placeholder="E-posta"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="register-input"
            required
          />
          <input
            type="password"
            placeholder="Şifre"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="register-input"
            required
            minLength={6}
          />
          {error && <div className="error-message">{error}</div>}
          <button type="submit" className="register-button" disabled={loading}>
            {loading ? 'Hesap oluşturuluyor...' : 'Kayıt Ol'}
          </button>
        </form>
        <div className="register-footer">
          <p>Zaten hesabınız var mı? <a href="/login">Giriş Yap</a></p>
        </div>
      </div>
    </div>
  );
}

export default Register;
