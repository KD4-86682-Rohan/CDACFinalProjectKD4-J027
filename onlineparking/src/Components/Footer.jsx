import "../CSS/Footer.css"

function Footer() {
    return ( 
        <footer>
  <div class="footer-container">
    <div class="footer-column logo-name">
      <img src={require("../Images/CompanyLogo.jpeg")} alt="Company Logo" />
      <p>QuickPark</p>
    </div>
    <div class="footer-column social-links">
      <h3>Follow Us</h3>
      <ul>
        <li><a href="#">Facebook</a></li>
        <li><a href="#">Twitter</a></li>
        <li><a href="#">Instagram</a></li>
        <li><a href="#">LinkedIn</a></li>
      </ul>
    </div>
    <div class="footer-column nav-links">
      <h3>Quick Links</h3>
      <ul>
        <li><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Services</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
    </div>
    <div class="footer-column locations">
      <h3>Our Locations</h3>
      <p>Pune, India</p>
      <p>Mumbai, India</p>
      <p>Delhi, India</p>
      <p>Bangalore, India</p>
    </div>
  </div>
</footer>

     );
}

export default Footer;