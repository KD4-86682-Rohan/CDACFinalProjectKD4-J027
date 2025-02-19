
// import React from "react";
// import Footer from "../Components/Footer";
// import UserNavbar from "../Components/UserNavbar";
// // import "../CSS/MyBooking.css"; // Import the CSS file for styling
// import "../CSS/MyBooking.css";

// function MyBookings() {
//   const bookings = [
//     {
//       id: 1,
//       image: "https://img.freepik.com/free-photo/full-car-parking-lot-mall_1268-14318.jpg?t=st=1738213604~exp=1738217204~hmac=9928cc74a76e2e0f3ac97d1211ae0d1dbdff8194fbaab58e34450c4d0d093c78&w=996",
//       location: "Downtown Parking Complex",
//       status: "active",
//       date: "2024-01-15",
//       time: "09:00 AM - 05:00 PM",
//       spot: "Spot A-123",
//       cost: "$5/hour",
//     },
//     {
//       id: 2,
//       image: "https://img.freepik.com/free-photo/horizontal-picture-car-parking-underground-garage-interior-with-neon-lights-autocars-parked-buildings-urban-constructions-space-transportation-vehicle-night-city-concept_343059-3077.jpg?t=st=1738213711~exp=1738217311~hmac=a4549ea1385cd630701627135ac3d7c5f8ae947210cb5bbe2399d5a1e1208a82&w=996", // Replace with actual image URL
//       location: "Central Mall Parking",
//       status: "upcoming",
//       date: "2024-01-18",
//       time: "10:00 AM - 02:00 PM",
//       spot: "Spot B-456",
//       cost: "$4/hour",
//     },
//     {
//       id: 3,
//       image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_mv2s15FHe9UIouR68MC3Lcrw_wZT1I7sXA", // Replace with actual image URL
//       location: "Airport Terminal Parking",
//       status: "completed",
//       date: "2024-01-10",
//       time: "02:00 PM - 06:00 PM",
//       spot: "Spot C-789",
//       cost: "$8/hour",
//     },
//   ];

//   return (
//     <div>
//       <UserNavbar />
//       <div className="container my-4">
//         {/* Navbar */}
//         <div className="d-flex justify-content-between align-items-center mb-3">
//           <h1 className="text-center">My Bookings</h1>
//           <div>
//             <input
//               type="text"
//               className="form-control me-2"
//               placeholder="Search bookings..."
//               style={{ display: "inline-block", width: "300px" }}
//             />
//             <select className="form-select" style={{ width: "150px", display: "inline-block" }}>
//               <option value="all">All Bookings</option>
//               <option value="active">Active</option>
//               <option value="upcoming">Upcoming</option>
//               <option value="completed">Completed</option>
//             </select>
//           </div>
//         </div>

//         {/* Booking Cards */}
//         <div className="row d-flex justify-content-center">
//           {bookings.map((booking) => (
//             <div className="col-lg-3 col-md-6 col-sm-12 mb-4" key={booking.id}>
//               <div className="card shadow-sm">
//                 <img
//                   src={booking.image}
//                   alt={booking.location}
//                   className="card-img-top"
//                 />
//                 <div className="card-body">
//                   <h5 className="card-title">{booking.location}</h5>
//                   <p className="card-text">
//                     <span className={`badge bg-${booking.status}`}>
//                       {booking.status.charAt(0).toUpperCase() + booking.status.slice(1)}
//                     </span>
//                   </p>
//                   <p className="card-text">
//                     <i className="bi bi-calendar"></i> {booking.date}
//                   </p>
//                   <p className="card-text">
//                     <i className="bi bi-clock"></i> {booking.time}
//                   </p>
//                   <p className="card-text">
//                     <i className="bi bi-geo-alt"></i> {booking.spot}
//                   </p>
//                   <p className="card-text text-primary fw-bold">{booking.cost}</p>

//                   {booking.status === "completed" ? (
//                       <button className="btn btn-warning btn-sm w-100">Rate Us</button>
//                     ) : (
//                       <div className="d-flex justify-content-between">
//                         <button className="btn btn-primary btn-sm">Extend</button>
//                         <button className="btn btn-danger btn-sm">Cancel</button>
//                       </div>
//                     )}

//                 </div>
//               </div>
//             </div>
//           ))}
//         </div>
//       </div>
//       <Footer />
//     </div>
//   );
// }

// export default MyBookings;

import Footer from "../Components/Footer";
import UserNavbar from "../Components/UserNavbar";
import "../CSS/MyBooking.css"; // Import the CSS file for styling
import React, { useState, useEffect } from "react";
import axios from "axios";

const MyBookings = ({ userId }) => {
  const [activeBookings, setActiveBookings] = useState([]);
  const [completedBookings, setCompletedBookings] = useState([]);
  const [upcomingBookings, setUpcomingBookings] = useState([]);
  const [newStartTime, setNewStartTime] = useState("");
  const [newEndTime, setNewEndTime] = useState("");
  
  // Fetch all bookings based on status
  useEffect(() => {
    if (userId) {
      fetchBookings("ACTIVE", setActiveBookings);
      fetchBookings("COMPLETED", setCompletedBookings);
      fetchBookings("UPCOMING", setUpcomingBookings);
    }
  }, [userId]);

  const fetchBookings = async (status, setState) => {
    try {
      const response = await axios.get(`http://localhost:8080/Booking/getAllBooking/${userId}`);
      const bookings = response.data.filter((booking) => booking.status === status);
      setState(bookings);
    } catch (error) {
      console.error(`Error fetching ${status} bookings:`, error);
    }
  };

  const cancelBooking = async (bookingId) => {
    try {
      const response = await axios.delete(`http://localhost:8080/Booking/cancelBooking/${userId}?slotId=${bookingId}`);
      fetchBookings("ACTIVE", setActiveBookings); // Refresh active bookings after cancellation
    } catch (error) {
      console.error("Error cancelling booking:", error);
    }
  };

  const extendBooking = async (bookingId) => {
    const updatedBooking = {
      startTime: newStartTime,
      endTime: newEndTime,
    };
    try {
      const response = await axios.put(`http://localhost:8080/Booking/extendBooking/${userId}`, updatedBooking);
      fetchBookings("ACTIVE", setActiveBookings); // Refresh active bookings after extension
    } catch (error) {
      console.error("Error extending booking:", error);
    }
  };

  return (
    <div>
      <UserNavbar />
      <h2>My Bookings</h2>

      <h3>Upcoming Bookings</h3>
      {upcomingBookings.length > 0 ? (
        upcomingBookings.map((booking) => (
          <div key={booking.id}>
            <p>Slot: {booking.slotId}</p>
            <p>Time: {booking.startTime} - {booking.endTime}</p>
          </div>
        ))
      ) : (
        <p>No upcoming bookings.</p>
      )}

      <h3>Active Bookings</h3>
      {activeBookings.length > 0 ? (
        activeBookings.map((booking) => (
          <div key={booking.id}>
            <p>Slot: {booking.slotId}</p>
            <p>Time: {booking.startTime} - {booking.endTime}</p>
            <button onClick={() => cancelBooking(booking.id)}>Cancel Booking</button>
            <input
              type="datetime-local"
              value={newStartTime}
              onChange={(e) => setNewStartTime(e.target.value)}
              placeholder="New Start Time"
            />
            <input
              type="datetime-local"
              value={newEndTime}
              onChange={(e) => setNewEndTime(e.target.value)}
              placeholder="New End Time"
            />
            <button onClick={() => extendBooking(booking.id)}>Extend Booking</button>
          </div>
        ))
      ) : (
        <p>No active bookings.</p>
      )}

      <h3>Completed Bookings</h3>
      {completedBookings.length > 0 ? (
        completedBookings.map((booking) => (
          <div key={booking.id}>
            <p>Slot: {booking.slotId}</p>
            <p>Time: {booking.startTime} - {booking.endTime}</p>
          </div>
        ))
      ) : (
        <p>No completed bookings.</p>
      )}
      
      <Footer />
    </div>
  );
};

export default MyBookings;





