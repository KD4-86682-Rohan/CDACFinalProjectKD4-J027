// src/components/AddLocationForm.js

import React, { useState } from "react";
import { addParkingLocation } from "../services/api";

const AddLocationForm = ({ vendorId }) => {
  const [city, setCity] = useState("");
  const [area, setArea] = useState("");
  const [location, setLocation] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const locationData = { city, area, location };

    try {
      const response = await addParkingLocation(vendorId, locationData);
      setMessage(response.data.message);
    } catch (error) {
      setMessage("Error: " + error.response.data.message);
    }
  };

  return (
    <div>
      <h2>Add New Parking Location</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>City:</label>
          <input
            type="text"
            value={city}
            onChange={(e) => setCity(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Area:</label>
          <input
            type="text"
            value={area}
            onChange={(e) => setArea(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Location:</label>
          <input
            type="text"
            value={location}
            onChange={(e) => setLocation(e.target.value)}
            required
          />
        </div>
        <button type="submit">Add Location</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default AddLocationForm;
