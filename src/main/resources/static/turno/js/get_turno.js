window.addEventListener("load", function () {
  (function () {
    //con fetch invocamos a la API de pacientes con el método GET
    //nos devolverá un JSON con una colección de pacientes
    const url = "/turnos";
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        //recorremos la colección de pacientes del JSON
        for (turno of data) {
          //por cada paciente armaremos una fila de la tabla
          //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la paciente
          var tableBody = document
            .getElementById("tableTurn")
            .getElementsByTagName("tbody")[0];
          var turnoRow = tableBody.insertRow(tableBody.rows.length);
          let tr_id = "tr_" + turno.id;
          turnoRow.id = tr_id;

          let editIcon =
            '<a href="#editTurnModal" class="edit" data-toggle="modal">' +
            '<i class="material-icons"' +
            " id=" +
            '"editIcon_' +
            turno.id +
            '" ' +
            'onclick="findBy(' +
            turno.id +
            ')"' +
            'data-toggle="tooltip" title="Edit">&#xE254;</i></a>';

          let deleteIcon =
            '<a href="#deleteTurnModal" class="delete" data-toggle="modal">' +
            '<i class="material-icons"' +
            " id=" +
            '"deleteIcon_' +
            turno.id +
            '" ' +
            'onclick="deleteFindBy(' +
            turno.id +
            ')"' +
            'data-toggle="tooltip" title="Delete">&#xE872;</i></a>';

          //armamos cada columna de la fila
          //como primer columna pondremos el boton modificar
          //luego los datos de la paciente
          //como ultima columna el boton eliminar
          turnoRow.innerHTML =
            "<td>" +
            turno.id +
            "</td>" +
            '<td class="td_fechaturno">' +
            turno.fechaCita +
            "</td>" +
            "<td>" +
            editIcon +
            deleteIcon +
            "</td>";
        }
      });
  })(function () {
    let pathname = window.location.pathname;
    if (pathname == "/administrar-turnos.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  });
});

function GoToHomePage()
{
    window.location.href = 'http://localhost:8080/index.html';
    window.location.pathname = '/index.html'
}