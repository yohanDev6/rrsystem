import React from "react";
import { useProfile } from "../../hooks/useProfile";
import "./Profile.css";

export default function Profile() {
    const { profileData, loading, error } = useProfile();

    if (loading) {
        return (
            <div className="profile-page">
                <h2>Profile Page</h2>
                <div className="loading">Loading...</div>
            </div>
        );
    }

    if (error) {
        return (
            <div className="profile-page">
                <h2>Profile Page</h2>
                <div className="error">Error: {error}</div>
            </div>
        );
    }

    return (
        <div className="profile-page">
            <h2>Profile Page</h2>
            <div className="div-data">
                <h3>{profileData?.username || "Username unavailable"}</h3>
                <p>{profileData?.email || "Email unavailable"}</p>
                <p>{profileData?.phone || "Phone unavailable"}</p>
                <p>
                    {profileData?.isAdmin
                        ? "You are an administrator"
                        : "You are not an administrator"}
                </p>
            </div>
            <hr />
            <div className="div-actions">
                {!profileData?.isAdmin && (
                    <button className="active-admin-button">
                        Request to be an administrator
                    </button>
                )}
                <button className="update-data-button">Update my data</button>
                <button className="delete-account-button">
                    Delete my account
                </button>
            </div>
        </div>
    );
}
