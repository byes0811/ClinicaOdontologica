window.addEventListener("load", function () {
  const formulario = document.querySelector("#deleteTurnModal");

  formulario.addEventListener("submit", function (event) {
    let turno_id = document.querySelector("#delete_turn_id").value;

    //con fetch invocamos a la API de odontologos con el método DELETE
    //pasandole el id en la URL
    const params = new URLSearchParams({
      id: turno_id,
    });

    const url = `/turnos/eliminar?${params.toString()}`;
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
  const url = "/turnos/" + id;
  const settings = {
    method: "GET",
  };
  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let turno = data;
      document.querySelector("#delete_turn_id").value = turno.id;
      document.querySelector("#delete_fechaturno").value = turno.fechaCita;
    })
    .catch((error) => {
      alert("Error: " + error);
    });
}