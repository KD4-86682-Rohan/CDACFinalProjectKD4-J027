import React from "react";
import { Link } from "react-router-dom";
// import "./AdminNavbar.css"; // CSS file for styling
import "../CSS/AdminNavbar.css"

function AdminNavbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
      <div className="container">
        <Link className="navbar-brand fw-bold fs-4" to="/admin/dashboard">
          QuickPark Admin
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#adminNavbar"
          aria-controls="adminNavbar"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="adminNavbar">
          <ul className="navbar-nav ms-auto">
            <li className="nav-item">
              <Link className="nav-link" to="/admin/manage-parking">
                Manage Parking
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/admin/booking-records">
                Booking Records
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/admin/add-bank-account">
                Add Bank Account
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/admin/view-reports">
                View Reports
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link btn btn-danger text-white ms-2" to="/logout">
                Logout
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default AdminNavbar;
