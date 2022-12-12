window.addEventListener("load", function () {

  //Buscamos y obtenemos el formulario donde estan
  //los datos que el usuario quiere modificar para un paciente en particular
  const formulario = document.querySelector("#editTurnModal");

  formulario.addEventListener("submit", function (event) {
    let turnoId = document.querySelector("#edit_turn_id").value;

    let fecha = new Date(document.querySelector("#edit_fechaturno").value);
    fecha.setMinutes(fecha.getMinutes() + fecha.getTimezoneOffset());

    const element_patient_id = document.querySelector("#edit_formControlSelectPatient").value;
    const patient_id = element_patient_id.split('.')[0];

    const element_dentist_id = document.querySelector("#edit_formControlSelectDentist").value;
    const dentist_id = element_dentist_id.split('.')[0];

    const params = new URLSearchParams({
      pacienteId: patient_id,
      odontologoId: dentist_id
    });

    const url = `/turnos/actualizar?${params.toString()}`;

    //Creamos un JSON que tendrá los datos del paciente. A diferencia de registrar un paciente nuevo, en este caso enviamos el id
    //para poder identificarlo y modificarlo, para no cargarlo como nuevo
    const formData = {
      id: turnoId,
      fechaCita: fecha
    };

    //invocamos utilizando la función fetch la API pacientes con el método PUT que modificará
    //la informacion del paciente que enviamos en formato JSON
    const settings = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };
    fetch(url, settings).then((response) => response.json());
  });
});

//Es la funcion que se invoca cuando se hace click sobre el icono para editar un registro del listado
//se encarga de llenar el formulario con los datos del paciente que se desea modificar
function findBy(id) {
  const url = "/turnos/" + id;
  const settings = {
    method: "GET",
  };
  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let turno = data;
      document.querySelector("#edit_turn_id").value = turno.id;
      document.querySelector("#edit_fechaturno").value = turno.fechaCita;
    })
    .catch((error) => {
      alert("Error: " + error);
    });

  const urlOdontologos = "/odontologos/";
  const settingsOdontologos = {
    method: "GET",
  };
  fetch(urlOdontologos, settingsOdontologos)
    .then((response) => response.json())
    .then((data) => {
      let formControlSelectDentist = document.querySelector(
        "#edit_formControlSelectDentist"
      );

      for (odontologo of data) {
        formControlSelectDentist.innerHTML +=
          "<option>" +
          odontologo.id +
          ". " +
          odontologo.nombre +
          " " +
          odontologo.apellido;
        ("</option>");
      }
    })
    .catch((error) => {
      alert("Error: " + error);
    });

  const urlPacientes = "/pacientes/";
  const settingsPacientes = {
    method: "GET",
  };
  fetch(urlPacientes, settingsPacientes)
    .then((response) => response.json())
    .then((data) => {
      let formControlSelectPatient = document.querySelector(
        "#edit_formControlSelectPatient"
      );

      for (paciente of data) {
        formControlSelectPatient.innerHTML +=
          "<option>" +
          paciente.id +
          ". " +
          paciente.nombre +
          " " +
          paciente.apellido;
        ("</option>");
      }
    })
    .catch((error) => {
      alert("Error: " + error);
    });
}