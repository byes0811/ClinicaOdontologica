window.addEventListener("load", function () {

  //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
  //los datos que el usuario cargará para registrar un nuevo paciente
  const formulario = document.querySelector("#addPatientModal");

  //Ante un submit del formulario se ejecutará la siguiente funcion
  formulario.addEventListener("submit", function (event) {
    let fecha = new Date(document.querySelector("#add_fechaingreso").value);
    fecha.setMinutes(fecha.getMinutes() + fecha.getTimezoneOffset());

    //creamos un JSON que tendrá los datos del nuevo paciente
    const formData = {
      nombre: document.querySelector("#add_name").value,
      apellido: document.querySelector("#add_lastname").value,
      dni: document.querySelector("#add_dni").value,
      fechaIngreso: fecha,
      domicilio: {
        calle: document.querySelector("#add_calle").value,
        numero: document.querySelector("#add_numero").value,
        localidad: document.querySelector("#add_localidad").value,
        provincia: document.querySelector("#add_provincia").value,
      },
    };
    //invocamos utilizando la función fetch la API pacientes con el método POST que guardará
    //el paciente que enviaremos en formato JSON
    const url = "/pacientes";
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