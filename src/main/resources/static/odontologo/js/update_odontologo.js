window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario quiere modificar para un odontologo en particular
    const formulario = document.querySelector('#editDentistModal');

    formulario.addEventListener('submit', function (event) {
        let odontologoId = document.querySelector('#dentist_id').value;

        //creamos un JSON que tendrá los datos del odontologo.
        //A diferencia de registrar un odontologo nuevo, en este caso enviamos el id
        //para poder identificarlo y modificarlo, para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#dentist_id').value,
            nombre: document.querySelector('#edit_name').value,
            apellido: document.querySelector('#edit_lastname').value,
            matricula: document.querySelector('#edit_registry').value,

        };

        //invocamos utilizando la función fetch la API odontologos con el método PUT que modificará
        //la informacion del odontologo que enviamos en formato JSON
        const url = '/odontologos/actualizar';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })

 })

    //Es la funcion que se invoca cuando se hace click sobre el icono para editar un registro del listado
    //se encarga de llenar el formulario con los datos del odontologo que se desea modificar
    function findBy(id) {
          const url = '/odontologos/' + id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let odontologo = data;
              document.querySelector('#dentist_id').value = odontologo.id;
              document.querySelector('#edit_name').value = odontologo.nombre;
              document.querySelector('#edit_lastname').value = odontologo.apellido;
              document.querySelector('#edit_registry').value = odontologo.matricula;
          }).catch(error => {
              alert("Error: " + error);
          })
      }