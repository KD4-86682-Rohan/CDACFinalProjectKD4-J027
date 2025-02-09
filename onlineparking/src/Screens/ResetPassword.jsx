import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import admin from "../services/admin";
import Navbar from "../Components/Navbar";
import Footer from "../Components/Footer";
import "../CSS/ResetPassword.css";

const ResetPassword = () => {
  const { token } = useParams(); // Get reset token from URL
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleReset = async (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      setMessage("Passwords do not match!");
      return;
    }

    try {
      const response = await admin.resetPassword(token, password);
      setMessage(response.data.message);
      setTimeout(() => navigate("/login"), 3000); // Redirect to login
    } catch (error) {
      setMessage("Error resetting password. Try again.");
    }
  };

  return (
    <div>
      <Navbar />
      <div className="reset-password-container">
        <div className="reset-password-card">
          <h2>Reset Password</h2>
          {message && <p className="message">{message}</p>}
          <form onSubmit={handleReset}>
            <div className="input-group">
              <label>New Password</label>
              <input
                type="password"
                placeholder="Enter new password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>
            <div className="input-group">
              <label>Confirm Password</label>
              <input
                type="password"
                placeholder="Confirm new password"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                required
              />
            </div>
            <button type="submit" className="reset-password-btn">Reset Password</button>
          </form>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default ResetPassword;
