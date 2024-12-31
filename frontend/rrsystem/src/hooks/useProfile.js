// src/hooks/useProfile.js
import { useState, useEffect } from "react";
import { fetchProfileData } from "../services/ProfileServices";

export const useProfile = () => {
    const [profileData, setProfileData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadProfile = async () => {
            try {
                const data = await fetchProfileData();
                setProfileData(data);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        loadProfile();
    }, []);

    return { profileData, loading, error };
};
