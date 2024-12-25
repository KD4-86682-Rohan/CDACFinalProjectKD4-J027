import { Route, Routes } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import Home from './Screens/Home';
import Login from './Screens/Login';
import AboutUs from './Screens/AboutUs';
import Register from './Screens/Register';
import UserHome from './Screens/UserHome';


function App() {
  return (
    <div className="container-fluid">
      
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/about-us" element={<AboutUs />} />
        <Route path="/register" element={<Register />} />
        <Route path="/userHome" element={<UserHome/>}/>
      </Routes>
      <ToastContainer />
    </div>
  );
}

export default App;
