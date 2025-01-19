import api from './api';

export const ClientService = {
    getClientById: async (clientId) => {
        const response = await api.get(`/clients/${clientId}`);
        return response.data;
    },

    createClient: async (clientData) => {
        const response = await api.post('/clients', clientData);
        return response.status;
    },

    updateClient: async (clientData) => {
        const response = await api.put('/clients', clientData);
        return response.status;
    },

    deleteClient: async (clientId) => {
        const response = await api.delete(`/clients/${clientId}`);
        return response.status;
    }
}