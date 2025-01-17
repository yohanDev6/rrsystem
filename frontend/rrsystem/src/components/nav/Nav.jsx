import React from 'react';
import './Nav.css';

export default function Nav() {
    return (
        <nav>
            <a href="/">Home</a>
            <a href="/profile">Profile</a>
            <a href="/myreservations">My Reservations</a>
            <a href="/logout" style={{color: '#f55'}}>Exit</a>
        </nav>
    );
}