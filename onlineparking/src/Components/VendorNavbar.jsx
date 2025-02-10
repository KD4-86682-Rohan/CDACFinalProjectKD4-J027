// <<<<<<< HEAD
// import React from "react";
// =======
// import React from "react";

// export const VendorNavbar = () => {
//   return (
//     <nav className="navbar">
//       <div className="navbar-logo">QuickPark</div>
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
// >>>>>>> caab4336dab92430f4672398c9d31ff8c3c15003

export const VendorNavbar = () => {
  return (
    <nav className="navbar">
{/* <<<<<<< HEAD
      <div className="navbar-logo">Vendor</div>
      <div className="navbar-links">
        <button className="navbar-button">Dashboard</button>
        <button className="navbar-button">My Parking Spaces</button>
        <button className="navbar-button">Account</button>
        <button className="navbar-button">Settings</button>
        <button className="navbar-button logout">Logout</button>
======= */}
      <div className="navbar-logo">QuickPark</div>
      <div className="navbar-links">
        <Link to="/vendorhome" className="navbar-button">Dashboard</Link>
        <Link to="/my-parking-spaces" className="navbar-button">My Parking Spaces</Link>
        {/* <Link to="/view-earnings" className="navbar-button">View Earnings</Link>
        <Link to="/customer-feedback" className="navbar-button">Customer Feedback</Link> */}
        <Link to="/account" className="navbar-button">Account</Link>
        <Link to="/settings" className="navbar-button">Settings</Link>
        <Link to="/logout" className="navbar-button logout">Logout</Link>
{/* >>>>>>> caab4336dab92430f4672398c9d31ff8c3c15003 */}
      </div>
    </nav>
  );
};

{/* <<<<<<< HEAD
export default VendorNavbar; */}
{/* ======= */}
export default VendorNavbar;
{/* >>>>>>> caab4336dab92430f4672398c9d31ff8c3c15003 */}
