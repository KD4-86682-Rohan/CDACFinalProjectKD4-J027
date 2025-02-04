// import { useState } from "react";
// import Footer from "../Components/Footer";
// import "../CSS/Profile.css";
// import UserNavbar from "../Components/UserNavbar";

// function Profile() {
//   const [profileImage, setProfileImage] = useState(null);

//   const handleImageUpload = (event) => {
//     const file = event.target.files[0];
//     if (file) {
//       const imageUrl = URL.createObjectURL(file);
//       setProfileImage(imageUrl);
//     }
//   };

//   return (
//     <div>
//       <UserNavbar/>
//       <div className="profile-container">
//         <div className="profile-card">
//           {/* Profile Picture Section */}
//           <div className="profile-header">
//             <div className="profile-image">
//               <img
//                 src={profileImage || "https://via.placeholder.com/150"}
//                 alt="Profile"
//               />
//               <input type="file" id="upload" accept="image/*" onChange={handleImageUpload} />
//               <label htmlFor="upload" className="upload-btn">Change Photo</label>
//             </div>
//             <h2>Ankita Zirpe</h2>
//             <p className="profile-role">User</p>
//           </div>

//           {/* Profile Information Section */}
//           <div className="profile-details">
//             <div className="detail-row">
//               <span className="detail-title">Email:</span>
//               <span className="detail-value">ankita@example.com</span>
//             </div>

//             <div className="detail-row">
//               <span className="detail-title">Phone:</span>
//               <span className="detail-value">+91 9876543210</span>
//             </div>

//             <div className="detail-row">
//               <span className="detail-title">Gender:</span>
//               <span className="detail-value">Female</span>
//             </div>

//             <div className="detail-row">
//               <span className="detail-title">Date of Birth:</span>
//               <span className="detail-value">01 Jan 2000</span>
//             </div>

//             <div className="detail-row">
//               <span className="detail-title">License Number:</span>
//               <span className="detail-value">MH12AB1234</span>
//             </div>

//             <div className="detail-row">
//               <span className="detail-title">Role:</span>
//               <span className="detail-value">User</span>
//             </div>
//           </div>

//           {/* Update Profile Button */}
//           <button className="update-profile-btn">Update Profile</button>
//         </div>
//       </div>
//       <Footer />
//     </div>
//   );
// }

// export default Profile;


import { useState } from "react";
import Footer from "../Components/Footer";
import "../CSS/Profile.css";
import UserNavbar from "../Components/UserNavbar";

function Profile() {
  const [profileImage, setProfileImage] = useState(null);

  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    if (file) {
      const imageUrl = URL.createObjectURL(file);
      setProfileImage(imageUrl);
    }
  };

  return (
    <div>
      <UserNavbar />
      <div className="profile-container">
        <div className="profile-card">
          {/* Left Section - Profile Information */}
          <div className="profile-info">
            <h2>Ankita Zirpe</h2>
            <p className="profile-role">User</p>

            <div className="profile-details">
              <div className="detail-row">
                <span className="detail-title">Email:</span>
                <span className="detail-value">ankita@example.com</span>
              </div>

              <div className="detail-row">
                <span className="detail-title">Phone:</span>
                <span className="detail-value">+91 9876543210</span>
              </div>

              <div className="detail-row">
                <span className="detail-title">Gender:</span>
                <span className="detail-value">Female</span>
              </div>

              <div className="detail-row">
                <span className="detail-title">Date of Birth:</span>
                <span className="detail-value">01 Jan 2000</span>
              </div>

              <div className="detail-row">
                <span className="detail-title">License Number:</span>
                <span className="detail-value">MH12AB1234</span>
              </div>

              <div className="detail-row">
                <span className="detail-title">Role:</span>
                <span className="detail-value">User</span>
              </div>
            </div>

            {/* Update Profile Button */}
            <button className="update-profile-btn">Update Profile</button>
          </div>

          {/* Right Section - Profile Image */}
          <div className="profile-image-container">
            <div className="profile-image">
              <img
                src={profileImage || "https://via.placeholder.com/150"}
                alt="Profile"
              />
              <input type="file" id="upload" accept="image/*" onChange={handleImageUpload} />
              <label htmlFor="upload" className="upload-btn">Change Photo</label>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Profile;
