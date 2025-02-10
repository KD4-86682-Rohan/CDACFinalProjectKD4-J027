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
import ParkingSpaces from "./Screens/ParkingSpaces";
import VendorAccount from "./Screens/VendorAccount";

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
        {/* <Route path='' element={<Ven/>}/> */}
{/* ======= */}
{/* <<<<<<< Updated upstream */}
{/* ======= */}
        <Route path="/vendor-parking" element={<ParkingSpaces />} />
        <Route path="/vendor-account" element={<VendorAccount />} />

        {/* <Route path='' element={<Ven/>}/> */}


{/* >>>>>>> Stashed changes */}
{/* >>>>>>> caab4336dab92430f4672398c9d31ff8c3c15003 */} */

      </Routes>
      <ToastContainer />
    </div>
  );
}

export default App;
