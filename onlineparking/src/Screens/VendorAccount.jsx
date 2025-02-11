import React, { useState } from "react";
import VendorNavbar from "../Components/VendorNavbar";
import "../CSS/VendorAccount.css";
// import "../CSS/VendorAccount.css";

const VendorAccount = () => {
  const [formData, setFormData] = useState({
    accountHolder: "",
    bankName: "",
    accountNumber: "",
    ifscCode: "",
    branchName: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Submitted Data:", formData);
    alert("Bank details saved successfully!");
  };

  return (
    <div className="vendor-account-container">
      <VendorNavbar />
      <div className="account-settings">
        <h2>Account Settings</h2>
        <div className="bank-details-card">
          <h3>💳 Bank Account Details</h3>
          <form onSubmit={handleSubmit}>
            <label>Account Holder Name</label>
            <input type="text" name="accountHolder" placeholder="Enter account holder name" onChange={handleChange} required />

            <label>Bank Name</label>
            <input type="text" name="bankName" placeholder="Enter bank name" onChange={handleChange} required />

            <label>Account Number</label>
            <input type="text" name="accountNumber" placeholder="Enter account number" onChange={handleChange} required />

            <label>IFSC Code</label>
            <input type="text" name="ifscCode" placeholder="Enter IFSC code" onChange={handleChange} required />

            <label>Branch Name</label>
            <input type="text" name="branchName" placeholder="Enter branch name" onChange={handleChange} required />

            <div className="button-group">
              <button type="button" className="cancel-btn">Cancel</button>
              <button type="submit" className="save-btn">Save Bank Details</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default VendorAccount;
