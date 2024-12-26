import { Route, Routes } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import Login from './Screens/Login';
import AboutUs from './Screens/AboutUs';
import Register from './Screens/Register';
import UserHome from './Screens/UserHome';
import Home from './Screens/Home';
import ParkingList from './Screens/AvailableParking';
import MyBookings from './Screens/MyBokking';


function App() {
  return (
    <div className="container-fluid">
      
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/about-us" element={<AboutUs />} />
        <Route path="/register" element={<Register />} />
        <Route path="/userHome" element={<UserHome/>}/>
        <Route path='/' element={<Home/>} />
        <Route path='/availableparking' element={<ParkingList />} />
        <Route path='/mybookings' element={<MyBookings />} />
      </Routes>
      <ToastContainer />
    </div>
  );
}

export default App;
