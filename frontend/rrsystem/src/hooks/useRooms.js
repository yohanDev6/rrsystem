import { useEffect, useState } from 'react';
import { RoomService } from '../services/RoomService';

export function useRooms() {
    const [rooms, setRooms] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        async function fetchRooms() {
            try {
                const response = await RoomService.getAllRooms();
                setRooms(response);
            } catch (err) {
                setError(err.message || 'Failed to fetch rooms.');
            } finally {
                setLoading(false);
            }
        }

        fetchRooms();
    }, []);

    return { rooms, loading, error };
}
