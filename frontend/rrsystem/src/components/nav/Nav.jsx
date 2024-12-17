// src/components/Nav.jsx
import React from "react";
import { Link } from "react-router-dom";
import "./Nav.css";

export default function Nav() {
    return (
        <nav className="nav">
            <ul className="nav-list">
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="/admin">Admin</Link>
                </li>
                <li>
                    <Link to="/profile">Profile</Link>
                </li>
                <li>
                    <Link to="/room">Room</Link>
                </li>
            </ul>
        </nav>
    );
}
