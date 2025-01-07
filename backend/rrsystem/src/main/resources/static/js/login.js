document.getElementById('loginForm').addEventListener('submit', async function (event) {
	event.preventDefault();

	localStorage.removeItem('jwtToken');
    const errorDiv = document.getElementById('errorMessage');
    errorDiv.textContent = '';

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

	const loginButton = document.getElementById('loginButton');
	loginButton.disabled = true;
    
	try {
        const response = await fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        if (response.ok) {
            const result = await response.json();
            localStorage.setItem('jwtToken', result.token);
			//window.location.href = '/';
        } else {
            const error = await response.json();
            errorDiv.textContent = error.message || 'Erro: verifique os dados e tente novamente.';
        }
    } catch (err) {
        errorDiv.textContent = 'Erro ao tentar fazer login. Verifique sua conexão ou tente novamente.';
    } finally {
		loginButton.disabled = false;
		console.log("Processo de login terminado");
	}
});