// import React from "react";
// import "../CSS/Home.css";
// import Navbar from "../Components/Navbar";
// import Footer from "../Components/Footer";
// import { Link } from "react-router-dom";

// const Home = () => {
//   return (
//     <div className="homepage">
//       <Navbar />
//       <header className="header">
//         <h1>Find Your Perfect Parking Spot</h1>
//         <p>
//           Discover convenient parking spaces near you with our smart parking
//           solutions.
//         </p>
//         <button className="find-parking-button"><Link className="nav-link" to="/userHome">Find Parking Now</Link></button>
//       </header>
//       <div className="features-section">
//         {/* Why Choose Us */}
//       <section className="why-choose-us">
//         <h2>Why Choose Us</h2>
//         <div className="features">
//           {[
//             { title: "Easy Booking", desc: "Book your parking spot in advance with just a few clicks.", icon: "🅿" },
//             { title: "24/7 Access", desc: "Access your parking spot anytime, day or night.", icon: "⏰" },
//             { title: "Secure Parking", desc: "Your vehicle's safety is our top priority.", icon: "🔒" },
//           ].map((feature, index) => (
//             <div key={index} className="feature-card">
//               <div className="feature-icon">{feature.icon}</div>
//               <h5>{feature.title}</h5>
//               <p>{feature.desc}</p>
//             </div>
//           ))}
//         </div>
//       </section>
//       </div>
//       <div className="testimonials-section">
//         <h2>What Our Users Say</h2>
//         <div className="testimonials">
//           <div className="testimonial">
//             <p>
//               <strong>John Doe</strong> <br />
//               Found parking spots easily! Great experience.
//             </p>
//           </div>
//           <div className="testimonial">
//             <p>
//               <strong>Jane Smith</strong> <br />
//               Managing my parking spaces has never been easier!
//             </p>
//           </div>
//           <div className="testimonial">
//             <p>
//               <strong>Mike Johnson</strong> <br />
//               Reliable and convenient parking solutions.
//             </p>
//           </div>
//         </div>
//       </div>
//       <Footer />
//     </div>
//   );
// };

// export default Home;


import React, { useState, useEffect } from "react";
import "../CSS/Home.css";
import Navbar from "../Components/Navbar";
import Footer from "../Components/Footer";
import { Link } from "react-router-dom";

const Home = () => {
  const images = [
    "https://c8.alamy.com/comp/PPP6GX/parking-garage-in-downtown-milwaukee-PPP6GX.jpg",
    "https://c8.alamy.com/comp/J0E9BT/car-parking-interior-shopping-mall-J0E9BT.jpg",
    "https://c8.alamy.com/comp/2T9J443/dulles-airport-virginia-usaterminal-and-parking-lot-2T9J443.jpg",
  ];

  const [currentImageIndex, setCurrentImageIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 4000); // Change image every 4 seconds

    return () => clearInterval(interval);
  }, []);

  return (
    <div className="homepage">
      <Navbar />

      {/* Background Image Slider */}
      <header
        className="header"
        style={{ backgroundImage: `url(${images[currentImageIndex]})` }}
      >
        <div className="overlay">
          <h1>Find Your Perfect Parking Spot</h1>
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
        <h2>What Our Users Say</h2>
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
