// Función para construir opciones de odontólogos y pacientes en sus respectivos <select>
function construirSelects(odontologos, odontologos1, pacientes, pacientes1) {
    // Construir opciones para odontólogos
    const selectOdontologos = document.getElementById('nombreOdontologo');
    selectOdontologos.innerHTML = ''; // Limpiar opciones existentes

    odontologos.forEach(odontologo => {
        const option = document.createElement('option');
        option.value = odontologo.id;
        option.textContent = `${odontologo.nombre} ${odontologo.apellido}`;
        selectOdontologos.appendChild(option);
    });

    const selectOdontologos1 = document.getElementById('nombreOdontologo-add');
    selectOdontologos1.innerHTML = ''; // Limpiar opciones existentes

    odontologos1.forEach(odontologo => {
        const option = document.createElement('option');
        option.value = odontologo.id;
        option.textContent = `${odontologo.nombre} ${odontologo.apellido}`;
        selectOdontologos1.appendChild(option);
    });

    // Construir opciones para pacientes
    const selectPacientes = document.getElementById('nombrePaciente');
    selectPacientes.innerHTML = ''; // Limpiar opciones existentes

    pacientes.forEach(paciente => {
        const option = document.createElement('option');
        option.value = paciente.id;
        option.textContent = `${paciente.nombre} ${paciente.apellido}`;
        selectPacientes.appendChild(option);
    });

    const selectPacientes1 = document.getElementById('nombrePaciente');
    selectPacientes1.innerHTML = ''; // Limpiar opciones existentes

    pacientes1.forEach(paciente => {
        const option = document.createElement('option');
        option.value = paciente.id;
        option.textContent = `${paciente.nombre} ${paciente.apellido}`;
        selectPacientes1.appendChild(option);
    });


}

// Función para obtener odontólogos y pacientes
function obtenerDatosParaSelects() {
    const urlOdontologos = "/odontologos";
    const urlPacientes = "/pacientes";

    const fetchOdontologos = fetch(urlOdontologos).then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    });

    const fetchPacientes = fetch(urlPacientes).then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    });

    Promise.all([fetchOdontologos, fetchPacientes])
        .then(data => {
            const odontologos = data[0];
            const pacientes = data[1];
            
            if (odontologos && pacientes) {
                construirSelects(odontologos, pacientes);
            } else {
                throw new Error('No se recibieron datos válidos de odontólogos o pacientes.');
            }
        })
        .catch(error => {
            console.error('Error fetching odontólogos o pacientes:', error);
            // Manejar el error, por ejemplo, mostrando un mensaje al usuario
            Swal.fire({
                icon: 'error',
                title: 'Error al obtener datos',
                text: 'Hubo un problema al obtener la lista de odontólogos o pacientes.',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                background: 'var(--color-card-background)',
                color: 'var(--color-text-secondary)',
            });
        });
}

// Llamar a la función para obtener odontólogos y pacientes cuando se carga la página u otra acción necesaria
window.addEventListener('load', function() {
    obtenerDatosParaSelects();
});