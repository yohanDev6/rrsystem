import api from './api';

export async function loginUser(email, password) {
    try {
        const response = await api.post('/login', { email, password });
        const token = response.data.token;
        const clientId = response.data.clientId;
        if (!token) {
            throw new Error('Authentication failed: No token received.');
        } else if (!clientId) {
            throw new Error('Authentication failed: No client id received.');
        }
        localStorage.setItem("jwtToken", token);
        localStorage.setItem("clientId", clientId);
    } catch (error) {
        const errorMessage = error.response?.data?.message || 'Login failed.';
        throw new Error(errorMessage);
    }
}

export async function logoutUser() {
    try {
        const response = await api.post('/logout', {}, 
            { headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}` } 
        });
        localStorage.removeItem('jwtToken');
        localStorage.removeItem('userId');
        return response.status;
    } catch (error) {
        console.error("Error logout: ",error);
    }
}