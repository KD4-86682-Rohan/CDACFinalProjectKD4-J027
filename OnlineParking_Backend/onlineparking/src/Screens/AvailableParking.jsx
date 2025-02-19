import React, { useState } from "react";
import "../CSS/AvailableParking.css";
import UserNavbar from '../Components/UserNavbar';
import Footer from '../Components/Footer';
import "../CSS/Booking.css";

const parkingData = [
  {
    name: 'Downtown Parking Complex',
    distance: '0.5 miles',
    type: 'Covered Parking',
    monitoring: '24/7 Surveillance',
    price: '$5/hour',
    spots: 12,
    img: 'https://img.freepik.com/free-photo/full-car-parking-lot-mall_1268-14318.jpg?t=st=1738213604~exp=1738217204~hmac=9928cc74a76e2e0f3ac97d1211ae0d1dbdff8194fbaab58e34450c4d0d093c78&w=996'
  },
  {
    name: 'Central Mall Parking',
    distance: '1.2 miles',
    type: 'Open Parking',
    monitoring: 'Guard Patrolled',
    price: '$4/hour',
    spots: 8,
    img: 'https://img.freepik.com/free-photo/horizontal-picture-car-parking-underground-garage-interior-with-neon-lights-autocars-parked-buildings-urban-constructions-space-transportation-vehicle-night-city-concept_343059-3077.jpg?t=st=1738213711~exp=1738217311~hmac=a4549ea1385cd630701627135ac3d7c5f8ae947210cb5bbe2399d5a1e1208a82&w=996'
  },
  {
    name: 'Airport Terminal Parking',
    distance: '3.0 miles',
    type: 'Covered Parking',
    monitoring: 'CCTV Monitoring',
    price: '$8/hour',
    spots: 25,
    img: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_mv2s15FHe9UIouR68MC3Lcrw_wZT1I7sXA&s'
  },
  {
    name: 'Shopping District Parking',
    distance: '0.8 miles',
    type: 'Open Parking',
    monitoring: 'Security Personnel',
    price: '$3/hour',
    spots: 15,
    img: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH8AHxrq88bgOytLvlFjSBXG1hfHbvtqpAPg&snot'
  }
];

function ParkingList() {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedSlot, setSelectedSlot] = useState(null);
  const [selectedParking, setSelectedParking] = useState(null);

  const togglePopup = (parking = null) => {
    setSelectedParking(parking);
    setIsOpen(!!parking);
  };

  const handleSlotSelection = (slot) => {
    setSelectedSlot(slot);
  };

  return (
    <div>
      <UserNavbar />
      <div className="parking-list">
        {parkingData.map((parking, index) => (
          <div key={index} className="parking-card">
            <img src={parking.img} alt={parking.name} className="parking-img" />
            <div className="parking-details">
              <h3>{parking.name}</h3>
              <p><strong>Distance:</strong> {parking.distance}</p>
              <p><strong>Type:</strong> {parking.type}</p>
              <p><strong>Security:</strong> {parking.monitoring}</p>
              <p><strong>Rate:</strong> {parking.price}</p>
              <p><strong>Available Spots:</strong> {parking.spots}</p>
              <button className="book-btn" onClick={() => togglePopup(parking)}>
                Book Now
              </button>
            </div>
          </div>
        ))}
      </div>

      {/* Popup */}
      {isOpen && selectedParking && (
        <div className="popup-overlay" onClick={() => togglePopup(null)}>
          <div className="popup-content" onClick={(e) => e.stopPropagation()}>
            <h2>Book Parking Slot</h2>
            <p><strong>Location:</strong> {selectedParking.name}</p>
            <form>
              <label>Select Date</label>
              <input type="date" />

              <div className="time-inputs">
                <label>Arrival Time</label>
                <input type="time" />
                <label>Departure Time</label>
                <input type="time" />
              </div>

              <div className="parking-slots">
                <label>Select Parking Slot</label>
                <div className="slots-grid">
                  {["Slot A1", "Slot A2", "Slot B1", "Slot B2", "Slot C1", "Slot C2"].map((slot, i) => (
                    <button 
                      key={i} 
                      type="button" 
                      className={`slot-button ${selectedSlot === slot ? "selected" : ""}`} 
                      onClick={() => handleSlotSelection(slot)}
                    >
                      {slot}
                    </button>
                  ))}
                </div>
              </div>

              <button type="submit" className="submit-btn">Confirm Booking</button>
            </form>
            <button className="close-btn" onClick={() => togglePopup(null)}>
              Close
            </button>
          </div>
        </div>
      )}
      <Footer />
    </div>
  );
}

export default ParkingList;

