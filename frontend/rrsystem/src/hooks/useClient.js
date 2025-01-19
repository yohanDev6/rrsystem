import { useState, useEffect } from 'react';
import { ClientService } from '../services/ClientService';

export function useClient() {
    const [client, setClient] = useState({});
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const clientId = localStorage.getItem('clientId');
        if (!clientId) {
            setError('Client ID not found in localStorage.');
            setLoading(false);
            return;
        }
    
        async function fetchClient() {
            try {
                const response = await ClientService.getClientById(clientId);
                setClient(response || null);
            } catch (err) {
                setError(err.message || 'Failed to fetch client.');
            } finally {
                setLoading(false);
            }
        }
    
        fetchClient();
    }, []);

    return {client, loading, error};
}