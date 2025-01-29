// import { useState, useEffect } from 'react';
// import { Link,useNavigate } from 'react-router-dom';
// import admin from '../services/admin';
// import Navbar from "../Components/Navbar";
// import Footer from './../Components/Footer';

// const SigninUser = () => {
//   const [email, setEmail] = useState('');
//   const [password, setPassword] = useState('');
//   const [user, setUser] = useState('');
//   const navigate = useNavigate()

//   const onLogin = (e) => {
//     debugger
//     // e.preventDefault();

//     const u = {
//       email,
//       password,
//     };
//      //create
//       admin
//         .login(u)
//         .then((response) => {
//           console.log('employee added successfully', response.data);
//           setUser(response.data)
//           if(user=="Vender")
//           {
//             navigate('/VenderHome');
//           }else if(user.role=="Admin")
//           {
//             navigate('/AdminHome');
//           }
//           else
//           {
//             navigate('/UserHome');
//           }
          
//         })
//         .catch((error) => {
//           console.log('something went wroing' + error.response);
//         });
//   };

//   useEffect(() => {
//     onLogin();
//   }, []);
 
//   return ( 
//             <div>
//               <Navbar />
//             {/* <h2 classNameName='header'>Login</h2> */}
//             <div className='row'>
//             <div className='col'></div>
//             <div className='col'>
//               <div className='mb-3'>
//                 <label htmlFor=''>Email</label>
//                 <input
//                   onChange={(e) => setEmail(e.target.value)}
//                   type='email'
//                   className='form-control'
//                 />
//               </div>
//               <div className='mb-3'>
//                 <label htmlFor=''>Password</label>
//                 <input
//                   onChange={(e) => setPassword(e.target.value)}
//                   type='password'
//                   className='form-control'
//                 />
//               </div>
//               <div className='mb-3'>
//                 <div>
//                   Don't have an account? <Link to='/register'>Register here</Link>
//                 </div>
//                 <button onClick={onLogin} className='btn btn-success mt-3'>
//                   Login
//                 </button>
//               </div>
//             </div>
//             <div className='col'></div>
//           </div>
//           <Footer/>
//           </div>
//          );
//     }
    
//     export default SigninUser;

import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import admin from '../services/admin';
import Navbar from "../Components/Navbar";
import Footer from './../Components/Footer';

const SigninUser = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [user, setUser] = useState({}); // Use an empty object instead of a string
  const navigate = useNavigate();

  const onLogin = async (e) => {
    e.preventDefault(); // Prevent page refresh on form submit

    const u = { email, password };

    try {
      const response = await admin.login(u);
      console.log('User logged in successfully', response.data);
      setUser(response.data);

      // Ensure `user` is updated before using it
      if (response.data.role === "Vendor") {
        navigate('/VendorHome');
      } else if (response.data.role === "Admin") {
        navigate('/AdminHome');
      } else {
        navigate('/UserHome');
      }
    } catch (error) {
      console.log('Something went wrong:', error.response);
    }
  };

  return ( 
    <div>
      <Navbar />
      <div className='row'>
        <div className='col'></div>
        <div className='col'>
          <div className='mb-3'>
            <label>Email</label>
            <input
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              type='email'
              className='form-control'
            />
          </div>
          <div className='mb-3'>
            <label>Password</label>
            <input
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              type='password'
              className='form-control'
            />
          </div>
          <div className='mb-3'>
            <div>
              Don't have an account? <Link to='/register'>Register here</Link>
            </div>
            <button onClick={onLogin} className='btn btn-success mt-3'>
              Login
            </button>
          </div>
        </div>
        <div className='col'></div>
      </div>
      <Footer />
    </div>
  );
};

export default SigninUser;
