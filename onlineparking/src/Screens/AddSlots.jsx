// src/components/AddSlotForm.js

import React, { useState } from "react";
import { addParkingSlot } from "../services/api";

const AddSlotForm = ({ locationId }) => {
  const [slotNumber, setSlotNumber] = useState("");
  const [slotType, setSlotType] = useState("CAR");
  const [pricePerHour2W, setPricePerHour2W] = useState("");
  const [pricePerHour4W, setPricePerHour4W] = useState("");
  const [slotStatus, setSlotStatus] = useState("AVAILABLE");
  const [message, setMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const slotData = {
      slotNumber,
      slotType,
      pricePerHour2W,
      pricePerHour4W,
      slotStatus,
    };

    try {
      const response = await addParkingSlot(locationId, slotData);
      setMessage(response.data.message);
    } catch (error) {
      setMessage("Error: " + error.response.data.message);
    }
  };

  return (
    <div>
      <h2>Add New Parking Slot</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Slot Number:</label>
          <input
            type="text"
            value={slotNumber}
            onChange={(e) => setSlotNumber(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Slot Type:</label>
          <select
            value={slotType}
            onChange={(e) => setSlotType(e.target.value)}
            required
          >
            <option value="CAR">Car</option>
            <option value="BIKE">Bike</option>
          </select>
        </div>
        <div>
          <label>Price per Hour for Bike:</label>
          <input
            type="number"
            value={pricePerHour2W}
            onChange={(e) => setPricePerHour2W(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Price per Hour for Car:</label>
          <input
            type="number"
            value={pricePerHour4W}
            onChange={(e) => setPricePerHour4W(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Slot Status:</label>
          <select
            value={slotStatus}
            onChange={(e) => setSlotStatus(e.target.value)}
            required
          >
            <option value="AVAILABLE">Available</option>
            <option value="OCCUPIED">Occupied</option>
          </select>
        </div>
        <button type="submit">Add Slot</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default AddSlotForm;
