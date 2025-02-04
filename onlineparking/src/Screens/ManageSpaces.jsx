// import React from "react";
// import "../CSS/ManageSpaces.css"; // Add CSS for styling
// import Footer from "../Components/Footer";
// import AdminNavbar from "../Components/AdminNavbar";

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
//     <div>
//       <AdminNavbar />
//     <div className="container">
//       <h1>Manage Parking Spaces</h1>
//       <button className="add-btn">+ Add New Space</button>
//       <table className="parking-table">
//         <thead>
//           <tr>
//             <th>Name</th>
//             <th>Location</th>
//             <th>Total Spots</th>
//             <th>Available</th>
//             <th>Rate/Hour</th>
//             <th>Status</th>
//             <th>Actions</th>
//           </tr>
//         </thead>
//         <tbody>
//           {parkingSpaces.map((space, index) => (
//             <tr key={index}>
//               <td>{space.name}</td>
//               <td>{space.location}</td>
//               <td>{space.totalSpots}</td>
//               <td>{space.available}</td>
//               <td>{space.rate}</td>
//               <td>
//                 <span className={`status ${space.status.toLowerCase()}`}>
//                   {space.status}
//                 </span>
//               </td>
//               <td>
//                 <button className="edit-btn">✏️</button>
//                 <button className="delete-btn">🗑️</button>
//               </td>
//             </tr>
//           ))}
//         </tbody>
//       </table>
//     </div>
//     <Footer />
//     </div>
//   );
// };

// export default ManageSpaces;


import React from "react";
import "../CSS/ManageSpaces.css";
import Footer from "../Components/Footer";
import AdminNavbar from "../Components/AdminNavbar";

const ManageSpaces = () => {
  const parkingSpaces = [
    {
      name: "Downtown Parking A",
      location: "123 Main St",
      totalSpots: 50,
      available: 25,
      rate: "$5.99",
      status: "Active",
    },
    {
      name: "City Center Parking",
      location: "456 Market St",
      totalSpots: 75,
      available: 30,
      rate: "$7.99",
      status: "Active",
    },
  ];

  return (
    <div className="wrapper">
      <AdminNavbar />
      <div className="main-content">
        <div className="container">
          <h1>Manage Parking Spaces</h1>
          <button className="add-btn"> Add New Space</button>
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
              {parkingSpaces.map((space, index) => (
                <tr key={index}>
                  <td>{space.name}</td>
                  <td>{space.location}</td>
                  <td>{space.totalSpots}</td>
                  <td>{space.available}</td>
                  <td>{space.rate}</td>
                  <td>
                    <span className={`status ${space.status.toLowerCase()}`}>
                      {space.status}
                    </span>
                  </td>
                  <td>
                    <button className="edit-btn">✏️</button>
                    <button className="delete-btn">🗑️</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default ManageSpaces;
