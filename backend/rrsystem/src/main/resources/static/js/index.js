// Buscar salas
const token = localStorage.getItem('jwtToken');

fetch('/rooms', {
    method: 'GET',
    headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
    }
})
.then(response => {
    if (response.ok) {
        return response.json();
    } else {
        throw new Error('Não autorizado');
    }
})
.then(data => {
    loadRooms(data.rooms);
})
.catch(error => {
    console.error('Erro de autenticação ou expiração de token', error);
    window.location.href = '/login';
});

//Exibir salas
function loadRooms(data) {
	const roomsdiv = document.querySelector('#rooms');
	rooms.innerHTML = '';
	
	data.forEach(room => {
		const div = document.createElement('<div>');
		div.classList.add('room');
		
		div.innerHTML = `
			<h3>${room.name}</h3>
			<p>Capacidade: ${room.capacity}</p>
			<p>${room.availability ? 'Disponível' : 'Indisponível'}</p>
		`;

		roomsdiv.appendChild(div);
	})
	
	const rooms = document.querySelectorAll(".room");

    rooms.forEach((room) => {
        const availability = room.querySelector(".availability");

        if (availability.textContent.trim() === "Available") {
            availability.style.color = "green";
        } else if (availability.textContent.trim() === "Unavailable") {
            availability.style.color = "red";
        }
    });
}





