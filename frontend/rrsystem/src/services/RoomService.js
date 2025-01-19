import api from './api';

export const RoomService = {
    getAllRooms: async () => {
        const response = await api.get('/rooms');
        return Array.isArray(response.data) ? response.data : [];
    },

    getRoomById: async (id) => {
        const response = await api.get(`/rooms/${id}`);
        return response.data;
    },

    createRoom: async (roomData) => {
        const response = await api.post('/rooms', roomData);
        return response.data;
    },

    updateRoom: async (roomData) => {
        const response = await api.put(`/rooms/`, roomData);
        return response.data;
    },

    deleteRoom: async (id) => {
        const response = await api.delete(`/rooms/${id}`);
        return response.data;
    },
};