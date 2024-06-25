// Obtener la fecha actual en formato YYYY-MM-DD
const fechaActual = new Date();
    const dia = String(fechaActual.getDate()).padStart(2, '0');
    const mes = String(fechaActual.getMonth() + 1).padStart(2, '0'); // Los meses empiezan en 0
    const anhio = fechaActual.getFullYear();
    
    const fechaFormateada = `${dia}/${mes}/${anhio}`;

    // Asignar la fecha actual al atributo max del input
    const inputFecha = document.getElementById('fechaIngreso-add');
    inputFecha.setAttribute('max', fechaFormateada);

    const inputFecha2 = document.getElementById('fechaTurno-add');
    inputFecha2.setAttribute('max', fechaFormateada);
