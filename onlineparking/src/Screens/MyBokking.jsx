
import React from "react";
import Footer from "../Components/Footer";
import UserNavbar from "../Components/UserNavbar";
// import "../CSS/MyBooking.css"; // Import the CSS file for styling
import "../CSS/MyBooking.css";

function MyBookings() {
  const bookings = [
    {
      id: 1,
      image: "https://img.freepik.com/free-photo/full-car-parking-lot-mall_1268-14318.jpg?t=st=1738213604~exp=1738217204~hmac=9928cc74a76e2e0f3ac97d1211ae0d1dbdff8194fbaab58e34450c4d0d093c78&w=996",
      location: "Downtown Parking Complex",
      status: "active",
      date: "2024-01-15",
      time: "09:00 AM - 05:00 PM",
      spot: "Spot A-123",
      cost: "$5/hour",
    },
    {
      id: 2,
      image: "https://img.freepik.com/free-photo/horizontal-picture-car-parking-underground-garage-interior-with-neon-lights-autocars-parked-buildings-urban-constructions-space-transportation-vehicle-night-city-concept_343059-3077.jpg?t=st=1738213711~exp=1738217311~hmac=a4549ea1385cd630701627135ac3d7c5f8ae947210cb5bbe2399d5a1e1208a82&w=996", // Replace with actual image URL
      location: "Central Mall Parking",
      status: "upcoming",
      date: "2024-01-18",
      time: "10:00 AM - 02:00 PM",
      spot: "Spot B-456",
      cost: "$4/hour",
    },
    {
      id: 3,
      image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_mv2s15FHe9UIouR68MC3Lcrw_wZT1I7sXA", // Replace with actual image URL
      location: "Airport Terminal Parking",
      status: "completed",
      date: "2024-01-10",
      time: "02:00 PM - 06:00 PM",
      spot: "Spot C-789",
      cost: "$8/hour",
    },
  ];

  return (
    <div>
      <UserNavbar />
      <div className="container my-4">
        {/* Navbar */}
        <div className="d-flex justify-content-between align-items-center mb-3">
          <h1 className="text-center">My Bookings</h1>
          <div>
            <input
              type="text"
              className="form-control me-2"
              placeholder="Search bookings..."
              style={{ display: "inline-block", width: "300px" }}
            />
            <select className="form-select" style={{ width: "150px", display: "inline-block" }}>
              <option value="all">All Bookings</option>
              <option value="active">Active</option>
              <option value="upcoming">Upcoming</option>
              <option value="completed">Completed</option>
            </select>
          </div>
        </div>

        {/* Booking Cards */}
        <div className="row d-flex justify-content-center">
          {bookings.map((booking) => (
            <div className="col-lg-3 col-md-6 col-sm-12 mb-4" key={booking.id}>
              <div className="card shadow-sm">
                <img
                  src={booking.image}
                  alt={booking.location}
                  className="card-img-top"
                />
                <div className="card-body">
                  <h5 className="card-title">{booking.location}</h5>
                  <p className="card-text">
                    <span className={`badge bg-${booking.status}`}>
                      {booking.status.charAt(0).toUpperCase() + booking.status.slice(1)}
                    </span>
                  </p>
                  <p className="card-text">
                    <i className="bi bi-calendar"></i> {booking.date}
                  </p>
                  <p className="card-text">
                    <i className="bi bi-clock"></i> {booking.time}
                  </p>
                  <p className="card-text">
                    <i className="bi bi-geo-alt"></i> {booking.spot}
                  </p>
                  <p className="card-text text-primary fw-bold">{booking.cost}</p>

                  {booking.status === "completed" ? (
                      <button className="btn btn-warning btn-sm w-100">Rate Us</button>
                    ) : (
                      <div className="d-flex justify-content-between">
                        <button className="btn btn-primary btn-sm">Extend</button>
                        <button className="btn btn-danger btn-sm">Cancel</button>
                      </div>
                    )}

                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default MyBookings;

// import React, { useEffect, useState } from "react";
// import axios from "axios";
// import Footer from "../Components/Footer";
// import UserNavbar from "../Components/UserNavbar";
// import "../CSS/MyBooking.css";

// function MyBookings() {
//   const [bookings, setBookings] = useState([]);
//   const [searchTerm, setSearchTerm] = useState("");
//   const [filterStatus, setFilterStatus] = useState("all");

//   useEffect(() => {
//     // Fetch bookings from the backend
//     const fetchBookings = async () => {
//       try {
//         const userId = 1; // Replace with dynamic user ID as needed
//         const response = await axios.get(`/Booking/getAllBooking/${userId}`);
//         setBookings(response.data);
//       } catch (error) {
//         console.error("Error fetching bookings:", error);
//       }
//     };

//     fetchBookings();
//   }, []);

//   const handleExtend = async (bookingId) => {
//     // Implement the logic to extend the booking
//     try {
//       const userId = 1; // Replace with dynamic user ID as needed
//       const slotId = bookingId; // Assuming bookingId corresponds to slotId
//       const updatedEndTime = "2024-01-15T18:00:00"; // Replace with the new end time
//       const response = await axios.put(`/Booking/extendBooking/${userId}?slotId=${slotId}`, {
//         endTime: updatedEndTime,
//       });
//       console.log(response.data);
//       // Optionally, refresh bookings after extending
//     } catch (error) {
//       console.error("Error extending booking:", error);
//     }
//   };

//   const handleCancel = async (bookingId) => {
//     // Implement the logic to cancel the booking
//     try {
//       const userId = 1; // Replace with dynamic user ID as needed
//       const slotId = bookingId; // Assuming bookingId corresponds to slotId
//       const response = await axios.delete(`/Booking/cancelBooking/${userId}?slotId=${slotId}`);
//       console.log(response.data);
//       // Optionally, refresh bookings after cancellation
//     } catch (error) {
//       console.error("Error cancelling booking:", error);
//     }
//   };

//   const filteredBookings = bookings.filter((booking) => {
//     const matchesSearch = booking.location.toLowerCase().includes(searchTerm.toLowerCase());
//     const matchesStatus = filterStatus === "all" || booking.status === filterStatus;
//     return matchesSearch && matchesStatus;
//   });

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
//               value={searchTerm}
//               onChange={(e) => setSearchTerm(e.target.value)}
//             />
//             <select
//               className="form-select"
//               style={{ width: "150px", display: "inline-block" }}
//               value={filterStatus}
//               onChange={(e) => setFilterStatus(e.target.value)}
//             >
//               <option value="all">All Bookings</option>
//               <option value="active">Active</option>
//               <option value="upcoming">Upcoming</option>
//               <option value="completed">Completed</option>
//             </select>
//           </div>
//         </div>

//         {/* Booking Cards */}
//         <div className="row d-flex justify-content-center">
//           {filteredBookings.map((booking) => (
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
//                     <button className="btn btn-warning btn-sm w-100">Rate Us</button>
//                   ) : (
//                     <div className="d-flex justify-content-between">
//                       <button
//                         className="btn btn-primary btn-sm"
//                         onClick={() => handleExtend(booking.id)}
//                       >
//                         Extend
//                       </button>
//                       <button
//                         className="btn btn-danger btn-sm"
//                         onClick={() => handleCancel(booking.id)}
//                       >
//                         Cancel
//                       </button>
//                     </div>
//                   )}
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

