import Footer from "../Components/Footer";
import Navbar from "../Components/Navbar";
import { Link } from "react-router-dom";

function Register() {
  return (
    <div>
      <Navbar />
      <h2 className="header">Register</h2>
      <div className="row">
        <div className="col"></div>
        <div className="col">
          {/* First Name */}
          <div className="mb-3">
            <label htmlFor="firstName">First Name</label>
            <input
              type="text"
              id="firstName"
              className="form-control"
            />
          </div>
          {/* Last Name */}
          <div className="mb-3">
            <label htmlFor="lastName">Last Name</label>
            <input
              type="text"
              id="lastName"
              className="form-control"
            />
          </div>
          {/* Email */}
          <div className="mb-3">
            <label htmlFor="email">Email</label>
            <input
              type="email"
              id="email"
              className="form-control"
            />
          </div>
          {/* Phone Number */}
          <div className="mb-3">
            <label htmlFor="phoneNumber">Phone Number</label>
            <input
              type="tel"
              id="phoneNumber"
              className="form-control"
            />
          </div>
          {/* Gender */}
          <div className="mb-3">
            <label>Gender</label>
            <div>
              <input type="radio" id="male" name="gender" value="male" />
              <label htmlFor="male" className="me-2">Male</label>
              <input type="radio" id="female" name="gender" value="female" />
              <label htmlFor="female" className="me-2">Female</label>
              <input type="radio" id="other" name="gender" value="other" />
              <label htmlFor="other">Other</label>
            </div>
          </div>
          {/* Date of Birth */}
          <div className="mb-3">
            <label htmlFor="dob">Date of Birth</label>
            <input
              type="date"
              id="dob"
              className="form-control"
            />
          </div>
          {/* Driving License */}
          <div className="mb-3">
            <label htmlFor="drivingLicense">Driving License</label>
            <input
              type="text"
              id="drivingLicense"
              className="form-control"
            />
          </div>
          {/* Select Role */}
          <div className="mb-3">
            <label htmlFor="role">Select Role</label>
            <select id="role" className="form-control">
              <option value="admin">Admin</option>
              <option value="parkingVendor">Parking Vendor</option>
              <option value="user">User</option>
            </select>
          </div>
          {/* Password */}
          <div className="mb-3">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              className="form-control"
            />
          </div>
          {/* Confirm Password */}
          <div className="mb-3">
            <label htmlFor="confirmPassword">Confirm Password</label>
            <input
              type="password"
              id="confirmPassword"
              className="form-control"
            />
          </div>
          {/* Signup Button */}
          <div className="mb-3">
            <div>
              Already have an account? <Link to="/login">Signin here</Link>
            </div>
            <button className="mt-3 btn btn-success">
              <Link className="nav-link" to='/vendorhome'>Signup</Link> 
            </button>
          </div>
        </div>
        <div className="col"></div>
      </div>
      <Footer />
    </div>
  );
}

export default Register;
