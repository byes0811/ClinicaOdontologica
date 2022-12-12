window.addEventListener("load", function () {

  //Buscamos y obtenemos el formulario donde estan
  //los datos que el usuario quiere modificar para un paciente en particular
  const formulario = document.querySelector("#editPatientModal");

  formulario.addEventListener("submit", function (event) {
    let pacienteId = document.querySelector("#edit_patient_id").value;

    let fecha = new Date(document.querySelector("#edit_fechaingreso").value);
    fecha.setMinutes(fecha.getMinutes() + fecha.getTimezoneOffset());

    //Creamos un JSON que tendrá los datos del paciente. A diferencia de registrar un paciente nuevo, en este caso enviamos el id
    //para poder identificarlo y modificarlo, para no cargarlo como nuevo
    const formData = {
      id: document.querySelector("#edit_patient_id").value,
      nombre: document.querySelector("#edit_name").value,
      apellido: document.querySelector("#edit_lastname").value,
      dni: document.querySelector("#edit_dni").value,
      fechaIngreso: fecha,
      domicilio: {
        calle: document.querySelector("#edit_calle").value,
        numero: document.querySelector("#edit_numero").value,
        localidad: document.querySelector("#edit_localidad").value,
        provincia: document.querySelector("#edit_provincia").value,
      },
    };

    //invocamos utilizando la función fetch la API pacientes con el método PUT que modificará
    //la informacion del paciente que enviamos en formato JSON
    const url = "/pacientes/actualizar";
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
  const url = "/pacientes/" + id;
  const settings = {
    method: "GET",
  };
  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let paciente = data;
      document.querySelector("#edit_patient_id").value = paciente.id;
      document.querySelector("#edit_name").value = paciente.nombre;
      document.querySelector("#edit_lastname").value = paciente.apellido;
      document.querySelector("#edit_dni").value = paciente.dni;
      document.querySelector("#edit_fechaingreso").value = paciente.fechaIngreso;
      document.querySelector("#edit_calle").value = paciente.domicilio.calle;
      document.querySelector("#edit_numero").value = paciente.domicilio.numero;
      document.querySelector("#edit_localidad").value = paciente.domicilio.localidad;
      document.querySelector("#edit_provincia").value = paciente.domicilio.provincia;
    })
    .catch((error) => {
      alert("Error: " + error);
    });
}