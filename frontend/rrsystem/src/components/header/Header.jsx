import React from "react";
import "./Header.css";
import Nav from "../nav/Nav";

export default function Header() {
    return (
        <header className="header">
            <h1 className="header-title">RoomSystemReservation</h1>
            <Nav />
        </header>
    );
}
