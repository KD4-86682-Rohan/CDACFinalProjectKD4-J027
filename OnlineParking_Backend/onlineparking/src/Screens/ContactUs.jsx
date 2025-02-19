import React from "react";
import UserNavbar from "../Components/UserNavbar";
import Footer from "../Components/Footer";
import "../CSS/ContactUs.css"

function ContactUs() {
  return (
    <div>
      <UserNavbar />
      <div className="contact-us-container">
        {/* Hero Section */}
        <div className="contact-us-hero">
          <h1>Contact Us</h1>
          <p>
            Have questions or need assistance? We’re here to help! Reach out to us, and we’ll respond promptly.
          </p>
        </div>

        {/* Content Section */}
        <div className="contact-us-content">
          {/* Contact Information */}
          <div className="contact-info">
            <h2>Contact Information</h2>
            <p><strong>Email:</strong> support@quickpark.com</p>
            <p><strong>Phone:</strong> +1 (123) 456-7890</p>
            <p><strong>Address:</strong> 123 Parking Avenue, City, Country</p>
            <p><strong>Working Hours:</strong> 9:00 AM - 6:00 PM (Mon - Sat)</p>
          </div>

          {/* Contact Form */}
          <div className="contact-form">
            <h2>Send Us a Message</h2>
            <form>
              <div className="form-group">
                <label htmlFor="name">Full Name</label>
                <input type="text" id="name" placeholder="Enter your name" />
              </div>
              <div className="form-group">
                <label htmlFor="email">Email</label>
                <input type="email" id="email" placeholder="Enter your email" />
              </div>
              <div className="form-group">
                <label htmlFor="message">Message</label>
                <textarea
                  id="message"
                  rows="4"
                  placeholder="Type your message here"
                ></textarea>
              </div>
              <button type="submit" className="submit-btn">
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default ContactUs;
