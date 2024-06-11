window.addEventListener('load', function () {

    // Al cargar la página buscamos y obtenemos el formulario donde estarán
    // los datos que el usuario cargará del nuevo odontólogo
    const formulario = document.querySelector('#add_new_odontologo');

    // Ante un submit del formulario se ejecutará la siguiente función
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        // Creamos un JSON que tendrá los datos del nuevo odontólogo
        const formData = {
            matricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
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
            .then(response => response.json())
            .then(data => {
                // Si no hay ningún error se muestra un mensaje diciendo que el odontólogo
                // se agregó correctamente
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Odontólogo Agregado </div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                setTimeout(function () {
                    document.querySelector('#response').style.display = "none";
                }, 3000); // Ocultar el mensaje después de 3 segundos
                resetForm();

            })
            .catch(error => {
                // Si hay algún error se muestra un mensaje diciendo que el odontólogo
                // no se pudo guardar y se intenta nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error, intente nuevamente</strong> </div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                setTimeout(function () {
                    document.querySelector('#response').style.display = "none";
                }, 3000); // Ocultar el mensaje después de 3 segundos
                // Se dejan todos los campos vacíos por si se quiere ingresar otro odontólogo
                resetForm();
            })
    });

    function resetForm() {
        document.querySelector('#matricula').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
    }

    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/") {
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname === "/get_odontologos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});
