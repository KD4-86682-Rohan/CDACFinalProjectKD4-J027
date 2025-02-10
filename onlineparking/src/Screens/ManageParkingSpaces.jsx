import React from "react";
import "../CSS/ManageParkingSpaces.css";

const ManageParkingSpaces = () => {
  return (
    <div className="manage-parking">
      {/* <h2 className="dashboard-title">Dashboard Overview</h2> */}

      <div className="dashboard-cards">
        <div className="card">
          <h3>Total Spaces</h3>
          <span className="card-value blue">50</span>
          <p>Total Available Capacity</p>
          <span className="card-icon">🅿</span>
        </div>

        <div className="card">
          <h3>Occupied Spaces</h3>
          <span className="card-value green">35</span>
          <p>Current Occupancy</p>
          <span className="card-icon">🅿</span>
        </div>

        <div className="card">
          <h3>Daily Bookings</h3>
          <span className="card-value purple">42</span>
          <p>Today's Total Bookings</p>
          <span className="card-icon">🅿</span>
        </div>

        <div className="card">
          <h3>Occupancy Rate</h3>
          <span className="card-value orange">70%</span>
          <p>Average Daily Rate</p>
          <span className="card-icon">📈</span>
        </div>
      </div>
    </div>
  );
};

export default ManageParkingSpaces;
