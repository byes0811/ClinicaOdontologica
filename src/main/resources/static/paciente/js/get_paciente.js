window.addEventListener("load", function () {
  (function () {
    //con fetch invocamos a la API de pacientes con el método GET
    //nos devolverá un JSON con una colección de pacientes
    const url = "/pacientes";
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        //recorremos la colección de pacientes del JSON
        for (paciente of data) {
          //por cada paciente armaremos una fila de la tabla
          //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la paciente
          var tableBody = document
            .getElementById("tablePatient")
            .getElementsByTagName("tbody")[0];
          var pacienteRow = tableBody.insertRow(tableBody.rows.length);
          let tr_id = "tr_" + paciente.id;
          pacienteRow.id = tr_id;

          let editIcon =
            '<a href="#editPatientModal" class="edit" data-toggle="modal">' +
            '<i class="material-icons"' +
            " id=" +
            '"editIcon_' +
            paciente.id +
            '" ' +
            'onclick="findBy(' +
            paciente.id +
            ')"' +
            'data-toggle="tooltip" title="Edit">&#xE254;</i></a>';

          let deleteIcon =
            '<a href="#deletePatientModal" class="delete" data-toggle="modal">' +
            '<i class="material-icons"' +
            " id=" +
            '"deleteIcon_' +
            paciente.id +
            '" ' +
            'onclick="deleteFindBy(' +
            paciente.id +
            ')"' +
            'data-toggle="tooltip" title="Delete">&#xE872;</i></a>';

          //armamos cada columna de la fila
          //como primer columna pondremos el boton modificar
          //luego los datos de la paciente
          //como ultima columna el boton eliminar
          pacienteRow.innerHTML =
            "<td>" +
            paciente.id +
            "</td>" +
            '<td class="td_nombre">' +
            paciente.nombre.toUpperCase() +
            "</td>" +
            '<td class="td_apellido">' +
            paciente.apellido.toUpperCase() +
            "</td>" +
            '<td class="td_dni">' +
            paciente.dni +
            "</td>" +
            '<td class="td_fechaingreso">' +
            paciente.fechaIngreso +
            "</td>" +
            '<td class="td_calle">' +
            paciente.domicilio.calle +
            "</td>" +
            '<td class="td_numero">' +
            paciente.domicilio.numero +
            "</td>" +
            '<td class="td_localidad">' +
            paciente.domicilio.localidad +
            "</td>" +
            '<td class="td_provincia">' +
            paciente.domicilio.provincia +
            "</td>" +
            "<td>" +
            editIcon +
            deleteIcon +
            "</td>";
        }
      });
  })(function () {
    let pathname = window.location.pathname;
    if (pathname == "/administrar-pacientes.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  });
});

function GoToHomePage()
{
    window.location.href = 'http://localhost:8080/index.html';
    window.location.pathname = '/index.html'
}