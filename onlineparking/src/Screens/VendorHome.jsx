import React from "react";
import "../CSS/VendorHome.css"; // Include CSS for styling

const VendorDashboard = () => {
  return (
    <div className="dashboard-container">
      {/* Header Navigation */}
      <header className="dashboard-header">
        <div className="logo">ParkEase</div>
        <nav className="dashboard-nav">
          <ul>
            <li>Dashboard</li>
            <li>My Parking Spaces</li>
            <li>Account Settings</li>
            <li className="logout">Logout</li>
          </ul>
        </nav>
      </header>

      {/* Welcome Section */}
      <div className="welcome-section">
        <h1>Welcome to Your Vendor Dashboard!</h1>
        <p>
          Maximize your parking space potential with our efficient management system. Track earnings, manage spaces, and grow your business all in one place.
        </p>
      </div>

      {/* Dashboard Cards */}
      <div className="dashboard-cards">
        <div className="card">
          <h2>Manage Parking Spaces</h2>
          <p>Add, edit, and manage your parking locations</p>
          <button>Manage Now</button>
        </div>

        <div className="card">
          <h2>View Earnings</h2>
          <p>Track your revenue and financial analytics</p>
          <button>View Details</button>
        </div>

        <div className="card">
          <h2>Customer Feedback</h2>
          <p>Review and respond to customer ratings</p>
          <button>Check Reviews</button>
        </div>
      </div>

      {/* Statistics Section */}
      <div className="dashboard-cards">
        <div className="card">
          <h3>Total Cars Parked</h3>
          <h5>1,234</h5>
        </div>
        <div className="card">
          <h3>Total Earnings</h3>
          <h5>Rs. 15,680</h5>
        </div>
        <div className="card">
          <h3>Active Parking Spaces</h3>
          <h5>25</h5>
        </div>
      </div>

      {/* Footer */}
      <footer className="dashboard-footer">
        <div className="about-us">
          <h4>About Us</h4>
          <p>Making parking management simple and efficient for vendors.</p>
        </div>

        <div className="quick-links">
          <h4>Quick Links</h4>
          <ul>
            <li>Terms of Service</li>
            <li>Privacy Policy</li>
            <li>Help Center</li>
          </ul>
        </div>

        <div className="contact">
          <h4>Contact</h4>
          <p>Email: support@parkease.com</p>
          <p>Phone: +1 (555) 123-4567</p>
        </div>
      </footer>
    </div>
  );
};

export default VendorDashboard;