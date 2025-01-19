import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Auth.css";
import { ClientService } from "../../services/ClientService";
import {loginUser} from "../../services/AuthService";

export default function Register() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleRegister = async (e) => {
        e.preventDefault();

        if (!name || !email || !password) {
            setError("Please fill in all fields.");
            return;
        } else if (password !== confirmPassword) {
            setError("Password and confirm password fields must be equals");
            return;
        }

        setLoading(true);
        setError(null);

        try {
            await ClientService.createClient({ name, email, password });
            await loginUser(email, password);
            navigate('/');
        } catch (err) {
            setError(err.message || "Register failed. Please try again.");
        }
    };
    return (
        <main className="auth-container">
            <form onSubmit={handleRegister}>
                <h2>Register</h2>

                {error && <p className="error-message">{error}</p>}

                <div className="form-group">
                    <label htmlFor="name">Name</label>
                    <input
                        type="text"
                        id="name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        placeholder="Enter your name"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder="Enter your email"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="Enter a strong password"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="confirmPassword">Confirm password</label>
                    <input
                        type="password"
                        id="confirmPassword"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        placeholder="Repeat the password"
                        required
                    />
                </div>
                <button
                type="submit"
                className="auth-button"
                disabled={loading}
                >
                    {loading ? "Registering..." : "Register"}
                </button>
            </form>
            <p>If you already have an account. <a href="/login">Login</a></p>
        </main>
    );
}
