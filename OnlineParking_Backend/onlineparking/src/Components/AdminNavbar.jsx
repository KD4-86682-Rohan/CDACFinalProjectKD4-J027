import React from "react";
import { Link } from "react-router-dom";
import "../CSS/AdminNavbar.css"; // Link the CSS file for custom styling

function AdminNavbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
      <div className="container">
        <Link className="navbar-brand fw-bold fs-4 text-primary" to="/vendorhome">
          QuickPark
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
              <Link className="nav-link" to="/vendorhome">
                Dashboard
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/managespaces">
                My Parking Spaces
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/admin/account">
                Account
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/admin/settings">
                Settings
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link btn btn-primary text-white ms-3" to="/">
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