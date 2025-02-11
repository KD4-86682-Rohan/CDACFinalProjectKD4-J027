// import React from "react";

// export const VendorNavbar = () => {
//   return (
//     <nav className="navbar">
//       <div className="navbar-logo">Vendor</div>
//       <div className="navbar-links">
//         <button className="navbar-button">Dashboard</button>
//         <button className="navbar-button">My Parking Spaces</button>
//         <button className="navbar-button">Account</button>
//         <button className="navbar-button">Settings</button>
//         <button className="navbar-button logout">Logout</button>
//       </div>
//     </nav>
//   );
// };

// export default VendorNavbar;

import React from "react";
import { Link } from "react-router-dom";
import "../CSS/VendorNavbar.css";


const VendorNavbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-logo">QuickPark</div>
      <div className="navbar-links">
        <Link to="/vendorhome" className="navbar-button">Dashboard</Link>
        <Link to="/my-parking-spaces" className="navbar-button">My Parking Spaces</Link>
        {/* <Link to="/view-earnings" className="navbar-button">View Earnings</Link> */}
        {/* <Link to="/customer-feedback" className="navbar-button">Customer Feedback</Link> */}
        <Link to="/account" className="navbar-button">Account</Link>
        <Link to="/settings" className="navbar-button">Settings</Link>
        <Link to="/login" className="navbar-button logout">Logout</Link>

//       <div className="navbar-logo">Vendor Admin</div>
//       <div className="navbar-links">
//         <Link to="/dashboard">
//           <button className="navbar-button">Dashboard</button>
//         </Link>
//         <Link to="/vendor-parking">
//           <button className="navbar-button">My Parking Spaces</button>
//         </Link>
//         <Link to="/vendor-account">
//           <button className="navbar-button">Account</button>
//         </Link>
//         <Link to="/settings">
//           <button className="navbar-button">Settings</button>
//         </Link>
//         <Link to="/logout">
//           <button className="navbar-button logout">Logout</button>
//         </Link>

      </div>
    </nav>
  );
};

export default VendorNavbar;
