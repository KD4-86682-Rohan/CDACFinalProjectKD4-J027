// import React, { useState } from "react";
// import "../CSS/AvailableParking.css";
// import UserNavbar from '../Components/UserNavbar';
// import Footer from '../Components/Footer';
// import "../CSS/Booking.css";

// const parkingData = [
//   {
//     name: 'Downtown Parking Complex',
//     distance: '0.5 miles',
//     type: 'covered parking',
//     monitoring: '24/7 surveillance',
//     price: '$5/hour',
//     spots: 12,
//     img: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRyFbbMl4yYqUv_Gvslzw0BNvqW01S-cYCZCw&s'
//   },
//   {
//     name: 'Central Mall Parking',
//     distance: '1.2 miles',
//     type: 'open parking',
//     monitoring: 'Guard patrolled',
//     price: '$4/hour',
//     spots: 8,
//     img: 'https://www.shutterstock.com/image-photo/parking-cars-without-people-260nw-1014849688.jpg'
//   },
//   {
//     name: 'Airport Terminal Parking',
//     distance: '3.0 miles',
//     type: 'covered parking',
//     monitoring: 'CCTV monitoring',
//     price: '$8/hour',
//     spots: 25,
//     img: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_mv2s15FHe9UIouR68MC3Lcrw_wZT1I7sXA&s'
//   },
//   {
//     name: 'Shopping District Parking',
//     distance: '0.8 miles',
//     type: 'open parking',
//     monitoring: 'Security personnel',
//     price: '$3/hour',
//     spots: 15,
//     img: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRH8AHxrq88bgOytLvlFjSBXG1hfHbvtqpAPg&snot'
//   },
//   {
//     name: 'Business Center Parking',
//     distance: '1.5 miles',
//     type: 'covered parking',
//     monitoring: '24/7 surveillance',
//     price: '$6/hour',
//     spots: 20,
//     img: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfCJxtKmrFVHJA6ijA4ApLYhJGIy14JNR24A&s'
//   },
//   {
//     name: 'Hospital Visitor Parking',
//     distance: '2.1 miles',
//     type: 'open parking',
//     monitoring: 'Guard patrolled',
//     price: '$2/hour',
//     spots: 30,
//     img: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7etL1t98UcHIsFeuioGB8ZYicxvJq3cUIIQ&s'
//   },
// ];

// function ParkingList() {
//     const [isOpen, setIsOpen] = useState(false);

//   const togglePopup = () => {
//     setIsOpen(!isOpen);
//   };
//   return (
//     <div>
//         <UserNavbar />
//     <div className="parking-list">
//       {parkingData.map((parking, index) => (
//         <div key={index} className="parking-card">
//           <div className="parking-image">
//           <img src={parking.img} alt={parking.name} />
//           </div>
//           <div className="parking-details">
//             <h3>{parking.name}</h3>
//             <p>{parking.distance}</p>
//             <p>{parking.type}</p>
//             <p>{parking.monitoring}</p>
//             <p><strong>{parking.price}</strong></p>
//             <p><strong>{parking.spots} spots</strong></p>
//             <div className="popup-container">
//       <button className="open-popup-button" onClick={togglePopup}>
//         Book Now
//       </button>
//             {isOpen && (
//         <div className="popup-overlay">
//           <div className="popup-content">
//             <h2>Book Parking Slot</h2>
//             <form>
//               <label>
//                 Select Date
//                 <input type="date" />
//               </label>
//               <div className="time-inputs">
//                 <div>
//                   <label>Arrival Time</label>
//                   <input type="time" />
//                 </div>
//                 <div>
//                   <label>Departure Time</label>
//                   <input type="time" />
//                 </div>
//               </div>
//               <div className="parking-slots">
//                 <label>Select Parking Slot</label>
//                 <div className="slots-grid">
//                   <button type="button" className="slot-button">Slot A1</button>
//                   <button type="button" className="slot-button">Slot A2</button>
//                   <button type="button" className="slot-button" disabled>Slot B1</button>
//                   <button type="button" className="slot-button">Slot B2</button>
//                   <button type="button" className="slot-button">Slot C1</button>
//                   <button type="button" className="slot-button" disabled>Slot C2</button>
//                 </div>
//               </div>
//               <button type="submit" className="submit-button">Book Now</button>
//             </form>
//             <button className="close-popup-button" onClick={togglePopup}>
//               Close
//             </button>
//           </div>
//         </div>
//       )}
//       </div>
//           </div>
//         </div>
//       ))}
//     </div>
//     <Footer />
//     </div>
//   );
// }

// export default ParkingList;

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

