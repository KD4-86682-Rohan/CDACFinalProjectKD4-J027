import React from "react";
import ParkingSpacesContainer from "../Screens/ParkingSpacesContainer";
import VendorNavbar from "../Components/VendorNavbar";
import Footer from "../Components/Footer";

const ParkingSpaces = () => {
  return (
    <div>
      <VendorNavbar />
    <div className="page-container">
      
      <ParkingSpacesContainer />
    </div>
    <Footer />
    </div>
  );
};

export default ParkingSpaces;
