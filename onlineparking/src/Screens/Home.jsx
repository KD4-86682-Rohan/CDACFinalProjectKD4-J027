import React from "react";
import "../CSS/Home.css";
import Navbar from "../Components/Navbar";
import Footer from "../Components/Footer";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <div className="homepage">
      <Navbar />
      <header className="header">
        <h1>Find Your Perfect Parking Spot</h1>
        <p>
          Discover convenient parking spaces near you with our smart parking
          solutions.
        </p>
        <button className="find-parking-button"><Link className="nav-link" to="/userHome">Find Parking Now</Link></button>
      </header>
      <div className="features-section">
        {/* Why Choose Us */}
      <section className="why-choose-us">
        <h2>Why Choose Us</h2>
        <div className="features">
          {[
            { title: "Easy Booking", desc: "Book your parking spot in advance with just a few clicks.", icon: "🅿" },
            { title: "24/7 Access", desc: "Access your parking spot anytime, day or night.", icon: "⏰" },
            { title: "Secure Parking", desc: "Your vehicle's safety is our top priority.", icon: "🔒" },
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
      <div className="testimonials-section">
        <h2>What Our Users Say</h2>
        <div className="testimonials">
          <div className="testimonial">
            <p>
              <strong>John Doe</strong> <br />
              Found parking spots easily! Great experience.
            </p>
          </div>
          <div className="testimonial">
            <p>
              <strong>Jane Smith</strong> <br />
              Managing my parking spaces has never been easier!
            </p>
          </div>
          <div className="testimonial">
            <p>
              <strong>Mike Johnson</strong> <br />
              Reliable and convenient parking solutions.
            </p>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Home;
