window.addEventListener("load", function () {
  const formulario = document.querySelector("#deletePatientModal");

  formulario.addEventListener("submit", function (event) {
    let patient_id = document.querySelector("#delete_patient_id").value;

    //con fetch invocamos a la API de odontologos con el método DELETE
    //pasandole el id en la URL
    const params = new URLSearchParams({
      id: patient_id,
    });

    const url = `/pacientes/eliminar?${params.toString()}`;
    const settings = {
      method: "DELETE",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then(function (response) {
        console.info("fetch()", response);
        return response;
      });

    //Eliminar el registro del odontologo de la tabla principal
    let row_id = "#tr_" + id;
    document.querySelector(row_id).remove();
  });
});

//Es la funcion que se invoca cuando se hace click sobre el icono para editar un registro del listado
//se encarga de llenar el formulario con los datos del odontologo que se desea modificar
function deleteFindBy(id) {
  const url = "/pacientes/" + id;
  const settings = {
    method: "GET",
  };
  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let paciente = data;
      document.querySelector("#delete_patient_id").value = paciente.id;
      document.querySelector("#delete_name").value = paciente.nombre;
      document.querySelector("#delete_lastname").value = paciente.apellido;
      document.querySelector("#delete_dni").value = paciente.dni;
      document.querySelector("#delete_fechaingreso").value = paciente.fechaIngreso;
      document.querySelector("#delete_calle").value = paciente.domicilio.calle;
      document.querySelector("#delete_numero").value = paciente.domicilio.numero;
      document.querySelector("#delete_localidad").value = paciente.domicilio.localidad;
      document.querySelector("#delete_provincia").value = paciente.domicilio.provincia;
    })
    .catch((error) => {
      alert("Error: " + error);
    });
}