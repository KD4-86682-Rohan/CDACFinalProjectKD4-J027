import React from "react";
import ParkingSpacesContainer from "../Screens/ParkingSpacesContainer";

const ParkingSpaces = () => {
  return (
    <div className="page-container">
      <header className="top-navbar">
        <h2>Vendor Admin</h2>
        <nav>
          <button className="nav-btn">🏠 Dashboard</button>
          <button className="nav-btn active">🅿️ My Parking Spaces</button>
          <button className="nav-btn">👤 Account</button>
          <button className="nav-btn">⚙️ Settings</button>
          <button className="nav-btn">🔙 Logout</button>
        </nav>
      </header>
      <ParkingSpacesContainer />
    </div>
  );
};

export default ParkingSpaces;
