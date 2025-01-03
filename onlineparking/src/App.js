import { Route, Routes } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import Login from './Screens/Login';
import AboutUs from './Screens/AboutUs';
import Register from './Screens/Register';
import UserHome from './Screens/UserHome';
import ContactUs from './Screens/ContactUs';
import Home from './Screens/Home';
import Booking from './Screens/Booking';
import VendorDashboard from './Screens/VendorHome';
import ManageSpaces from './Screens/ManageSpaces';
import ParkingList from './Screens/AvailableParking';
import MyBookings from './Screens/MyBokking'


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

        <Route path='/booking' element={<Booking/>} />
        <Route path='/vendorhome' element={<VendorDashboard/>} />
        <Route path='/managespaces' element={<ManageSpaces/>} />

        <Route path='/availableparking' element={<ParkingList />} />
        <Route path='/mybookings' element={<MyBookings />} />
      </Routes>
      <ToastContainer />
    </div>
  );
}

export default App;
