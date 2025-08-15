// frontend/src/components/SlideShow.js
import React, { useState, useEffect } from 'react';
import './SlideShow.css';

export default function SlideShow({ images, interval = 5000 }) {
  const [current, setCurrent] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrent((prev) => (prev + 1) % images.length);
    }, interval);
    return () => clearInterval(timer);
  }, [images.length, interval]);

  return (
    <div className="slideshow-container">
      {images.map((src, idx) => (
        <div key={idx} className={`slide ${idx === current ? 'active' : ''}`}>
          <img src={src} alt={`Slide ${idx + 1}`} />
        </div>
      ))}
    </div>
  );
}
