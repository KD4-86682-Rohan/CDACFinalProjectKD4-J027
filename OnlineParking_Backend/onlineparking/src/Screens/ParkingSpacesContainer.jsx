import React, { useState, useEffect } from "react";
import ParkingSpaceCard from "../Components/ParkingSpaceCard";
import "./../CSS/ParkingSpaces.css";

const ParkingSpacesContainer = () => {
  const [parkingSpaces, setParkingSpaces] = useState([]);

  useEffect(() => {
    // Fetch parking space data from backend (Replace with API)
    setParkingSpaces([
      {
        blockName: "Block A",
        location: "Near Main Gate",
        rates: [
          { type: "Two Wheeler", rate: 20, capacity: 20 },
          { type: "Four Wheeler", rate: 40, capacity: 30 },
        ],
        status: "Active",
      },
      {
        blockName: "Block B",
        location: "Behind Building",
        rates: [
          { type: "Two Wheeler", rate: 25, capacity: 15 },
          { type: "Four Wheeler", rate: 45, capacity: 25 },
        ],
        status: "Inactive",
      },
    ]);
  }, []);

  return (
    <div className="parking-spaces-container">
      <h2>My Parking Spaces</h2>
      <button className="add-btn">+ Add New Parking Space</button>
      <div className="parking-list">
        {parkingSpaces.map((space, index) => (
          <ParkingSpaceCard key={index} {...space} />
        ))}
      </div>
    </div>
  );
};

export default ParkingSpacesContainer;
