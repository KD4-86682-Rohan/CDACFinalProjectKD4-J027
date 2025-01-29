// import { Link } from "react-router-dom";
// import Navbar from "../Components/Navbar";
// import Footer from './../Components/Footer';

// function Login() {
//     return ( 
//         <div>
//           <Navbar />
//         {/* <h2 classNameName='header'>Login</h2> */}
//         <div className='row'>
//         <div className='col'></div>
//         <div className='col'>
//           <div className='mb-3'>
//             <label htmlFor=''>Email</label>
//             <input

//               type='email'
//               className='form-control'
//             />
//           </div>
//           <div className='mb-3'>
//             <label htmlFor=''>Password</label>
//             <input

//               type='password'
//               className='form-control'
//             />
//           </div>
//           <div className='mb-3'>
//             <div>
//               Don't have an account? <Link to='/register'>Register here</Link>
//             </div>
//             <button className='btn btn-success mt-3'>
//               <Link className="nav-link" to='/userHome'>Login</Link>
//             </button>
//           </div>
//         </div>
//         <div className='col'></div>
//       </div>
//       <Footer/>
//       </div>
//      );
// }

// export default Login;

import { Link } from "react-router-dom";
import Navbar from "../Components/Navbar";
import Footer from './../Components/Footer';
import "../CSS/Login.css";


function Login() {
    return ( 
      <div>
        <Navbar />
        <div className="login-container">
      <div className="login-content">
        {/* Left Side: Illustration */}
        <div className="login-illustration">
          <img
          src="https://img.freepik.com/premium-vector/reserve-parking-space-curbside-pickup-abstract-concept-vector-illustration_107173-20370.jpg?w=740"
            // src="https://www.shutterstock.com/shutterstock/photos/1465305536/display_1500/stock-vector-vector-illustration-of-autonomous-wireless-parking-remote-connected-car-sharing-service-controlled-1465305536.jpg" // Replace with the actual image path
            alt="Login Illustration"
          />
        </div>

        {/* Right Side: Login Form */}
        <div className="login-form">
          <h2>Welcome Back :)</h2>
          <p>
            To keep connected with us, please login with your personal
            information by email address and password.
          </p>
          <form>
            {/* Email Input */}
            <div className="input-group">
              <label htmlFor="email">Email Address</label>
              <input
                type="email"
                id="email"
                placeholder="Enter your email"
                required
              />
            </div>

            {/* Password Input */}
            <div className="input-group">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                id="password"
                placeholder="Enter your password"
                required
              />
            </div>

            {/* Options */}
            <div className="options">
              <label>
                <input type="checkbox" /> Remember Me
              </label>
              <a href="/forgot-password">Forgot Password?</a>
            </div>

            {/* Buttons */}
            <div className="button-group">
              <button type="submit" className="btn login-btn">
                Login Now
              </button>
              <Link to="/register">
                    <button type="button" className="btn create-account-btn">
                      Create Account
                    </button>
                  </Link>
            </div>
          </form>
        </div>
      </div>
    </div>
        <Footer/>
      </div>
     );
}

export default Login;

