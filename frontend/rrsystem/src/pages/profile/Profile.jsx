import React from "react";
import "./Profile.css";

export default function Profile() {
    return (
        <div className="profile-page">
            <h2>Profile page</h2>
            <div className="div-data">
                <h3>Your user name</h3>
                <p>Your email</p>
                <p>Your phone</p>
                <p>You are not an administrator</p>
            </div>
            <hr />
            <div className="div-actions">
                <button className="active-admin-button">
                    Request to be an administrator
                </button>
                <button className="update-data-button">Update my data</button>
                <button className="delete-account-button">
                    Delete my account
                </button>
            </div>
        </div>
    );
}
