window.addEventListener("load", function () {
    const formulario = document.querySelector("#update_turno_form");

    formulario.addEventListener("submit", function (event) {
        event.preventDefault();
        let turnoId = document.querySelector("#turno_id").value;
        const formData = {
            id: turnoId,
            paciente: {
                id: document.querySelector('#nombrePaciente').value
            },
            odontologo: {
                id: document.querySelector('#nombreOdontologo').value
            },
            fecha: document.querySelector('#fechaTurno').value,
        };

        const url = "/turnos";
        const settings = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData),
        };

        fetch(url, settings)
            .then((response) => {
                if (response.ok) {
                    return Promise.resolve("Eliminado correctamente");
                } else {
                    return response.json().then(body => {
                        throw new Error(body.message);
                    });
                }
            })
            .then((data) => {
                // Mostrar alerta de éxito
                Swal.fire({
                    position: "bottom-end",
                    toast: true,
                    icon: "success",
                    title: "Actualizado con éxito",
                    background: "var(--color-card-background)",
                    color: "var(--color-text-secondary)",
                    showConfirmButton: false,
                    timer: 1500,
                    timerProgressBar: true,
                });
                updateTableTurno();
            })
            .catch((error) => {
                console.error("Error:", error);
                Swal.fire({
                    position: "bottom-end",
                    toast: true,
                    icon: "error",
                    title: "Error",
                    text: error.message,
                    background: "var(--color-card-background)",
                    color: "var(--color-text-secondary)",
                    showConfirmButton: false,
                    timer: 3000,
                    timerProgressBar: true,
                });
            });
    });
});
function hideForm() {
    // Animar la tabla para que vuelva a su posición original

    document.querySelector("#update-form").style.display = "none";
}
function animateBoxShadow() {
    gsap.fromTo(
        "#turno_updating",
        { boxShadow: "0 0 0px rgba(255, 255, 255, 0)" },
        {
            boxShadow: "0 0 10px rgba(0, 116, 160, 1)",
            duration: 0.5,
            yoyo: true,
            repeat: 1,
        }
    );
}

function findBy(id) {
    const url = "/turnos/" + id;
    const settings = {
        method: "GET",
    };

    fetch(url, settings)
        .then((response) => response.json())
        .then((data) => {
            let turno = data;

            document.querySelector("#turno_id").value = turno.id;
            document.querySelector("#nombrePaciente").value = turno.paciente.id;
            document.querySelector("#nombreOdontologo").value = turno.odontologo.id;
            document.querySelector("#fechaTurno").value = turno.fecha;

            const updatingForm = document.querySelector("#update-form");

            if (updatingForm.style.display === "block") {
                // Si el formulario ya está visible, animar el box-shadow
                animateBoxShadow();
            } else {
                // Si el formulario no está visible, mostrarlo:
                gsap.fromTo(
                    updatingForm,
                    { x: -100, display: "none" },
                    { x: 0, display: "block", duration: 0.5 }
                );
                gsap.fromTo(
                    ".turnos-table-wrapper",
                    { x: -50, display: "none" },
                    { x: 0, display: "block", duration: 0.5 }
                );
            }
        })
        .catch((error) => {
            console.error("Error:", error);
        });
}

function updateTableTurno() {
    const url = "/turnos";
    const settings = {
        method: "GET",
    };

    fetch(url, settings)
        .then((response) => response.json())
        .then((data) => {
            const tableBody = document.getElementById("turnoTableBody");
            tableBody.innerHTML = ""; // Limpiar contenido actual de la tabla

            data.forEach((turno) => {
                let turnoRow = tableBody.insertRow();
                let tr_id = "tr_" + turno.id;
                    turnoRow.id = tr_id;

                    // Botón de editar
                    let editButton =
                        "<button" +
                        " id=" +
                        '"' +
                        "btn_edit_" +
                        turno.id +
                        '"' +
                        ' type="button" onclick="findBy(' +
                        turno.id +
                        ')" class="btn_edit">' +
                        '<i class="fa-solid fa-pen-to-square"></i>' +
                        "</button>";

                    // Botón de eliminar
                    let deleteButton =
                        "<button" +
                        " id=" +
                        '"' +
                        "btn_delete_" +
                        turno.id +
                        '"' +
                        ' type="button" onclick="deleteBy(' +
                        turno.id +
                        ')" class="btn_delete">' +
                        '<i class="fa-solid fa-trash"></i>' +
                        "</button>";

                    // Armamos cada columna de la fila
                    // Primero los datos del odontólogo
                    // Luego los botones de editar y eliminar
                    
                    const odontologo = `${turno.odontologo.nombre} ${turno.odontologo.apellido}`;
                    const paciente = `${turno.paciente.nombre} ${turno.paciente.apellido}`;

                    turnoRow.innerHTML =
                        "<td>" +
                        turno.id +
                        "</td>" +
                        '<td class="td_odontologo">' +
                        odontologo.toUpperCase() +
                        "</td>" +
                        '<td class="td_paciente">' +
                        paciente.toUpperCase() +
                        "</td>" +
                        '<td class="td_fechaTurno">' +
                        turno.fecha +
                        "</td>" +
                        '<td class="actions-buttons">' +
                        editButton +
                        deleteButton +
                        "</td>";
            });
        })
        .catch((error) => {
            console.error("Error:", error);
        });
}
