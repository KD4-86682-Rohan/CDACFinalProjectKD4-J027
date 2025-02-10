import React from "react";

export const VendorNavbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-logo">Vendor</div>
      <div className="navbar-links">
        <button className="navbar-button">Dashboard</button>
        <button className="navbar-button">My Parking Spaces</button>
        <button className="navbar-button">Account</button>
        <button className="navbar-button">Settings</button>
        <button className="navbar-button logout">Logout</button>
      </div>
    </nav>
  );
};

export default VendorNavbar;