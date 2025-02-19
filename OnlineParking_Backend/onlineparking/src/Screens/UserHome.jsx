// import React, { useState, useEffect } from "react";
// import Footer from "../Components/Footer";
// import UserNavbar from "../Components/UserNavbar";
// import "../CSS/UserHome.css";
// import "../CSS/Booking.css";

// function UserHome() {
//     const [parkingSpots, setParkingSpots] = useState([]);
//     const [filteredSpots, setFilteredSpots] = useState([]);
//     const [city, setCity] = useState("");
//     const [area, setArea] = useState("");
//     const [isOpen, setIsOpen] = useState(false);
//     const [selectedSpot, setSelectedSpot] = useState(null);
//     const [selectedDate, setSelectedDate] = useState("");
//     const [arrivalTime, setArrivalTime] = useState("");
//     const [departureTime, setDepartureTime] = useState("");
//     const [selectedSlot, setSelectedSlot] = useState("");

//     // Static image for parking locations
//     const staticImage = "https://via.placeholder.com/300x200"; // Replace with actual image URL

//     const togglePopup = (spot) => {
//         setSelectedSpot(spot);
//         setIsOpen(!isOpen);
//     };

//     const handleSlotSelect = (slot) => {
//         setSelectedSlot(slot);
//     };

//     const handleSubmitBooking = (e) => {
//         e.preventDefault();
//         // Here you would typically call an API to book the spot.
//         console.log("Booking submitted:", { selectedSpot, selectedDate, arrivalTime, departureTime, selectedSlot });
//         // Close the popup after submitting
//         setIsOpen(false);
//     };

//     useEffect(() => {
//         const fetchParkingSpots = async () => {
//             try {
//                 const response = await fetch("http://localhost:8081/ParkingLocation");
//                 const data = await response.json();
//                 if (Array.isArray(data)) {
//                     setParkingSpots(data);
//                     setFilteredSpots(data); // Initially show all locations
//                 } else {
//                     console.error("Unexpected response format:", data);
//                 }
//             } catch (error) {
//                 console.error("Error fetching parking spots:", error);
//             }
//         };

//         fetchParkingSpots();
//     }, []);

//     // Handle city and area filtering
//     const handleSearch = () => {
//         let filtered = parkingSpots;

//         if (city.trim()) {
//             filtered = filtered.filter((spot) => spot.city?.toLowerCase().includes(city.toLowerCase()));
//         }
//         if (area.trim()) {
//             filtered = filtered.filter((spot) => spot.area?.toLowerCase().includes(area.toLowerCase()));
//         }

//         setFilteredSpots(filtered);
//     };

//     return (
//         <div>
//             <UserNavbar />
//             {/* Search Banner */}
//             <section className="search-banner">
//                 <h1>Find Your Perfect Parking Spot</h1>
//                 <p>Quick, easy, and convenient parking solutions for your vehicle</p>
//                 <div className="search-bar">
//                     <input
//                         type="text"
//                         placeholder="Enter City"
//                         value={city}
//                         onChange={(e) => setCity(e.target.value)}
//                     />
//                     <input
//                         type="text"
//                         placeholder="Enter Area (Optional)"
//                         value={area}
//                         onChange={(e) => setArea(e.target.value)}
//                     />
//                     <button onClick={handleSearch}>Search</button>
//                 </div>
//             </section>

//             {/* Available Parking Spots */}
//             <section className="available-parking">
//                 <h2>Available Parking Spots</h2>
//                 <div className="parking-spots">
//                     {filteredSpots.length > 0 ? (
//                         filteredSpots.map((spot, index) => (
//                             <div key={index} className="parking-card">
//                                 <img src={staticImage} alt="Parking Spot" className="parking-image" />
//                                 <div className="parking-info">
//                                     <h5>{spot.location || "Unknown Location"}</h5>
//                                     <p>City: {spot.city || "N/A"}</p>
//                                     <p>Area: {spot.area || "N/A"}</p>
//                                     <button className="open-popup-button" onClick={() => togglePopup(spot)}>
//                                         Book Now
//                                     </button>
//                                 </div>
//                             </div>
//                         ))
//                     ) : (
//                         <p>No parking spots found for the selected city and area.</p>
//                     )}
//                 </div>
//             </section>

//             {/* Popup Form */}
//             {isOpen && (
//                 <div className="popup-overlay">
//                     <div className="popup-content">
//                         <h2>Book Parking Slot</h2>
//                         <form onSubmit={handleSubmitBooking}>
//                             <label>
//                                 Select Date
//                                 <input
//                                     type="date"
//                                     value={selectedDate}
//                                     onChange={(e) => setSelectedDate(e.target.value)}
//                                 />
//                             </label>
//                             <div className="time-inputs">
//                                 <div>
//                                     <label>Arrival Time</label>
//                                     <input
//                                         type="time"
//                                         value={arrivalTime}
//                                         onChange={(e) => setArrivalTime(e.target.value)}
//                                     />
//                                 </div>
//                                 <div>
//                                     <label>Departure Time</label>
//                                     <input
//                                         type="time"
//                                         value={departureTime}
//                                         onChange={(e) => setDepartureTime(e.target.value)}
//                                     />
//                                 </div>
//                             </div>
//                             <div className="parking-slots">
//                                 <label>Select Parking Slot</label>
//                                 <div className="slots-grid">
//                                     <button type="button" className="slot-button" onClick={() => handleSlotSelect("Slot A1")}>
//                                         Slot A1
//                                     </button>
//                                     <button type="button" className="slot-button" onClick={() => handleSlotSelect("Slot A2")}>
//                                         Slot A2
//                                     </button>
//                                     <button type="button" className="slot-button" disabled>
//                                         Slot B1
//                                     </button>
//                                     <button type="button" className="slot-button" onClick={() => handleSlotSelect("Slot B2")}>
//                                         Slot B2
//                                     </button>
//                                     <button type="button" className="slot-button" onClick={() => handleSlotSelect("Slot C1")}>
//                                         Slot C1
//                                     </button>
//                                     <button type="button" className="slot-button" disabled>
//                                         Slot C2
//                                     </button>
//                                 </div>
//                             </div>
//                             <button type="submit" className="submit-button">
//                                 Book Now
//                             </button>
//                         </form>
//                         <button className="close-popup-button" onClick={() => setIsOpen(false)}>
//                             Close
//                         </button>
//                     </div>
//                 </div>
//             )}

//             <Footer />
//         </div>
//     );
// }

// export default UserHome;

import React, { useState, useEffect } from "react";
import Footer from "../Components/Footer";
import UserNavbar from "../Components/UserNavbar";
import "../CSS/UserHome.css";
import "../CSS/Booking.css";

const UserHome = () => {
    const [parkingSpots, setParkingSpots] = useState([]);
    const [filteredSpots, setFilteredSpots] = useState([]);
    const [city, setCity] = useState("");
    const [area, setArea] = useState("");
    const [isOpen, setIsOpen] = useState(false);
    const [selectedSpot, setSelectedSpot] = useState(null);

    const [parkingSlots, setParkingSlots] = useState([]);
    const [selectedDate, setSelectedDate] = useState("");
    const [arrivalTime, setArrivalTime] = useState("");
    const [departureTime, setDepartureTime] = useState("");
    const [selectedSlot, setSelectedSlot] = useState("");

    const staticImage = "https://via.placeholder.com/300x200"; // Replace with actual image URL

    // Fetch Parking Locations (Initial Load)
    useEffect(() => {
        const fetchParkingSpots = async () => {
            try {
                const response = await fetch("http://localhost:8081/ParkingLocation");
                const data = await response.json();
                if (Array.isArray(data)) {
                    setParkingSpots(data);
                    setFilteredSpots(data);
                } else {
                    console.error("Unexpected response format:", data);
                }
            } catch (error) {
                console.error("Error fetching parking spots:", error);
            }
        };
        fetchParkingSpots();
    }, []);

    // Handle city and area filtering
    const handleSearch = () => {
        let filtered = parkingSpots;
        if (city.trim()) {
            filtered = filtered.filter((spot) => spot.city?.toLowerCase().includes(city.toLowerCase()));
        }
        if (area.trim()) {
            filtered = filtered.filter((spot) => spot.area?.toLowerCase().includes(area.toLowerCase()));
        }
        setFilteredSpots(filtered);
    };

    // Toggle Booking Popup & Load Available Slots
    const togglePopup = async (spot) => {
        setSelectedSpot(spot);
        setIsOpen(!isOpen);
        if (!isOpen && selectedDate) {
            await fetchAvailableSlots(selectedDate, spot.lId);
        }
    };

    // Fetch Available Slots from Backend
    const fetchAvailableSlots = async (date, locationId) => {
        try {
            const response = await fetch(`http://localhost:8081/Availability/availabilityByDate/${locationId}?date=${date}`);
            if (response.ok) {
                const slots = await response.json();
                setParkingSlots(slots);
            } else {
                setParkingSlots([]); // No slots available
            }
        } catch (error) {
            console.error("Error fetching slots:", error);
        }
    };

    // Handle Date Selection (Refetch Slots)
    useEffect(() => {
        if (selectedDate && selectedSpot) {
            fetchAvailableSlots(selectedDate, selectedSpot.lId);
        }
    }, [selectedDate]);

    // Handle Booking Submission
    const handleSubmitBooking = async (e) => {
        e.preventDefault();
        if (!selectedSlot) {
            alert("Please select a slot before booking.");
            return;
        }

        const bookingDetails = {
            locationId: selectedSpot.lId,
            selectedDate,
            arrivalTime,
            departureTime,
            selectedSlot
        };

        try {
            const response = await fetch("http://localhost:8081/Availability/bookSlot", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(bookingDetails),
            });

            if (response.ok) {
                alert("Slot booked successfully!");
                setIsOpen(false);
            } else {
                alert("Booking failed. Please try again.");
            }
        } catch (error) {
            console.error("Error booking slot:", error);
        }
    };

    return (
        <div>
            <UserNavbar />

            {/* Search Banner */}
            <section className="search-banner">
                <h1>Find Your Perfect Parking Spot</h1>
                <p>Quick, easy, and convenient parking solutions for your vehicle</p>
                <div className="search-bar">
                    <input type="text" placeholder="Enter City" value={city} onChange={(e) => setCity(e.target.value)} />
                    <input type="text" placeholder="Enter Area (Optional)" value={area} onChange={(e) => setArea(e.target.value)} />
                    <button onClick={handleSearch}>Search</button>
                </div>
            </section>

            {/* Available Parking Spots */}
            <section className="available-parking">
                <h2>Available Parking Spots</h2>
                <div className="parking-spots">
                    {filteredSpots.length > 0 ? (
                        filteredSpots.map((spot, index) => (
                            <div key={index} className="parking-card">
                                <img src={staticImage} alt="Parking Spot" className="parking-image" />
                                <div className="parking-info">
                                    <h5>{spot.location || "Unknown Location"}</h5>
                                    <p>City: {spot.city || "N/A"}</p>
                                    <p>Area: {spot.area || "N/A"}</p>
                                    <button className="open-popup-button" onClick={() => togglePopup(spot)}>Book Now</button>
                                </div>
                            </div>
                        ))
                    ) : (
                        <p>No parking spots found for the selected city and area.</p>
                    )}
                </div>
            </section>

            {/* Booking Popup */}
            {isOpen && selectedSpot && (
                <div className="popup-overlay">
                    <div className="popup-content">
                        <h2>Book Parking Slot</h2>
                        <form onSubmit={handleSubmitBooking}>
                            <label>
                                Select Date
                                <input type="date" value={selectedDate} onChange={(e) => setSelectedDate(e.target.value)} />
                            </label>
                            <div className="time-inputs">
                                <label>Arrival Time</label>
                                <input type="time" value={arrivalTime} onChange={(e) => setArrivalTime(e.target.value)} />
                                <label>Departure Time</label>
                                <input type="time" value={departureTime} onChange={(e) => setDepartureTime(e.target.value)} />
                            </div>
                            <div className="parking-slots">
                                <label>Select Parking Slot</label>
                                <div className="slots-grid">
                                    {parkingSlots.length > 0 ? (
                                        parkingSlots.map((slot) => (
                                            <button
                                                key={slot.slotNumber}
                                                type="button"
                                                className={`slot-button ${selectedSlot === slot.slotNumber ? "selected" : ""}`}
                                                onClick={() => setSelectedSlot(slot.slotNumber)}
                                            >
                                                {slot.slotNumber}
                                            </button>
                                        ))
                                    ) : (
                                        <p>No slots available</p>
                                    )}
                                </div>
                            </div>
                            <button type="submit" className="submit-button" disabled={!selectedSlot}>Book Now</button>
                        </form>
                        <button className="close-popup-button" onClick={() => setIsOpen(false)}>Close</button>
                    </div>
                </div>
            )}

            <Footer />
        </div>
    );
};

export default UserHome;


