import { useState } from "react";
import { useNavigate } from "react-router-dom";
import admin from "../services/admin";
import Navbar from "../Components/Navbar";
import Footer from "../Components/Footer";
import "../CSS/ForgotPassword.css";

const ForgotPassword = () => {
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await admin.forgotPassword(email);
      setMessage(response.data.message);
    } catch (error) {
      setMessage("Error sending reset link. Please try again.");
    }
  };

  return (
    <div>
      <Navbar />
      <div className="forgot-password-container">
        <div className="forgot-password-card">
          <h2>Forgot Password?</h2>
          <p>Enter your email to receive a password reset link.</p>
          {message && <p className="message">{message}</p>}
          <form onSubmit={handleSubmit}>
            <div className="input-group">
              <label>Email Address</label>
              <input
                type="email"
                placeholder="Enter your email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>
            <button type="submit" className="forgot-password-btn">Send Reset Link</button>
            <button type="button" className="cancel-btn" onClick={() => navigate("/")}>Cancel</button>
          </form>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default ForgotPassword;
