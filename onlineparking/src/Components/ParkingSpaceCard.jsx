import React from "react";
import "./../CSS/ParkingSpaces.css";

const ParkingSpaceCard = ({ blockName, location, rates, capacity, status }) => {
  return (
    <div className="parking-space-card">
      <h3>{blockName}</h3>
      <p className="location">{location}</p>
      <div className="rates-container">
        {rates.map((rate, index) => (
          <div key={index} className="rate-box">
            <h4>{rate.type}</h4>
            <p>Rate per hour: ₹{rate.rate}</p>
            <p>Capacity: {rate.capacity}</p>
          </div>
        ))}
      </div>
      <div className="status-container">
        <span className={`status-dot ${status.toLowerCase()}`}></span>
        <span className="status-text">{status}</span>
      </div>
      <div className="actions">
        <button className="edit-btn">✏️</button>
        <button className="delete-btn">🗑️</button>
      </div>
    </div>
  );
};

export default ParkingSpaceCard;
