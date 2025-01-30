import React, { useState } from "react";
import Footer from "../Components/Footer";
import UserNavbar from "../Components/UserNavbar";
import "../CSS/UserHome.css"
import "../CSS/Booking.css";


function UserHome() {
  const [isOpen, setIsOpen] = useState(false);

  const togglePopup = () => {
    setIsOpen(!isOpen);
  };
    return ( 
        <div >
            <UserNavbar/>
      {/* Search Banner */}
      <section className="search-banner">
        <h1>Find Your Perfect Parking Spot</h1>
        <p>Quick, easy, and convenient parking solutions for your vehicle</p>
        <div className="search-bar">
        <input
            type="text"
            placeholder="Select city."
            //drop down for cities 
          />
          <input
            type="text"
            placeholder="Search by location or landmark..."
          />
        </div>
      </section>

      {/* Available Parking Spots */}
      <section className="available-parking">
        <h2>Available Parking Spots</h2>
        <div className="parking-spots">
          {[
            {
              title: "Downtown Parking Complex",
              distance: "0.5 miles",
              price: "$5/hour",
              spots: "12 spots available",
              img: "https://img.freepik.com/free-photo/full-car-parking-lot-mall_1268-14318.jpg?t=st=1738213604~exp=1738217204~hmac=9928cc74a76e2e0f3ac97d1211ae0d1dbdff8194fbaab58e34450c4d0d093c78&w=996",
              // img: "../Images/AvailableParking1.jpg"
            },
            {
              title: "Central Mall Parking",
              distance: "2 miles",
              price: "$4/hour",
              spots: "8 spots available",
              img: "https://img.freepik.com/free-photo/horizontal-picture-car-parking-underground-garage-interior-with-neon-lights-autocars-parked-buildings-urban-constructions-space-transportation-vehicle-night-city-concept_343059-3077.jpg?t=st=1738213711~exp=1738217311~hmac=a4549ea1385cd630701627135ac3d7c5f8ae947210cb5bbe2399d5a1e1208a82&w=996",
            },
            {
              title: "Airport Terminal Parking",
              distance: "5 miles",
              price: "$6/hour",
              spots: "15 spots available",
              img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_mv2s15FHe9UIouR68MC3Lcrw_wZT1I7sXA&s",
            },
            {
              title: "Shopping District Parking",
              distance: "3 miles",
              price: "$5/hour",
              spots: "10 spots available",
              img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH8AHxrq88bgOytLvlFjSBXG1hfHbvtqpAPg&snot",
            },
          ].map((spot, index) => (
            <div key={index} className="parking-card">
              <img src={spot.img} alt={spot.title} />
              <h5>{spot.title}</h5>
              <p>{spot.distance}</p>
              <p>{spot.price}</p>
              <p>{spot.spots}</p>
              <div className="popup-container">
      <button className="open-popup-button" onClick={togglePopup}>
        Book Now
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
            </div>
          ))}
        </div>
      </section>
          
      
      <Footer/>
    </div>
     );
}
export default UserHome;