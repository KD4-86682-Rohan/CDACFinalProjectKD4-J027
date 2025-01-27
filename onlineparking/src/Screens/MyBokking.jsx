import React from "react";
import Footer from "../Components/Footer";
import UserNavbar from "../Components/UserNavbar";

function MyBookings() {
  const bookings = [
    {
      id: 1,
      image: "https://via.placeholder.com/300", // Replace with actual image URL
      location: "Downtown Parking Complex",
      status: "active",
      date: "2024-01-15",
      time: "09:00 AM - 05:00 PM",
      spot: "Spot A-123",
      cost: "$5/hour",
    },
    {
      id: 2,
      image: "https://via.placeholder.com/300", // Replace with actual image URL
      location: "Central Mall Parking",
      status: "upcoming",
      date: "2024-01-18",
      time: "10:00 AM - 02:00 PM",
      spot: "Spot B-456",
      cost: "$4/hour",
    },
    {
      id: 3,
      image: "https://via.placeholder.com/300", // Replace with actual image URL
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
        <h1>My Bookings</h1>
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
      <div className="row">
        {bookings.map((booking) => (
          <div className="col-md-4 mb-3" key={booking.id}>
            <div className="card shadow-sm">
              <img
                src={booking.image}
                alt={booking.location}
                className="card-img-top"
                style={{ height: "150px", objectFit: "cover" }}
              />
              <div className="card-body">
                <h5 className="card-title">{booking.location}</h5>
                <p className="card-text">
                  <span className="badge bg-success me-">
                    {booking.status === "active" && "Active"}
                  </span>
                  <span className="badge bg-primary me-2">
                    {booking.status === "upcoming" && "Upcoming"}
                  </span>
                  <span className="badge bg-secondary me-2">
                    {booking.status === "completed" && "Completed"}
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
                <div className="d-flex justify-content-between">
                  <button className="btn btn-primary btn-sm">Extend</button>
                  <button className="btn btn-danger btn-sm">Cancel</button>
                </div>
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
