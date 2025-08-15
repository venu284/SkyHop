import React, { useState } from 'react';
import './home.css';
import logo from '../images/logo.png';
import image1 from '../assets/image1.jpg';
import image2 from '../assets/image2.jpg';
import image3 from '../assets/image3.jpg';
import image4 from '../assets/image4.jpg';
import image5 from '../assets/image5.jpg';
import image6 from '../assets/image6.jpg';
import SlideShow from './SlideShow';  // <-- new

const Home = ({ onLogin, setUser }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isLogin, setIsLogin] = useState(true); // toggle login/create
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    phone: '',
    password: '',
    confirm_password: '',
  });

  const validateInputs = ({ email, password, username, confirm_password, phone }) => {
    const errors = [];
    if (email && !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      errors.push("Invalid email format");
    }
    if (password && password.length < 6) {
      errors.push("Password must be at least 6 characters long");
    }
    if (username && username.trim().length === 0) {
      errors.push("Username cannot be empty");
    }
    if (confirm_password && password !== confirm_password) {
      errors.push("Passwords do not match");
    }
    if (phone && !/^\d{10}$/.test(phone)) {
      errors.push("Phone number must be 10 digits");
    }
    return errors;
  };

  const handleFormChange = (e) => {
    const { id, value } = e.target;
    setFormData(prev => ({ ...prev, [id]: value }));
  };

  function decodeJWT(token) {
    const payload = token.split('.')[1];
    return JSON.parse(atob(payload));
  }

  const handleLogin = async (e) => {
    e.preventDefault();
    const { email, password } = formData;
    const errors = validateInputs({ email, password });
    if (errors.length) {
      alert(errors.join("\n"));
      return;
    }
    try {
      const res = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password }),
      });
      if (!res.ok) {
        const err = await res.json();
        throw new Error(err.message);
      }
      const data = await res.json();
      localStorage.setItem('token', data.token);
      setIsLoggedIn(true);
      setUser(decodeJWT(data.token).passengerID);
      alert('Login successful!');
      onLogin();
    } catch (err) {
      alert(err.message || 'An error occurred during login.');
    }
  };

  const handleCreateAccount = async (e) => {
    e.preventDefault();
    const { username, email, phone, password, confirm_password } = formData;
    const errors = validateInputs({ username, email, phone, password, confirm_password });
    if (errors.length) {
      alert(errors.join("\n"));
      return;
    }
    try {
      const res = await fetch('http://localhost:8080/passenger/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: username, phone, email, password }),
      });
      if (!res.ok) {
        const err = await res.json();
        throw new Error(err.message);
      }
      alert('Account created successfully. Please log in.');
      setIsLogin(true);
    } catch (err) {
      alert(err.message || 'An error occurred during account creation.');
    }
  };

  // slide images array
  const slides = [image1, image2, image3, image4, image5, image6];

  return (
    <div className="home-container">
      {/* replace grid with slideshow */}
      <SlideShow images={slides} interval={4000} />

      {/* login/register modal overlay */}
      {!isLoggedIn && (
        <div className="modal-container">
          <div className="login-box">
            <div className="logo-container">
              <img src={logo} alt="Logo" className="logo" />
            </div>

            {isLogin ? (
              <>
                <h2 className="welcome-text">SkyHop</h2>
                <p className="description">Find the best flight deals</p>
                <form className="login-form" onSubmit={handleLogin}>
                  <input
                    type="email"
                    id="email"
                    className="input-field"
                    placeholder="Email"
                    required
                    value={formData.email}
                    onChange={handleFormChange}
                  />
                  <input
                    type="password"
                    id="password"
                    className="input-field"
                    placeholder="Password"
                    required
                    value={formData.password}
                    onChange={handleFormChange}
                  />
                  <button type="submit" className="login-btn">Login</button>
                </form>
                <div className="or-container">
                  <span className="line" />
                  <span className="or-text">or</span>
                  <span className="line" />
                </div>
                <button
                  className="switch-btn"
                  onClick={() => setIsLogin(false)}
                >
                  Create an Account
                </button>
              </>
            ) : (
              <>
                <h2 className="welcome-text">Create an Account</h2>
                <p className="description">Join MyFlights to find the best flights</p>
                <form className="login-form" onSubmit={handleCreateAccount}>
                  <input
                    type="text"
                    id="username"
                    className="input-field"
                    placeholder="Username"
                    required
                    value={formData.username}
                    onChange={handleFormChange}
                  />
                  <input
                    type="text"
                    id="phone"
                    className="input-field"
                    placeholder="Phone Number"
                    required
                    value={formData.phone}
                    onChange={handleFormChange}
                  />
                  <input
                    type="email"
                    id="email"
                    className="input-field"
                    placeholder="Email"
                    required
                    value={formData.email}
                    onChange={handleFormChange}
                  />
                  <input
                    type="password"
                    id="password"
                    className="input-field"
                    placeholder="Password"
                    required
                    value={formData.password}
                    onChange={handleFormChange}
                  />
                  <input
                    type="password"
                    id="confirm_password"
                    className="input-field"
                    placeholder="Confirm Password"
                    required
                    value={formData.confirm_password}
                    onChange={handleFormChange}
                  />
                  <button type="submit" className="login-btn">Create Account</button>
                </form>
                <div className="or-container">
                  <span className="line" />
                  <span className="or-text">or</span>
                  <span className="line" />
                </div>
                <button
                  className="switch-btn"
                  onClick={() => setIsLogin(true)}
                >
                  Back to Login
                </button>
              </>
            )}
          </div>
        </div>
      )}
    </div>
  );
};

export default Home;
