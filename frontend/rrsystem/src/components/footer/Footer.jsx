import React from 'react';
import './Footer.css';

export default function Footer() {
    return (
        <footer>
            <p>All rights reserved &copy; 2025</p>
            <p>
                Dev: Yohan Mendonça.{' '}
                <a href="mailto:yohan.dev6@gmail.com">yohan.dev6@gmail.com</a>
            </p>
            <div className="social-links">
                <a href="https://linkedin.com" target="_blank" rel="noopener noreferrer">LinkedIn</a> |{' '}
                <a href="https://github.com/yohanDev6/" target="_blank" rel="noopener noreferrer">GitHub</a>
            </div>
        </footer>
    );
}
