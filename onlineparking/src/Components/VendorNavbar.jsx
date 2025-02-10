import React from "react";
import { Link } from "react-router-dom";

const VendorNavbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-logo">Vendor Admin</div>
      <div className="navbar-links">
        <Link to="/dashboard">
          <button className="navbar-button">Dashboard</button>
        </Link>
        <Link to="/vendor-parking">
          <button className="navbar-button">My Parking Spaces</button>
        </Link>
        <Link to="/vendor-account">
          <button className="navbar-button">Account</button>
        </Link>
        <Link to="/settings">
          <button className="navbar-button">Settings</button>
        </Link>
        <Link to="/logout">
          <button className="navbar-button logout">Logout</button>
        </Link>
      </div>
    </nav>
  );
};

export default VendorNavbar;
