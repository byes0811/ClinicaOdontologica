window.addEventListener("load", function () {

const formulario = document.querySelector("#deleteDentistModal");

formulario.addEventListener("submit", function (event) {

  let id = document.querySelector("#delete_dentist_id").value;

  //con fetch invocamos a la API de odontologos con el método DELETE
  //pasandole el id en la URL
  const url = "/odontologos/eliminar/" + id;
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
})

//Es la funcion que se invoca cuando se hace click sobre el icono para editar un registro del listado
//se encarga de llenar el formulario con los datos del odontologo que se desea modificar
function deleteFindBy(id) {
  const url = "/odontologos/" + id;
  const settings = {
    method: "GET",
  };
  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let odontologo = data;
      document.querySelector("#delete_dentist_id").value = odontologo.id;
      document.querySelector("#delete_name").value = odontologo.nombre;
      document.querySelector("#delete_lastname").value = odontologo.apellido;
      document.querySelector("#delete_registry").value = odontologo.matricula;
    })
    .catch((error) => {
      alert("Error: " + error);
    });
}