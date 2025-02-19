import React from "react";
import { Link } from "react-router-dom";
import "../CSS/UserNavbar.css";

function UserNavbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
      <div className="container">
        {/* Brand Name */}
        <Link className="navbar-brand brand-name" to="/userHome">
          QuickPark
        </Link>

        {/* Toggler for smaller screens */}
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        {/* Navbar Links */}
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto">
            {/* Home */}
            <li className="nav-item">
              <Link className="nav-link" to="/userHome">
                Home
              </Link>
            </li>

            {/* Available Parking */}
            <li className="nav-item">
              <Link className="nav-link" to="/availableparking">
                Available Parking
              </Link>
            </li>

            {/* My Booking */}
            <li className="nav-item">
              <Link className="nav-link" to="/mybookings">
                My Booking
              </Link>
            </li>

            {/* Contact Us */}
            <li className="nav-item">
              <Link className="nav-link" to="/contact-us">
                Contact Us
              </Link>
            </li>

            {/* Profile Dropdown */}
            <li className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle"
                href="#"
                id="profileDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >

<img
      src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png" 
      alt="Profile Icon"
      className="profile-icon"
      width="30"
      height="30"
    />
              </a>
              <ul
                className="dropdown-menu dropdown-menu-end"
                aria-labelledby="profileDropdown"
              >
                <li>
                  <Link className="dropdown-item" to="/profile">
                    Profile
                  </Link>
                </li>
                <li>
                  <Link className="dropdown-item" to="/settings">
                    Settings
                  </Link>
                </li>
                <li>
                  <hr className="dropdown-divider" />
                </li>
                <li>
                  <Link className="dropdown-item" to="/">
                    Logout
                  </Link>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default UserNavbar;
