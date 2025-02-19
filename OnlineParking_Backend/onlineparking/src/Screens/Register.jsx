import Footer from "../Components/Footer";
import Navbar from "../Components/Navbar";
import { useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useEffect } from "react";
import admin from "../services/admin";
import "../CSS/Register.css";

const Register = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [phone, setPhone] = useState("");
  const [gender, setGender] = useState("");
  const [dob, setDob] = useState("");
  const [role, setRole] = useState("");
  const [licenseNumber, setLicenseNumber] = useState("");
  const navigate = useNavigate();
  const { id } = useParams();
 
  const RegisterUser = async (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      alert("Passwords do not match!");
      return;
    }
    const user = {
      firstName,
      lastName,
      email,
      password,
      phone,
      gender,
      dob,
      role,
      licenseNumber,  
   };
   
    try {
      const response = await admin.register(user);
      console.log("User registered successfully", response.data);
      navigate("/Login");
    } catch (error) {
      alert("Error during registration. Please try again.");
      console.error("Registration error: ", error.response);
    }
  };
  

  return (
    <div>
      <Navbar />
      <div className="registration-container">
        <div className="registration-content">
          <h2>Create an Account</h2>
          <p>
            Fill in the details below to register for the online parking system.
          </p>

          <form>
            {/* First Name */}
            <div className="input-group">
              <label htmlFor="firstName">First Name</label>
              <input
                onChange={(e) => setFirstName(e.target.value)}
                type="text"
                // className="form-control"
                id="firstName"
                name="firstName"
                placeholder="Enter your first name"
                required
              />
             
            </div>

            {/* Last Name */}
            <div className="input-group">
              <label htmlFor="lastName">Last Name</label>
              <input
                onChange={(e) => setLastName(e.target.value)}
                type="text"
                // className="form-control"
                id="lastName"
                name="lastName"
                placeholder="Enter your last name"
                required
              />
              
            </div>

            {/* Email */}
            <div className="input-group">
              <label htmlFor="email">Email</label>
              <input
                onChange={(e) => setEmail(e.target.value)}
                type="email"
                // className="form-control"
                id="email"
                name="email"
                placeholder="Enter your email"
                required
              />
             
            </div>

            {/* Phone Number */}
            <div className="input-group">
              <label htmlFor="phone">Phone Number</label>
              <input
                onChange={(e) => setPhone(e.target.value)}
                type="tel"
                // className="form-control"
                id="phone"
                name="phone"
                placeholder="Enter your phone number"
                required
              />
              
            </div>

            {/* Gender */}
            <div className="input-group">
              <label htmlFor="gender">Gender</label>
              
              <select
                id="gender"
                name="gender"
                onChange={(e) => setGender(e.target.value)}
                required
              >
                <option value="">Select Gender</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
              </select>
            </div>

            {/* Date of Birth */}
            <div className="input-group">
              <label htmlFor="dob">Date of Birth</label>
              <input
                onChange={(e) => setDob(e.target.value)}
                type="text"
                // className="form-control"
                id="dob"
                name="don"
                required
              />
              {/* <input type="date" id="dob" name="dob" required /> */}
            </div>

            {/* License Number */}
            <div className="input-group">
              <label htmlFor="licenseNumber">License Number</label>
              <input
                onChange={(e) => setLicenseNumber(e.target.value)}
                type="text"
                // className="form-control"
                id="licenseNumber"
                name="licenseNumber"
                placeholder="Enter your phone number"
                required
              />
              
            </div>

            {/* Role */}
            <div className="input-group">
              <label htmlFor="role">Role</label>
              <select
                onChange={(e) => setRole(e.target.value)}
                id="role"
                name="role"
                required
              >
                <option value="">Select Role</option>
                <option value="Admin">Admin</option>
                <option value="Vendor">Parking Vendor</option>
                <option value="User">User</option>
              </select>
            </div>

            {/* Password */}
            <div className="input-group">
              <label htmlFor="password">Password</label>
              <input
                onChange={(e) => setPassword(e.target.value)}
                type="password"
                // className="form-control"
                id="password"
                name="password"
                placeholder="Enter your password"
                required
              />
             
            </div>

            {/* Confirm Password */}
            <div className="input-group">
              <label htmlFor="confirmPassword">Confirm Password</label>
              <input
                onChange={(e) => setConfirmPassword(e.target.value)}
                type="password"
                id="confirmPassword"
                name="confirmPassword"
                placeholder="Confirm your password"
                required
              />
            </div>

            {/* Submit Button */}
            <button
              onClick={(e) => RegisterUser(e)}
              className="btn btn-primary"
            >
              Register
            </button>
          </form>

          {/* Link to Login for existing users */}
          <div className="existing-user">
            <p>
              Already have an account? <Link to="/login">Login here</Link>
            </p>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Register;
