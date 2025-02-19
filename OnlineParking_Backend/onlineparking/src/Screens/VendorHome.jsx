
// import React from "react";
// import "../CSS/VendorHome.css"; // Include CSS for styling
// import Footer from "../Components/Footer";
// // import AdminNavbar from "../Components/AdminNavbar";
// import VendorNavbar from "../Components/VendorNavbar";

// const VendorDashboard = () => {
//   return (
//     <div>
//       <VendorNavbar/>
//       {/* <AdminNavbar /> */}
//     <div className="dashboard-container">
//       {/* Welcome Section */}
//       <div className="welcome-section">
//         <h1>Welcome to Your Vendor Dashboard!</h1>
//         <p>
//           Maximize your parking space potential with our efficient management system. Track earnings, manage spaces, and grow your business all in one place.
//         </p>
//       </div>

//       {/* Dashboard Cards */}
//       <div className="dashboard-cards">
//         <div className="card">
//           <h2>Manage Parking Spaces</h2>
//           <p>Add, edit, and manage your parking locations</p>
//           <button>Manage Now</button>
//         </div>

//         <div className="card">
//           <h2>View Earnings</h2>
//           <p>Track your revenue and financial analytics</p>
//           <button>View Details</button>
//         </div>

//         <div className="card">
//           <h2>Customer Feedback</h2>
//           <p>Review and respond to customer ratings</p>
//           <button>Check Reviews</button>
//         </div>
//       </div>

//       {/* Statistics Section */}
//       <div className="dashboard-cards">
//         <div className="card">
//           <h3>Total Cars Parked</h3>
//           <h5>1,234</h5>
//         </div>
//         <div className="card">
//           <h3>Total Earnings</h3>
//           <h5>Rs. 15,680</h5>
//         </div>
//         <div className="card">
//           <h3>Active Parking Spaces</h3>
//           <h5>25</h5>
//         </div>
//       </div>

//       {/* Footer */}
  
//     </div>
//     <Footer />
//     </div>
//   );
// };

// export default VendorDashboard;

// import React from "react";
// import "../CSS/VendorHome.css"; // Include CSS for styling
// import Footer from "../Components/Footer";
// // import AdminNavbar from "../Components/AdminNavbar";
// import VendorNavbar from "../Components/VendorNavbar";
// import { Link } from "react-router-dom";

// const VendorDashboard = () => {
//   return (
//     <div>
//       <VendorNavbar/>
//       {/* <AdminNavbar /> */}
//       <div className="dashboard-container">
//       <h2 className="dashboard-title">Dashboard Overview</h2>

//       {/* Buttons */}
//       <div className="dashboard-buttons">
//       <Link to="/manage-parking" className="btn blue-btn">🅿 Manage Parking Spaces</Link>
//         <Link to="/view-earnings" className="btn gray-btn">📊 View Earnings</Link>
//           <Link to="/customer-feedback" className="btn gray-btn">💬 Customer Feedback</Link>
//       </div>

//       {/* Stats Cards */}
//       <div className="dashboard-cards">
//         {/* Total Spaces */}
//         <div className="card">
//           <h3>Total Spaces</h3>
//           <div className="card-content">
//             <span className="number blue">50</span>
//             <span className="icon">🅿</span>
//           </div>
//           <p>Total Available Capacity</p>
//         </div>

//         {/* Occupied Spaces */}
//         <div className="card">
//           <h3>Occupied Spaces</h3>
//           <div className="card-content">
//             <span className="number green">35</span>
//             <span className="icon">🚗</span>
//           </div>
//           <p>Current Occupancy</p>
//         </div>

//         {/* Daily Bookings */}
//         <div className="card">
//           <h3>Daily Bookings</h3>
//           <div className="card-content">
//             <span className="number purple">42</span>
//             <span className="icon">📋</span>
//           </div>
//           <p>Today's Total Bookings</p>
//         </div>

//         {/* Occupancy Rate */}
//         <div className="card">
//           <h3>Occupancy Rate</h3>
//           <div className="card-content">
//             <span className="number orange">70%</span>
//             <span className="icon">📈</span>
//           </div>
//           <p>Average Daily Rate</p>
//         </div>
//       </div>
//     </div>
//     <Footer />
//     </div>
//   );
// };

// export default VendorDashboard;



import React, { useState } from "react";
import "../CSS/VendorDashboard.css";
// import ManageParkingSpaces from "../Screens/ManageParkingSpaces";
import ManageParkingSpaces from "../Screens/ManageParkingSpaces";
import ViewEarnings from "../Screens/ViewEarnings";
import CustomerFeedback from "../Screens/CustomerFeedback";
// import "../CSS/VendorHome.css"; 
import Footer from "../Components/Footer";
import VendorNavbar from "../Components/VendorNavbar";

const VendorDashboard = () => {
  const [activeTab, setActiveTab] = useState("manage");

  return (
    <div>
      <VendorNavbar/>
    
    <div className="vendor-dashboard">
      {/* <nav className="dashboard-nav">
        <button
          className={activeTab === "manage" ? "active" : ""}
          onClick={() => setActiveTab("manage")}
        >
          🚗 Manage Parking Spaces
        </button>
        <button
          className={activeTab === "earnings" ? "active" : ""}
          onClick={() => setActiveTab("earnings")}
        >
          💰 View Earnings
        </button>
        <button
          className={activeTab === "feedback" ? "active" : ""}
          onClick={() => setActiveTab("feedback")}
        >
          💬 Customer Feedback
        </button>
      </nav> */}

<nav className="dashboard-nav" style={{ marginTop: "20px" }}>
  <button
    className={activeTab === "manage" ? "active" : ""}
    onClick={() => setActiveTab("manage")}
  >
    🚗 Manage Parking Spaces
  </button>
  <button
    className={activeTab === "earnings" ? "active" : ""}
    onClick={() => setActiveTab("earnings")}
  >
    💰 View Earnings
  </button>
  <button
    className={activeTab === "feedback" ? "active" : ""}
    onClick={() => setActiveTab("feedback")}
  >
    💬 Customer Feedback
  </button>
</nav>


      <div className="dashboard-content">
        {activeTab === "manage" && <ManageParkingSpaces />}
        {activeTab === "earnings" && <ViewEarnings />}
        {activeTab === "feedback" && <CustomerFeedback />}
      </div>
    </div>
    </div>
  );
};

export default VendorDashboard;
