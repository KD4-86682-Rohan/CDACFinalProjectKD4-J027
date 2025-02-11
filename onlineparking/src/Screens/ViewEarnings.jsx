import React from "react";
import "../CSS/ViewEarnings.css";
import Footer from "../Components/Footer";

const ViewEarnings = () => {
  return (
    <div>
     
      <div className="dashboard-container">
        <h2 className="dashboard-title">Dashboard Overview</h2>

        {/* Buttons
        <div className="dashboard-buttons">
          <button className="btn gray-btn">🅿 Manage Parking Spaces</button>
          <button className="btn blue-btn">📊 View Earnings</button>
          <button className="btn gray-btn">💬 Customer Feedback</button>
        </div> */}

        {/* Stats Cards */}
        <div className="dashboard-cards">
          {/* Today's Earnings */}
          <div className="card">
            <h3>Today's Earnings</h3>
            <div className="card-content">
              <span className="number green">$1250</span>
              <span className="icon">💵</span>
            </div>
            <p>Daily Revenue</p>
          </div>

          {/* Monthly Earnings */}
          <div className="card">
            <h3>Monthly Earnings</h3>
            <div className="card-content">
              <span className="number blue">$28500</span>
              <span className="icon">📈</span>
            </div>
            <p>Monthly Revenue</p>
          </div>

          {/* Peak Hours */}
          <div className="card">
            <h3>Peak Hours</h3>
            <div className="card-content">
              <span className="number purple">9 AM - 5 PM</span>
              <span className="icon">📊</span>
            </div>
            <p>Busiest Time Period</p>
          </div>
        </div>

        {/* Two Wheeler & Four Wheeler Statistics */}
        <div className="dashboard-stats">
          {/* Two Wheeler */}
          <div className="stat-card">
            <h3>Two Wheeler Statistics</h3>
            <p>Daily Earnings <span className="green">$450</span></p>
            <p>Monthly Earnings <span className="blue">$12500</span></p>
            <p>Occupied/Total Spaces <span className="purple">15/20</span></p>
          </div>

          {/* Four Wheeler */}
          <div className="stat-card">
            <h3>Four Wheeler Statistics</h3>
            <p>Daily Earnings <span className="green">$800</span></p>
            <p>Monthly Earnings <span className="blue">$16000</span></p>
            <p>Occupied/Total Spaces <span className="purple">20/30</span></p>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default ViewEarnings;
