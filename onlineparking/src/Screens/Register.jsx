//import axios from 'axios';
import { useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { useEffect } from 'react';
import admin  from '../services/admin';
import Footer from "../Components/Footer";
import Navbar from "../Components/Navbar";
// import { toast } from "react-toastify"

const AddUser = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('')
  const [phone, setPhone] = useState('')
  const [gender, setGender]= useState('')
  const [dob, setDob] = useState('')
  const [role, setRole] = useState('')
  const [LicenseNunmber, setLicenseNumber] = useState('')

  const navigate = useNavigate();
  const { id } = useParams();

  const RegisterUser = (e) => {
    // if(firstName.length==0)
    // {
    //     toast.warn('Please enter first name');
    // }else if(lastName.length==0)
    // {
    //     toast.warn('Please enter last name');
    // }
    // debugger
    e.preventDefault();

    const user = {
      firstName,
      lastName,
      email,
      password,
      phone,
      gender,
      dob,
      role,
      LicenseNunmber,
    };
    // console.log(employee)
     //create
      admin
        .register(user)
        .then((response) => {
          console.log('employee added successfully', response.data);
          navigate('/');
        })
        .catch((error) => {
          console.log('something went wroing' + error.response);
        });
  };
   
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
            onChange={(e) => setFirstName(e.target.value)}
            type='text'
            className='form-control'
          />
        </div>
        {/* Last Name */}
        <div className="mb-3">
          <label htmlFor="lastName">Last Name</label>
          <input
            onChange={(e) => setLastName(e.target.value)}
            type='text'
            className='form-control'
          />
        </div>
        {/* Email */}
        <div className="mb-3">
          <label htmlFor="email">Email</label>
          <input
            onChange={(e) => setEmail(e.target.value)}
            type='email'
            className='form-control'
          />
        </div>
        {/* Phone Number */}
        <div className="mb-3">
          <label htmlFor="phoneNumber">Phone Number</label>
          <input
            onChange={(e) => setPhone(e.target.value)}
            type='tel'
            className='form-control'
          />
        </div>
        {/* Gender */}
        <div className="mb-3">
          <label>Gender</label>
          <div>
            <input onChange={(e) => setGender(e.target.value)} type="radio" id="male" name="gender" value="Male" />
            <label htmlFor="male" className="me-2">Male</label>
            <input onChange={(e) => setGender(e.target.value)} type="radio" id="female" name="gender" value="Female" />
            <label htmlFor="female" className="me-2">Female</label>
            <input onChange={(e) => setGender(e.target.value)} type="radio" id="other" name="gender" value="Other" />
            <label htmlFor="other">Other</label>
          </div>
        </div>
        {/* Date of Birth */}
        <div className="mb-3">
          <label htmlFor="dob">Date of Birth</label>
          <input
            onChange={(e) => setDob(e.target.value)}
            type='dob'
            className='form-control'
          />
        </div>
        {/* Driving License */}
        <div className="mb-3">
          <label htmlFor="drivingLicense">Driving License</label>
          <input
            onChange={(e) => setLicenseNumber(e.target.value)}
            type='text'
            className='form-control'
          />
        </div>
        {/* Select Role */}
        <div className="mb-3">
          <label htmlFor="role">Select Role</label>
          <select onChange={(e) => setRole(e.target.value)} id="role" className="form-control">
            <option  value="Admin">Admin</option>
            <option  value="Vendor">Parking Vendor</option>
            <option  value="User">User</option>
          </select>
        </div>
        {/* Password */}
        <div className="mb-3">
          <label htmlFor="password">Password</label>
          <input
            onChange={(e) => setPassword(e.target.value)}
            type='text'
            className='form-control'
          />
        </div>
        {/* Confirm Password */}
        <div className="mb-3">
          <label htmlFor="confirmPassword">Confirm Password</label>
          <input
            onChange={(e) => setConfirmPassword(e.target.value)}
            type='text'
            className='form-control'
          />
        </div>
        {/* Signup Button */}
        <div className="mb-3">
          <div>
            Already have an account? <Link to="/login">Signin here</Link>
          </div>
          <button onClick={(e) => RegisterUser(e)} className='btn btn-primary'>
            Register
          </button>
        </div>
      </div>
      <div className="col"></div>
    </div>
    <Footer />
  </div>
);
};

export default AddUser;
