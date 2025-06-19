import React from 'react';

const AboutUs = () => {
  return (
    <div className="about-container bottom-space" style={styles.container}>
      <h1 style={styles.heading}>About Us</h1>
      <p style={styles.paragraph}>
        Welcome to our website! We're passionate about building great digital experiences.
        Our team is made up of developers, designers, and creatives who love turning ideas into reality.
      </p>

      <p style={styles.paragraph}>
        Our mission is to deliver high-quality software solutions that are intuitive, scalable, and user-friendly.
        Whether it's web development, mobile apps, or design, we strive to make things simple and effective.
      </p>

      <p style={styles.paragraph}>
        We're always looking to grow, learn, and connect. If you'd like to collaborate or just say hi, feel free to contact us!
      </p>

      <p style={styles.signature}>
        — The Team
      </p>
    </div>
  );
};

const styles = {
  container: {
    maxWidth: '800px',
    margin: '50px auto',
    padding: '0 20px',
    fontFamily: 'Arial, sans-serif',
    lineHeight: '1.6',
    color: '#333',
  },
  heading: {
    fontSize: '36px',
    marginBottom: '20px',
  },
  paragraph: {
    marginBottom: '16px',
    fontSize: '18px',
  },
  signature: {
    marginTop: '30px',
    fontStyle: 'italic',
    fontSize: '16px',
  },
};

export default AboutUs;
