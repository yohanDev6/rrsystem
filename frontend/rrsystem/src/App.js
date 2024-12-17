import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/header/Header";
import Nav from "./components/nav/Nav";
import Home from "./pages/home/Home";
import Admin from "./pages/admin/Admin";
import Profile from "./pages/profile/Profile";
import Room from "./pages/room/Room";

function App() {
    return (
        <Router>
            <Header />
            <Nav />
            <main>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/admin" element={<Admin />} />
                    <Route path="/profile" element={<Profile />} />
                    <Route path="/room" element={<Room />} />
                </Routes>
            </main>
        </Router>
    );
}

export default App;
