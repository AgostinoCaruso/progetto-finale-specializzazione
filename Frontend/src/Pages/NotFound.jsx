import React from 'react';

const NotFound = () => {
  return (
    <div style={styles.container} className='bottom-space'>
      <h1 style={styles.code}>404</h1>
      <h2 style={styles.message}>Page Not Found</h2>
      <p style={styles.paragraph}>
        Oops! The page you're looking for doesn't exist or has been moved.
      </p>
      <a href="/" style={styles.link}>Go back to Home</a>
    </div>
  );
};

const styles = {
  container: {
    textAlign: 'center',
    padding: '100px 20px',
    fontFamily: 'Arial, sans-serif',
    color: '#333',
  },
  code: {
    fontSize: '96px',
    fontWeight: 'bold',
    margin: '0',
  },
  message: {
    fontSize: '28px',
    margin: '20px 0',
  },
  paragraph: {
    fontSize: '18px',
    marginBottom: '30px',
  },
  link: {
    fontSize: '16px',
    textDecoration: 'none',
    color: '#007bff',
  },
};

export default NotFound;
