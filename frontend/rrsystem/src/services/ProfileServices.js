import api from "./API";

export const fetchProfileData = async () => {
    try {
        // ID: 1 is a test
        const response = await api.get("/clients/id/1");
        return response.data;
    } catch (error) {
        console.error("Error when fetching profile data: ", error);
        throw error;
    }
};
