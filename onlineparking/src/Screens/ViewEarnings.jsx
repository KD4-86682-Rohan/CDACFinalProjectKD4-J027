
// <<<<<<< HEAD
// import React from "react";
// import "../CSS/ViewEarnings.css";
// import Footer from "../Components/Footer";
// import VendorNavbar from "../Components/VendorNavbar";

// const ViewEarnings = () => {
//   return (
//     <div>
//       <VendorNavbar />
//       <div className="dashboard-container">
//         <h2 className="dashboard-title">Dashboard Overview</h2>

//         {/* Buttons */}
//         <div className="dashboard-buttons">
//           <button className="btn gray-btn">🅿 Manage Parking Spaces</button>
//           <button className="btn blue-btn">📊 View Earnings</button>
//           <button className="btn gray-btn">💬 Customer Feedback</button>
//         </div>

//         {/* Stats Cards */}
//         <div className="dashboard-cards">
//           {/* Today's Earnings */}
//           <div className="card">
//             <h3>Today's Earnings</h3>
//             <div className="card-content">
//               <span className="number green">$1250</span>
//               <span className="icon">💵</span>
//             </div>
//             <p>Daily Revenue</p>
//           </div>

//           {/* Monthly Earnings */}
//           <div className="card">
//             <h3>Monthly Earnings</h3>
//             <div className="card-content">
//               <span className="number blue">$28500</span>
//               <span className="icon">📈</span>
//             </div>
//             <p>Monthly Revenue</p>
//           </div>

//           {/* Peak Hours */}
//           <div className="card">
//             <h3>Peak Hours</h3>
//             <div className="card-content">
//               <span className="number purple">9 AM - 5 PM</span>
//               <span className="icon">📊</span>
//             </div>
//             <p>Busiest Time Period</p>
//           </div>
//         </div>

//         {/* Two Wheeler & Four Wheeler Statistics */}
//         <div className="dashboard-stats">
//           {/* Two Wheeler */}
//           <div className="stat-card">
//             <h3>Two Wheeler Statistics</h3>
//             <p>Daily Earnings <span className="green">$450</span></p>
//             <p>Monthly Earnings <span className="blue">$12500</span></p>
//             <p>Occupied/Total Spaces <span className="purple">15/20</span></p>
//           </div>

//           {/* Four Wheeler */}
//           <div className="stat-card">
//             <h3>Four Wheeler Statistics</h3>
//             <p>Daily Earnings <span className="green">$800</span></p>
//             <p>Monthly Earnings <span className="blue">$16000</span></p>
//             <p>Occupied/Total Spaces <span className="purple">20/30</span></p>
//           </div>
//         </div>
//       </div>
//       <Footer />
// =======
// import React from "react";
// import "../CSS/ViewEarnings.css";
// import Footer from "../Components/Footer";
// import VendorNavbar from "../Components/VendorNavbar";

// const ViewEarnings = () => {
//   return (
//     <div>
//       <VendorNavbar />
//       <div className="dashboard-container">
//         <h2 className="dashboard-title">Dashboard Overview</h2>

//         {/* Buttons */}
//         <div className="dashboard-buttons">
//           <button className="btn gray-btn">🅿 Manage Parking Spaces</button>
//           <button className="btn blue-btn">📊 View Earnings</button>
//           <button className="btn gray-btn">💬 Customer Feedback</button>
//         </div>

//         {/* Stats Cards */}
//         <div className="dashboard-cards">
//           {/* Today's Earnings */}
//           <div className="card">
//             <h3>Today's Earnings</h3>
//             <div className="card-content">
//               <span className="number green">$1250</span>
//               <span className="icon">💵</span>
//             </div>
//             <p>Daily Revenue</p>
//           </div>

//           {/* Monthly Earnings */}
//           <div className="card">
//             <h3>Monthly Earnings</h3>
//             <div className="card-content">
//               <span className="number blue">$28500</span>
//               <span className="icon">📈</span>
//             </div>
//             <p>Monthly Revenue</p>
//           </div>

//           {/* Peak Hours */}
//           <div className="card">
//             <h3>Peak Hours</h3>
//             <div className="card-content">
//               <span className="number purple">9 AM - 5 PM</span>
//               <span className="icon">📊</span>
//             </div>
//             <p>Busiest Time Period</p>
//           </div>
//         </div>

//         {/* Two Wheeler & Four Wheeler Statistics */}
//         <div className="dashboard-stats">
//           {/* Two Wheeler */}
//           <div className="stat-card">
//             <h3>Two Wheeler Statistics</h3>
//             <p>Daily Earnings <span className="green">$450</span></p>
//             <p>Monthly Earnings <span className="blue">$12500</span></p>
//             <p>Occupied/Total Spaces <span className="purple">15/20</span></p>
//           </div>

//           {/* Four Wheeler */}
//           <div className="stat-card">
//             <h3>Four Wheeler Statistics</h3>
//             <p>Daily Earnings <span className="green">$800</span></p>
//             <p>Monthly Earnings <span className="blue">$16000</span></p>
//             <p>Occupied/Total Spaces <span className="purple">20/30</span></p>
//           </div>
//         </div>
//       </div>
//       <Footer />
//     </div>
//   );
// };

// export default ViewEarnings;


import React, { useState, useEffect } from "react";
import "../CSS/ViewEarnings.css";
import Footer from "../Components/Footer";

const ViewEarnings = () => {
  const [earningsData, setEarningsData] = useState({
    monthly: 28500,
    daily: 1250,
    twoWheeler: {
      daily: 450,
      monthly: 12500,
      occupied: 15,
      total: 20,
    },
    fourWheeler: {
      daily: 800,
      monthly: 16000,
      occupied: 20,
      total: 30,
    },
    peakHours: "9 AM - 5 PM",
  });

  // Simulate fetching data with useEffect
  useEffect(() => {
    // Here you can fetch data from an API and update the state
    // Example: setEarningsData(fetchedData);
  }, []);

  return (
    <div className="view-earnings">
      <div className="dashboard-container">
        <h2 className="dashboard-title">Dashboard Overview</h2>

        <div className="card">
          <h3>Monthly Earnings</h3>
          <span className="card-value blue">₹{earningsData.monthly}</span>
          <p>Monthly Revenue</p>
          <span className="card-icon">📈</span>
        </div>

        <div className="card">
          <h3>Peak Hours</h3>
          <span className="card-value purple">{earningsData.peakHours}</span>
          <p>Busiest Time Period</p>
          <span className="card-icon">📊</span>
        </div>
      </div>

      <div className="earnings-statistics">
        <div className="stats-card">
          <h3>Two Wheeler Statistics</h3>
          <div className="stat-row">
            <span>Daily Earnings</span>
            <span className="green">₹{earningsData.twoWheeler.daily}</span>
          </div>
          <div className="stat-row">
            <span>Monthly Earnings</span>
            <span className="blue">₹{earningsData.twoWheeler.monthly}</span>
          </div>
          <div className="stat-row">
            <span>Occupied/Total Spaces</span>
            <span className="purple">{earningsData.twoWheeler.occupied}/{earningsData.twoWheeler.total}</span>
          </div>
        </div>

        <div className="stats-card">
          <h3>Four Wheeler Statistics</h3>
          <div className="stat-row">
            <span>Daily Earnings</span>
            <span className="green">₹{earningsData.fourWheeler.daily}</span>
          </div>
          <div className="stat-row">
            <span>Monthly Earnings</span>
            <span className="blue">₹{earningsData.fourWheeler.monthly}</span>
          </div>
          <div className="stat-row">
            <span>Occupied/Total Spaces</span>
            <span className="purple">{earningsData.fourWheeler.occupied}/{earningsData.fourWheeler.total}</span>
          </div>
        </div>
      </div>

      <Footer />
    </div>
  );
};

export default ViewEarnings;


