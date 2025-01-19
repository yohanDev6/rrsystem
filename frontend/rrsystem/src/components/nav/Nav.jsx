import React from "react";
import { Link } from "react-router-dom";
import './Nav.css';
import { logoutUser } from "../../services/AuthService";

export default function Nav() {

    const logout = () => {
        logoutUser();
    }

    return (
        <nav>
            <Link to="/" className="anchor">Home</Link>
            <Link to="/profile" className="anchor">Profile</Link>
            <Link to="/myreservations" className="anchor">My Reservations</Link>
            <a href="/login" className="anchor" style={{color: '#f55'}} onClick={logout}>Exit</a>
        </nav>
    );
}