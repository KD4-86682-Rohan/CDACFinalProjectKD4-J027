import React, { useState, useEffect } from "react";
import "../CSS/Home.css";
import Navbar from "../Components/Navbar";
import Footer from "../Components/Footer";
import { Link } from "react-router-dom";
import ParkingImage1 from "../Images/ParkingImage1.jpeg";
import ParkingImage2 from "../Images/ParkingImage2.jpeg";
import ParkingImage3 from "../Images/ParkingImage3.jpeg";

const Home = () => {
  const images = [
    ParkingImage1,
    ParkingImage2,
    ParkingImage3,
    // "https://img.freepik.com/free-photo/top-view-electric-cars-parking-lot_23-2148972403.jpg?w=1060",
    // "https://img.freepik.com/free-photo/empty-parking-lot-parking-lane-outdoor-public-park_1127-3375.jpg?w=996",
    // "https://img.freepik.com/free-photo/city-square_1359-706.jpg?w=996",
  ];

  const [currentImageIndex, setCurrentImageIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 4000); 

    return () => clearInterval(interval);
  }, []);

  return (
    <div className="homepage">
      <Navbar />

      {/* Background Image Slider */}
      <header className="header" style={{ backgroundImage: `url(${images[currentImageIndex]})` }}>
        <div className="overlay">
          {/* <h1 >Find Your Perfect Parking Spot</h1> */}
          <h1 style={{ color: "#ffcc00", fontSize: "36px", fontWeight: "bold" }}>
  Find Your Perfect Parking Spot
</h1>

          <p>Discover convenient parking spaces near you with our smart solutions.</p>
          <button className="find-parking-button">
            <Link className="nav-link" to="/userHome">Find Parking Now</Link>
          </button>
        </div>
      </header>

      {/* Features Section */}
      <div className="features-section">
        <section className="why-choose-us">
          <h2>Why Choose Us</h2>
          <div className="features">
            {[
              { title: "Easy Booking", desc: "Reserve your spot in advance in just a few clicks.", icon: "🅿" },
              { title: "24/7 Access", desc: "Access parking at any time, day or night.", icon: "⏰" },
              { title: "Secure Parking", desc: "We prioritize your vehicle’s security.", icon: "🔒" },
            ].map((feature, index) => (
              <div key={index} className="feature-card">
                <div className="feature-icon">{feature.icon}</div>
                <h5>{feature.title}</h5>
                <p>{feature.desc}</p>
              </div>
            ))}
          </div>
        </section>
      </div>

      {/* Testimonials Section */}
      <div className="testimonials-section">
        <h2 >What Our Users Say</h2>
        <div className="testimonials">
          {[
            { name: "John Doe", feedback: "Found parking spots easily! Great experience." },
            { name: "Jane Smith", feedback: "Managing parking has never been easier!" },
            { name: "Mike Johnson", feedback: "Reliable and convenient parking solutions." },
          ].map((testimonial, index) => (
            <div key={index} className="testimonial">
              <p>
                <strong>{testimonial.name}</strong> <br />
                {testimonial.feedback}
              </p>
            </div>
          ))}
        </div>
      </div>

      <Footer />
    </div>
  );
};

export default Home;
