

import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import admin from "../services/admin";
import Navbar from "../Components/Navbar";
import Footer from "../Components/Footer";
import "../CSS/Login.css";
import axios from 'axios';
const SigninUser = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const onLogin = async (e) => {
    e.preventDefault();

    if (!email || !password) {
      setErrorMessage("Please enter both email and password.");
      return;
    }

    try {
      const response = await axios.post("http://localhost:8081/User/login", {
        email,
        password,
      });

      if (response.data.token) {
        localStorage.setItem("authToken", response.data.token); // Store JWT token
        console.log("Login Successful:", response.data);

        // Redirect user based on role
        switch (response.data.role) {
          case "Vendor":
            navigate("/VendorHome");
            break;
          case "Admin":
            navigate("/AdminHome");
            break;
          case "User":
            navigate("/UserHome");
            break;
          default:
            setErrorMessage("Invalid role assigned. Please contact support.");
        }
      } else {
        setErrorMessage("Invalid credentials. Please try again.");
      }
    } catch (error) {
      setErrorMessage(error.response?.data?.message || "Login failed.");
      console.error("Login Error:", error.response?.data || error);
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
              alt="Login Illustration"
            />
          </div>

          {/* Right Side: Login Form */}
          <div className="login-form">
            <h2>Welcome Back :</h2>
            <p>To keep connected with us, please login with your email and password.</p>

            <form onSubmit={onLogin}>
              {/* Display Error Message */}
              {errorMessage && <p className="error-message">{errorMessage}</p>}

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
              </div>

              {/* Options */}
              <div className="options">
                <label>
                  <input type="checkbox" /> Remember Me
                </label>
                <Link to="/forgot-password">Forgot Password?</Link>
              </div>

              {/* Buttons */}
              <div className="button-group">
                <button type="submit" className="btn btn-success mt-3">
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
