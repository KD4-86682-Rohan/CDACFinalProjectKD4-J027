import { Route, Routes } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import Login from './Screens/Login';
import AboutUs from './Screens/AboutUs';
import Register from './Screens/Register';
import UserHome from './Screens/UserHome';
import ContactUs from './Screens/ContactUs';
import Home from './Screens/Home';
import VendorDashboard from './Screens/VendorHome';
import ManageSpaces from './Screens/ManageSpaces';
import ParkingList from './Screens/AvailableParking';
import MyBookings from './Screens/MyBokking'
import Profile from './Screens/Profile';
import ForgotPassword from "./Screens/ForgotPassword";
import ResetPassword from "./Screens/ResetPassword";

function App() {
  return (
    <div className="container-fluid">
      
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/about-us" element={<AboutUs />} />
        <Route path="/register" element={<Register />} />
        <Route path="/userHome" element={<UserHome/>}/>
        <Route path="/contact-us" element={<ContactUs/>}/>
        <Route path="/about-us" element={<AboutUs/>}/>
        <Route path='/' element={<Home/>} />
        <Route path='/vendorhome' element={<VendorDashboard/>} />
        <Route path='/managespaces' element={<ManageSpaces/>} />
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path='/availableparking' element={<ParkingList />} />
        <Route path='/mybookings' element={<MyBookings />} />
        <Route path='/profile' element={<Profile/>}/>
      </Routes>
      <ToastContainer />
    </div>
  );
}

export default App;
