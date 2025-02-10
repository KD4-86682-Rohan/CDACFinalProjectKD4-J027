import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import admin from '../services/admin';
import Navbar from "../Components/Navbar";
import Footer from './../Components/Footer';
import "../CSS/Login.css";

const SigninUser = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [user, setUser] = useState({}); // Use an empty object instead of a string
  const navigate = useNavigate();
  const onLogin = async (e) => {
    e.preventDefault(); // Prevent page refresh on form submit
    const u = { email, password };
    try {
      const response = await admin.login(u);
      console.log("User logged in successfully", response.data);
      setUser(response.data);
      // Ensure `user` is updated before using it
      if (response.data.role === "Vendor") {
        navigate("/VendorHome");
      } else if (response.data.role === "Admin") {
        navigate("/AdminHome");
      } else {
        navigate("/UserHome");
      }
    } catch (error) {
      console.log("Something went wrong:", error.response);
    }
  };
  return (
    <div>
      <Navbar />
      <div className="login-container">
        <div className="login-content">
          {/* Left Side: Illustration */}
          <div className="login-illustration">
            <img
              src="https://img.freepik.com/premium-vector/reserve-parking-space-curbside-pickup-abstract-concept-vector-illustration_107173-20370.jpg?w=740"
              // src="https://www.shutterstock.com/shutterstock/photos/1465305536/display_1500/stock-vector-vector-illustration-of-autonomous-wireless-parking-remote-connected-car-sharing-service-controlled-1465305536.jpg" // Replace with the actual image path
              alt="Login Illustration"
            />
          </div>

          {/* Right Side: Login Form */}
          <div className="login-form">
            <h2>Welcome Back :)</h2>
            <p>
              To keep connected with us, please login with your personal
              information by email address and password.
            </p>
            <form>
              {/* Email Input */}
              <div className="input-group">
                <label htmlFor="email">Email Address</label>
                <input
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  type="email"
                  id="email"
                  placeholder="Enter your email"
                  required
                />
                {/* <input
                type="email"
                id="email"
                placeholder="Enter your email"
                required
              /> */}
              </div>

              {/* Password Input */}
              <div className="input-group">
                <label htmlFor="password">Password</label>
                <input
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  type="password"
                  id="password"
                  placeholder="Enter your password"
                  required
                />
                {/* <input
                  type="password"
                  id="password"
                  placeholder="Enter your password"
                  required
                /> */}
              </div>

              {/* Options */}
              <div className="options">
                <label>
                  <input type="checkbox" /> Remember Me
                </label>
                <a href="/forgot-password">Forgot Password?</a>
              </div>

              {/* Buttons */}
              <div className="button-group">
              <button onClick={onLogin} className='btn btn-success mt-3'>
              Login
            </button>
                <Link to="/register">
                  <button type="button" className="btn create-account-btn">
                    Create Account
                  </button>
                </Link>
              </div>
            </form>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default SigninUser;
