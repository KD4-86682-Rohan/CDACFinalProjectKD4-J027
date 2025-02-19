import React from "react";
import "../CSS/CustomerFeedback.css";
import Footer from "../Components/Footer";

const CustomerFeedback = () => {
  const feedbacks = [
    {
      name: "John Doe",
      rating: 5,
      comment: "Great parking space, very convenient location!",
      date: "2023-08-01",
    },
    {
      name: "Jane Smith",
      rating: 4,
      comment: "Good experience overall, but lighting could be better.",
      date: "2023-07-31",
    },
    {
      name: "Mike Johnson",
      rating: 5,
      comment: "Very secure parking space, will use again!",
      date: "2023-07-30",
    },
  ];

  const renderStars = (rating) => {
    return "⭐".repeat(rating) + "☆".repeat(5 - rating);
  };

  return (
    <div className="customer-feedback">
      <h2 className="dashboard-title">Dashboard Overview</h2>

      <div className="feedback-section">
        <h3>Recent Feedback</h3>
        <div className="feedback-stats">
          <div className="feedback-stat">
            <h4>Total Feedbacks</h4>
            <p>{feedbacks.length}</p>
          </div>
          <div className="feedback-stat">
            <h4>Average Rating</h4>
            <p>
              {(
                feedbacks.reduce((acc, feedback) => acc + feedback.rating, 0) /
                feedbacks.length
              ).toFixed(1)}
              /5
            </p>
          </div>
        </div>
        {feedbacks.map((feedback, index) => (
          <div className="feedback-card" key={index}>
            <p>
              <strong>{feedback.name}</strong> <span className="stars">{renderStars(feedback.rating)}</span>
            </p>
            <p>{feedback.comment}</p>
            <p className="feedback-date">{feedback.date}</p>
            <a href="#" className="reply-link">Reply</a>
          </div>
        ))}
        <a href="#" className="view-all">View All Feedback</a>
      </div>

      <Footer />
    </div>
  );
};

export default CustomerFeedback;
