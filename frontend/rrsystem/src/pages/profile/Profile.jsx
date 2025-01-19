import React from "react";
import { useClient } from "../../hooks/useClient";

export default function Profile() {
    const { client, loading, error } = useClient();

    return (
        <main>
            <section>
                <h2>Profile page</h2>
                {loading && <p>Loading client...</p>}
                {!loading && !error && !client && <p>Client not found.</p>}
                {error && <p>{error}</p>}
                {!loading && !error && client && (
                    <Client 
                        name={client.name} 
                        email={client.email} 
                        isActive={client.isActive} 
                    />
                )}
            </section>
        </main>
    );
}

function Client({ name, email, isActive }) {
    return (
        <div className="client-data">
            <h3>{name}</h3>
            <p>{email}</p>
            <p>Is active: {isActive ? 'yes' : 'no'}</p>
        </div>
    );
}