import Navbar from "../Components/Navbar";
import "../CSS/AboutUs.css"

function AboutUs() {
    return ( 
      <div className="about-us">
        <Navbar/>
      {/* Hero Section */}
      <section className="hero-section">
        <div className="container text-center">
          <h1 className="hero-title">About Us</h1>
          <p className="hero-subtitle">
            Your trusted partner for finding the perfect parking spot effortlessly.
          </p>
        </div>
      </section>

      {/* About Company Section */}
      <section className="company-section">
        <div className="container">
          <div className="row align-items-center">
            <div className="col-md-6">
              <img
                src="https://media.licdn.com/dms/image/v2/D5612AQEkFgBD_LiJ1Q/article-cover_image-shrink_720_1280/article-cover_image-shrink_720_1280/0/1734297980521?e=2147483647&v=beta&t=bAuCVRtWrRAwG1SVZfDJ2LnvFrZEQuYK2BLuJd-EIyQ"
                alt="About Us"
                className="img-fluid rounded"
              />
            </div>
            <div className="col-md-4">
              <h2 className="section-title">Our Story</h2>
              <p className="section-text">
                QuickPark is an innovative platform dedicated to making parking
                stress-free. With our state-of-the-art technology, we connect
                drivers with available parking spots, saving time and ensuring
                convenience.
              </p>
              <p className="section-text">
              Our origin story takes place in a grundy, concrete car park. Like everyone, 
              we were experiencing exorbitant parking prices, the gut-wrench of a massive fine or tow, 
              the broken parking machines, and the frustrating daily search for a parking spot.

              But when we looked around, we also saw available parking space everywhere - 
              empty driveways, underused spaces outside supermarkets, churches, hotels, and in business parks.

              Why not make them available to the public? The businesses would earn some extra income,
               fewer new car parks would need to be built, and more affordable, effortless,
                transparent parking would be available to everyone.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Mission Section */}
      <section className="mission-section bg-light">
        <div className="container text-center">
          <h2 className="section-title">Our Mission</h2>
          <p className="section-text">
          Parkable was founded with a playful spirit and an ambitious goal: to make parking easy
           and stress-free, while ensuring that communities, workplaces, and commercial properties
            make the best use of their space.
          </p>
        </div>
      </section>

      {/* Team Section */}
      <section className="team-section">
        <div className="container">
          <h2 className="section-title text-center">Meet Our Team</h2>
          <div className="row">
            <div className="col-md-3 text-center">
              <img
                src="https://marketplace.canva.com/EAFuJ5pCLLM/1/0/1600w/canva-black-and-gold-simple-business-man-linkedin-profile-picture-BM_NPo97JwE.jpg"
                alt="Team Member"
                className="rounded-circle team-img"
              />
              <h4 className="team-name">John Doe</h4>
              <p className="team-role">CEO & Founder</p>
            </div>
            <div className="col-md-3 text-center">
              <img
                src="https://media.gettyimages.com/id/1317804578/photo/one-businesswoman-headshot-smiling-at-the-camera.jpg?s=612x612&w=gi&k=20&c=tFkDOWmEyqXQmUHNxkuR5TsmRVLi5VZXYm3mVsjee0E="
                alt="Team Member"
                className="rounded-circle team-img"
              />
              <h4 className="team-name">Jane Smith</h4>
              <p className="team-role">CTO</p>
            </div>
            <div className="col-md-3 text-center">
              <img
                src="https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg"
                alt="Team Member"
                className="rounded-circle team-img"
              />
              <h4 className="team-name">Emily Johnson</h4>
              <p className="team-role">Operations Manager</p>
            </div>
          </div>
        </div>
      </section>

      {/* Call to Action Section */}
      <section className="cta-section bg-primary text-white">
        <div className="container text-center">
          <h2 className="cta-title">Join Us Today!</h2>
          <p className="cta-text">
            Experience hassle-free parking and join the QuickPark community.
          </p>
          <button className="btn btn-light btn-lg">Get Started</button>
        </div>
      </section>
    </div> 
      );
}

export default AboutUs;