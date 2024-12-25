import { Link } from "react-router-dom";
import Navbar from "../Components/Navbar";
import Footer from './../Components/Footer';

function Login() {
    return ( 
        <div>
          <Navbar />
        {/* <h2 classNameName='header'>Login</h2> */}
        <div className='row'>
        <div className='col'></div>
        <div className='col'>
          <div className='mb-3'>
            <label htmlFor=''>Email</label>
            <input

              type='email'
              className='form-control'
            />
          </div>
          <div className='mb-3'>
            <label htmlFor=''>Password</label>
            <input

              type='password'
              className='form-control'
            />
          </div>
          <div className='mb-3'>
            <div>
              Don't have an account? <Link to='/register'>Register here</Link>
            </div>
            <button className='btn btn-success mt-3'>
              Login
            </button>
          </div>
        </div>
        <div className='col'></div>
      </div>
      <Footer/>
      </div>
     );
}

export default Login;