import api from './api';

export async function loginUser(email, password) {
    try {
        const response = await api.post('/login', { email, password });
        const token = response.data.token;
        if (!token) {
            throw new Error('Authentication failed: No token received.');
        }
        return token;
    } catch (error) {
        const errorMessage = error.response?.data?.message || 'Login failed.';
        throw new Error(errorMessage);
    }
}