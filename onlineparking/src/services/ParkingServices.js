import axios from "axios";

const API_BASE_URL = "http://localhost:8081"; // Change this to your backend URL

export const getParkingLocations = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/ParkingLocation`);
    return response.data;
  } catch (error) {
    console.error("Error fetching parking locations:", error);
    throw error;
  }
};

export const getParkingSlots = async (locationId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/ParkingSlots/${locationId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching parking slots:", error);
    throw error;
  }
};
