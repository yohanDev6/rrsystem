import { useEffect, useState } from 'react';
import api from '../services/api';

export function useRooms() {
    const [rooms, setRooms] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        async function fetchRooms() {
            try {
                const response = await api.get('/rooms');
                setRooms(response.data);
            } catch (err) {
                setError('Failed to fetch rooms.');
            } finally {
                setLoading(false);
            }
        }

        fetchRooms();
    }, []);

    return { rooms, loading, error };
}
