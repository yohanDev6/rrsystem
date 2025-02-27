import React from "react";
import { useRooms } from "../../hooks/useRooms";
import "./Home.css";

export default function Home() {
    const { rooms, loading, error } = useRooms();

    return (
        <main>
            <h2>Home page</h2>
            {loading && <p>Loading client...</p>}
            {!loading && !error && rooms.length === 0 && <p>No rooms available.</p>}
            {error && <p>{error}</p>}
            <div className="rooms">
                {Array.isArray(rooms) && rooms.map((room) => (
                    <Room
                        key={room.id}
                        name={room.name}
                        capacity={room.capacity}
                        availability={room.availability}
                    />
                ))}
            </div>
        </main>
    );
}

function Room({ name, capacity, availability }) {
    return (
        <div className="room">
            <h3>{name}</h3>
            <p>Capacity: {capacity}</p>
            <p
                style={{
                    color: availability === "available" ? "green" : "red",
                }}
            >
                {availability} now
            </p>
        </div>
    );
}
