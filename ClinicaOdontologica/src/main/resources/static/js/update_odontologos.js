window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_odontologo_form');

    formulario.addEventListener('submit', function (event) {
        let odontologoId = document.querySelector('#odontologo_id').value;
        const formData = {
            id: odontologoId,
            matricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
        };

        const url = '/odontologos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
});
function hideForm() {
    // Animar la tabla para que vuelva a su posición original
    gsap.to('.odontologos-table-wrapper', { x: -50, duration: 0.5, onComplete: () => {
            // Luego de la animación, ocultar el formulario
            document.querySelector('#odontologo_updating').style.display = "none";
            // Restaurar la posición de la tabla
            gsap.set('.odontologos-table-wrapper', { x: 0 });
        }});
}
    function animateBoxShadow() {
        gsap.fromTo('#odontologo_updating',
            { boxShadow: '0 0 0px rgba(255, 255, 255, 0)' },
            { boxShadow: '0 0 10px rgba(255, 255, 255, 1)', duration: 0.5, yoyo: true, repeat: 1 }
        );
    }

function findBy(id) {
    const url = '/odontologos/buscar-id/' + id;
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let odontologo = data;
            document.querySelector('#odontologo_id').value = odontologo.id;
            document.querySelector('#matricula').value = odontologo.matricula;
            document.querySelector('#nombre').value = odontologo.nombre;
            document.querySelector('#apellido').value = odontologo.apellido;

            const updatingForm = document.querySelector('#odontologo_updating');

            if (updatingForm.style.display === 'block') {
                // Si el formulario ya está visible, animar el box-shadow
                animateBoxShadow();
            } else {
                // Si el formulario no está visible, mostrarlo:
                gsap.fromTo(updatingForm, { x: -100, display: 'none' }, { x: 0, display: 'block', duration: 0.5 });
                gsap.fromTo('.odontologos-table-wrapper', { x: -50, display: 'none' }, { x: 0, display: 'block', duration: 0.5 });
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


