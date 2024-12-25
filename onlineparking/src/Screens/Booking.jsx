import React, { useState } from "react";
import "../CSS/Booking.css";

const Booking = () => {
  const [isOpen, setIsOpen] = useState(false);

  const togglePopup = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className="popup-container">
      <button className="open-popup-button" onClick={togglePopup}>
        Book Parking Slot
      </button>
      {isOpen && (
        <div className="popup-overlay">
          <div className="popup-content">
            <h2>Book Parking Slot</h2>
            <form>
              <label>
                Select Date
                <input type="date" />
              </label>
              <div className="time-inputs">
                <div>
                  <label>Arrival Time</label>
                  <input type="time" />
                </div>
                <div>
                  <label>Departure Time</label>
                  <input type="time" />
                </div>
              </div>
              <div className="parking-slots">
                <label>Select Parking Slot</label>
                <div className="slots-grid">
                  <button type="button" className="slot-button">Slot A1</button>
                  <button type="button" className="slot-button">Slot A2</button>
                  <button type="button" className="slot-button" disabled>Slot B1</button>
                  <button type="button" className="slot-button">Slot B2</button>
                  <button type="button" className="slot-button">Slot C1</button>
                  <button type="button" className="slot-button" disabled>Slot C2</button>
                </div>
              </div>
              <button type="submit" className="submit-button">Book Now</button>
            </form>
            <button className="close-popup-button" onClick={togglePopup}>
              Close
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Booking;
