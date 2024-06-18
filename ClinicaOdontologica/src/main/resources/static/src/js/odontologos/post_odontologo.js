window.addEventListener('load', function () {

    // Al cargar la página buscamos y obtenemos el formulario donde estarán
    // los datos que el usuario cargará del nuevo odontólogo
    const formulario = document.querySelector('#add_new_odontologo');

    // Ante un submit del formulario se ejecutará la siguiente función
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        //Cerrar modal
        const modal = document.getElementById('add-odontologo');
        const modalInstance = bootstrap.Modal.getInstance(modal);
        modalInstance.hide();

        // Creamos un JSON que tendrá los datos del nuevo odontólogo
        const formData = {
            matricula: document.querySelector('#matricula-add').value,
            nombre: document.querySelector('#nombre-add').value,
            apellido: document.querySelector('#apellido-add').value,
        };
        // Invocamos utilizando la función fetch la API de odontólogos con el método POST que guardará
        // al odontólogo que enviaremos en formato JSON
        const url = '/odontologos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al agregar el odontólogo');
                }
                return response.json();
            })
            .then(data => {
                // Mensaje de éxito con SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: '¡Agregado!',
                    showConfirmButton: false,
                    timer: 1500,
                    timerProgressBar: true,
                    background: 'var(--color-card-background)',
                    color: 'var(--color-text-secondary)',
                });
                updateTable()
                formulario.reset();
            })
            .catch(error => {
                // Mensaje de error con SweetAlert2
                Swal.fire({
                    icon: 'error',
                    title: 'Error al agregar el odontólogo',
                    text: 'Por favor, inténtelo nuevamente.',
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true,
                    background: 'var(--color-card-background)',
                    color: 'var(--color-text-secondary)',
                });
                console.error('Error:', error);
            });
    });

});
