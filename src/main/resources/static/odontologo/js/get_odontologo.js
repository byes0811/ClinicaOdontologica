window.addEventListener("load", function () {
  (function () {
    //con fetch invocamos a la API de odontologos con el método GET
    //nos devolverá un JSON con una colección de odontologos
    const url = "/odontologos";
    const settings = {
      method: "GET",
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        //recorremos la colección de odontologos del JSON
        for (odontologo of data) {
          //por cada odontologo armaremos una fila de la tabla
          //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la odontologo
          var tableBody = document.getElementById("tableDentist").getElementsByTagName('tbody')[0];
          var odontologoRow = tableBody.insertRow(tableBody.rows.length);
          let tr_id = "tr_" + odontologo.id;
          odontologoRow.id = tr_id;

          let editIcon =
            '<a href="#editDentistModal" class="edit" data-toggle="modal">' +
            '<i class="material-icons"' +
            " id=" +
            '"editIcon_' +
            odontologo.id +
            '" ' +
            'onclick="findBy(' + odontologo.id +')"' +
            'data-toggle="tooltip" title="Edit">&#xE254;</i></a>';

          let deleteIcon =
          '<a href="#deleteDentistModal" class="delete" data-toggle="modal">' +
          '<i class="material-icons"' +
          " id=" +
          '"deleteIcon_' +
          odontologo.id +
          '" ' +
          'onclick="deleteFindBy(' + odontologo.id +')"' +
          'data-toggle="tooltip" title="Delete">&#xE872;</i></a>';

          //armamos cada columna de la fila
          //como primer columna pondremos el boton modificar
          //luego los datos de la odontologo
          //como ultima columna el boton eliminar
          odontologoRow.innerHTML =
              "<td>" +
              odontologo.id +
              "</td>" +
              '<td class="td_nombre">' +
              odontologo.nombre.toUpperCase() +
              "</td>" +
              '<td class="td_apellido">' +
              odontologo.apellido.toUpperCase() +
              "</td>" +
              '<td class="td_matricula">' +
              odontologo.matricula +
              "</td>" +
              "<td>" +
              editIcon +
              deleteIcon +
              "</td>";
        }
      });
  })(function () {
    let pathname = window.location.pathname;
    if (pathname == "/administrar-odontologo.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  });
});

function GoToHomePage()
{
    window.location.href = 'http://localhost:8080/index.html';
    window.location.pathname = '/index.html'
}