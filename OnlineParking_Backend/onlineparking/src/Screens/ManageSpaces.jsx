
// import React from "react";
// import "../CSS/ManageSpaces.css";
// import Footer from "../Components/Footer";
// import VendorNavbar from "../Components/VendorNavbar";

// const ManageSpaces = () => {
//   const parkingSpaces = [
//     {
//       name: "Downtown Parking A",
//       location: "123 Main St",
//       totalSpots: 50,
//       available: 25,
//       rate: "$5.99",
//       status: "Active",
//     },
//     {
//       name: "City Center Parking",
//       location: "456 Market St",
//       totalSpots: 75,
//       available: 30,
//       rate: "$7.99",
//       status: "Active",
//     },
//   ];
//   return (
//     <div className="wrapper">
//       <VendorNavbar />
//       <div className="main-content">
//         <div className="container">
//           <h1>Manage Parking Spaces</h1>
//           <button className="add-btn">Add New Space</button>
//           <table className="parking-table">
//             <thead>
//               <tr>
//                 <th>Name</th>
//                 <th>Location</th>
//                 <th>Total Spots</th>
//                 <th>Available</th>
//                 <th>Rate/Hour</th>
//                 <th>Status</th>
//                 <th>Actions</th>
//               </tr>
//             </thead>
//             <tbody>
//               {parkingSpaces.map((space, index) => (
//                 <tr key={index}>
//                   <td>{space.name}</td>
//                   <td>{space.location}</td>
//                   <td>{space.totalSpots}</td>
//                   <td>{space.available}</td>
//                   <td>{space.rate}</td>
//                   <td>
//                     <span className={`status ${space.status.toLowerCase()}`}>
//                       {space.status}
//                     </span>
//                   </td>
//                   <td>
//                     <button className="edit-btn">✏️</button>
//                     <button className="delete-btn">🗑️</button>
//                   </td>
//                 </tr>
//               ))}
//             </tbody>
//           </table>
//         </div>
//       </div>
//       <Footer />
//     </div>
//   );
  
  
// };

// export default ManageSpaces;


import React, { useEffect, useState } from "react";
import "../CSS/ManageSpaces.css";
import Footer from "../Components/Footer";
import VendorNavbar from "../Components/VendorNavbar";
import { Link } from "react-router-dom";

const ManageSpaces = () => {
  const [parkingSpaces, setParkingSpaces] = useState([]);
  
  useEffect(() => {
    fetchParkingLocations();
  }, []);

  const fetchParkingLocations = async () => {
    try {
      const response = await fetch("http://localhost:8081/ParkingLocation"); // Update URL if needed
      if (!response.ok) {
        throw new Error("Failed to fetch parking locations");
      }
      const data = await response.json();
      setParkingSpaces(data);
    } catch (error) {
      console.error("Error fetching parking locations:", error);
    }
  };

  return (
    <div className="wrapper">
      <VendorNavbar />
      <div className="main-content">
        <div className="container">
          <h1>Manage Parking Spaces</h1>
          <button className="add-btn"><Link className="nav-link" to="/AddLocation">Add New Space</Link></button>
          <button className="add-btn"><Link className="nav-link" to="/AddSlots">Add New Slot</Link></button>
          <table className="parking-table">
            <thead>
              <tr>
                <th>Name</th>
                <th>Location</th>
                <th>Total Spots</th>
                <th>Available</th>
                <th>Rate/Hour</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {parkingSpaces.length > 0 ? (
                parkingSpaces.map((space, index) => (
                  <tr key={index}>
                    <td>{space.name || "N/A"}</td>
                    <td>{space.area}, {space.city}</td>
                    <td>{space.totalSpots || "N/A"}</td>
                    <td>{space.available || "N/A"}</td>
                    <td>{space.rate ? `$${space.rate}` : "N/A"}</td>
                    <td>
                      <span className={`status ${space.status ? "active" : "inactive"}`}>
                        {space.status ? "Active" : "Inactive"}
                      </span>
                    </td>
                    <td>
                      <button className="edit-btn">✏️</button>
                      <button className="delete-btn">🗑️</button>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="7">No parking spaces found.</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default ManageSpaces;

