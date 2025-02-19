// src/api.js

import axios from "axios";

// Set base URL for API
const API_URL = "http://localhost:8080"; // Replace with your backend URL

export const addParkingLocation = (vendorId, locationData) => {
  return axios.post(`${API_URL}/ParkingLocation/addLocation/${vendorId}`, locationData);
};

export const addParkingSlot = (locationId, slotData) => {
  return axios.post(`${API_URL}/ParkingSlot/addSlot/${locationId}`, slotData);
};
