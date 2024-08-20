import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';  // CSS 파일 import

function App() {
  const [review, setReview] = useState('');
  const [result, setResult] = useState(null);

  useEffect(() => {
    console.log('result', result);
  }, [result]);  // result가 변경될 때마다 로그 출력

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData();
      formData.append('review', review);
  
      const response = await axios.post('http://localhost:8007/classification', formData, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
      });
  
      setResult(response.data.result);
      console.log(review);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div className="container">
      <h1>Review Classification</h1>
      <form onSubmit={handleSubmit}>
        <textarea
          value={review}
          onChange={(e) => setReview(e.target.value)}
          placeholder="Enter your review"
          required
        />
        <button type="submit">Submit</button>
      </form>
      {result && (
        <div>
          <p>
            Label: {result.label === 'LABEL_0' ? '😢' : '😊'}
          </p>
          <p>Score: {result.score}</p>
        </div>
      )}
    </div>
  );
}

export default App;
