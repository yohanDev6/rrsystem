import React from "react";
import {
    BrowserRouter as Router,
    Routes,
    Route,
    Navigate,
} from "react-router-dom";
import Header from "./components/header/Header";
import Nav from "./components/nav/Nav";
import Footer from "./components/footer/Footer";

import Login from "./pages/auth/Login";
import Register from "./pages/auth/Register";

import Home from "./pages/home/Home";
import Profile from "./pages/profile/Profile";
import MyReservations from "./pages/myreservations/MyReservations";

const isAuthenticated = () => {
    const token = localStorage.getItem("jwtToken");
    return !!token;
};

function App() {
    return (
        <Router>
            <Header />
            {isAuthenticated() && <Nav />}
            <div style={{ minHeight: "calc(100vh - 100px)" }}>
                <Routes>
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />

                    <Route
                        path="/"
                        element={
                            isAuthenticated() ? (
                                <Home />
                            ) : (
                                <Navigate to="/login" replace />
                            )
                        }
                    />
                    <Route
                        path="/profile"
                        element={
                            isAuthenticated() ? (
                                <Profile />
                            ) : (
                                <Navigate to="/login" replace />
                            )
                        }
                    />
                    <Route
                        path="/myreservations"
                        element={
                            isAuthenticated() ? (
                                <MyReservations />
                            ) : (
                                <Navigate to="/login" replace />
                            )
                        }
                    />
                </Routes>
            </div>
            <Footer />
        </Router>
    );
}

export default App;
