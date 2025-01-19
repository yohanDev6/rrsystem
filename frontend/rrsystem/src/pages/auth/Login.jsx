import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Auth.css";
import {loginUser} from "../../services/AuthService";

export default function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        if (!email || !password) {
            setError("Please fill in all fields.");
            return;
        }

        setLoading(true);
        setError(null);

        try {
            await loginUser(email, password);
            navigate('/');
        } catch (err) {
            setError(err.message || "Login failed. Please try again.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <main className="auth-container">
            <form onSubmit={handleLogin}>
                <h2>Login</h2>

                {error && <p className="error-message">{error}</p>}

                <div className="form-group">
                    <label htmlFor="email">Email:</label>
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
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="Enter your password"
                        required
                    />
                </div>

                <button
                    type="submit"
                    className="auth-button"
                    disabled={loading}
                >
                    {loading ? "Logging in..." : "Login"}
                </button>
            </form>
            <p>If you do not have an account yet. <a href="/register">Register</a></p>
        </main>
    );
}
