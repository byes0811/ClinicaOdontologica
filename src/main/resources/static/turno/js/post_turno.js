window.addEventListener("load", function () {

  //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
  //los datos que el usuario cargará para registrar un nuevo paciente
  const formulario = document.querySelector("#addTurnModal");

  //Ante un submit del formulario se ejecutará la siguiente funcion
  formulario.addEventListener("submit", function (event) {

    let fecha = new Date(document.querySelector("#add_fechaturno").value);
    fecha.setMinutes(fecha.getMinutes() + fecha.getTimezoneOffset());

    const element_patient_id = document.querySelector("#add_formControlSelectPatient").value;
    const patient_id = element_patient_id.split('.')[0];

    const element_dentist_id = document.querySelector("#add_formControlSelectDentist").value;
    const dentist_id = element_dentist_id.split('.')[0];

    const params = new URLSearchParams({
      pacienteId: patient_id,
      odontologoId: dentist_id
    });

    const url = `/turnos?${params.toString()}`;
    //creamos un JSON que tendrá los datos del nuevo paciente
    const formData = {
      fechaCita: fecha
    };
    //invocamos utilizando la función fetch la API pacientes con el método POST que guardará
    //el paciente que enviaremos en formato JSON

    const settings = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        //Si no hay ningun error se muestra un mensaje diciendo que el paciente se creo exitosamente.
        let successAlert =
          '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong></strong> Paciente creado con exito </div>";

        document.querySelector("#response").innerHTML = successAlert;
        document.querySelector("#response").style.display = "block";
        resetUploadForm();
      })
      .catch((error) => {
        //Si hay algun error se muestra un mensaje diciendo que el paciente
        //no se pudo guardar y se intente nuevamente
        let errorAlert =
          '<div class="alert alert-danger alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong> Error intente nuevamente</strong> </div>";

        document.querySelector("#response").innerHTML = errorAlert;
        document.querySelector("#response").style.display = "block";
        //se dejan todos los campos vacíos por si se quiere ingresar un nuevo registro
        resetUploadForm();
      });
  });

  function resetUploadForm() {
    document.querySelector("#add_name").value = "";
    document.querySelector("#add_lastname").value = "";
    document.querySelector("#add_dni").value = "";
    document.querySelector("#add_fechaingreso").value = "";
    document.querySelector("#add_calle").value = "";
    document.querySelector("#add_numero").value = "";
    document.querySelector("#add_localidad").value = "";
    document.querySelector("#add_provincia").value = "";
  }

  (function () {
    let pathname = window.location.pathname;
    if (pathname === "/") {
      document.querySelector(".nav .nav-item a:first").addClass("active");
    } else if (pathname == "/administrar-pacientes.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  })();
});

function findPacientAndDentistBy() {
  const urlOdontologos = "/odontologos/";
  const settingsOdontologos = {
    method: "GET",
  };
  fetch(urlOdontologos, settingsOdontologos)
    .then((response) => response.json())
    .then((data) => {
      let formControlSelectDentist = document.querySelector(
        "#add_formControlSelectDentist"
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
        "#add_formControlSelectPatient"
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
