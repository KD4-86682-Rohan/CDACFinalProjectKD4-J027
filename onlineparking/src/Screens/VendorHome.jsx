import React from "react";
import "../CSS/VendorHome.css"; 
import Footer from "../Components/Footer";
import VendorNavbar from "../Components/VendorNavbar";
import { Link } from "react-router-dom";

const VendorDashboard = () => {
  return (
    <div>
      <VendorNavbar/>
      {/* <AdminNavbar /> */}
      <div className="dashboard-container">
      <h2 className="dashboard-title">Dashboard Overview</h2>

      {/* Buttons */}
      <div className="dashboard-buttons">
      <Link to="/manage-parking" className="btn blue-btn">🅿 Manage Parking Spaces</Link>
        <Link to="/view-earnings" className="btn gray-btn">📊 View Earnings</Link>
          <Link to="/customer-feedback" className="btn gray-btn">💬 Customer Feedback</Link>
      </div>

      {/* Stats Cards */}
      <div className="dashboard-cards">
        {/* Total Spaces */}
        <div className="card">
          <h3>Total Spaces</h3>
          <div className="card-content">
            <span className="number blue">50</span>
            <span className="icon">🅿</span>
          </div>
          <p>Total Available Capacity</p>
        </div>

        {/* Occupied Spaces */}
        <div className="card">
          <h3>Occupied Spaces</h3>
          <div className="card-content">
            <span className="number green">35</span>
            <span className="icon">🚗</span>
          </div>
          <p>Current Occupancy</p>
        </div>

        {/* Daily Bookings */}
        <div className="card">
          <h3>Daily Bookings</h3>
          <div className="card-content">
            <span className="number purple">42</span>
            <span className="icon">📋</span>
          </div>
          <p>Today's Total Bookings</p>
        </div>

        {/* Occupancy Rate */}
        <div className="card">
          <h3>Occupancy Rate</h3>
          <div className="card-content">
            <span className="number orange">70%</span>
            <span className="icon">📈</span>
          </div>
          <p>Average Daily Rate</p>
        </div>
      </div>
    </div>
    <Footer />
    </div>
  );
};

export default VendorDashboard;