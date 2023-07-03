/* -------------------------------------------------------------------------- */
/*                        // LOGICA DE MENU DESPLEGABLE                       */
/* -------------------------------------------------------------------------- */
window.addEventListener('load', function () {
    const formAgregarPaciente = document.getElementById("formAgregarPaciente");
    const spanAgregarPaciente = document.getElementById("spanAgregarPaciente");
    const buttonAgregarPaciente = document.getElementById("buttonAgregarPaciente");

    const formActualizarPaciente = document.getElementById("formActualizarPaciente");
    const spanActualizarPaciente = document.getElementById("spanActualizarPaciente");
    const buttonActualizarPaciente = document.getElementById("buttonActualizarPaciente");


    buttonAgregarPaciente.addEventListener("click", (e)=> {
        spanAgregarPaciente.classList.toggle("transition-transform");
        spanAgregarPaciente.classList.toggle("rotate-90");
        formAgregarPaciente.classList.toggle("transition-transform");
        formAgregarPaciente.classList.toggle("hidden");
        formAgregarPaciente.classList.toggle("top-0"); 
    })


    buttonActualizarPaciente.addEventListener("click", (e)=> {
        console.log(e);
        spanActualizarPaciente.classList.toggle("transition-transform");
        spanActualizarPaciente.classList.toggle("rotate-90");
        formActualizarPaciente.classList.toggle("transition-transform")
        formActualizarPaciente.classList.toggle("hidden");
        formActualizarPaciente.classList.toggle("top-0");
    })


    /* -------------------------------------------------------------------------- */
    /*                           CREAR UN NUEVO PACIENTE                           */
    /* -------------------------------------------------------------------------- */

    const nombrePaciente = document.getElementById("nombrePaciente");
    const apellido = document.getElementById("apellido");
    const dni = document.getElementById("documento");
    const fechaDeAlta = document.getElementById("fechaDeAlta");
    const calle = document.getElementById("calle");
    const localidad = document.getElementById("localidad");
    const provincia = document.getElementById("provincia");


    formAgregarPaciente.addEventListener("submit", (event) => {
        event.preventDefault();
        let payLoad = {
            nombre: nombrePaciente.value,
            apellido: apellido.value,
            dni: dni.value,
            fechaDeAlta: fechaDeAlta.value,
            domicilio: {
                calle: calle.value,
                localidad: localidad.value,
                provincia: provincia.value
            }
        };

        let settings = {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(payLoad)
        };

        fetch("http://localhost:8080/pacientes", settings)
        .then(response => {
            return response.json();
        })
        .then(data => {
            console.log(data);
        })
        .catch(error => {
            return error;
        })

        Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Usuario agregado',
            showConfirmButton: false,
            timer: 1500
        })

        contenedor.innerHTML = "";
        formAgregarPaciente.reset();
    });

    /* -------------------------------------------------------------------------- */
    /*                              MOSTRAR USUARIOS                              */
    /* -------------------------------------------------------------------------- */

    const contenedor = document.getElementById("contenedor-items");
    const templateItems = document.getElementById("items").content;
    const templateNoItems = document.getElementById("no-items").content;
    const buttonMostrarPaciente = document.getElementById("botonMostrarPaciente");
    const fragment = document.createDocumentFragment();

    buttonMostrarPaciente.addEventListener("click", ()=> {
        getPacientes();
    });


    // Funcion que contiene el metodo get para traer todos los usuarios
    function getPacientes(){
        let settings = {
            method: "GET",
            headers: {
                "Content-type": "application/json"
            }
        };

        fetch("http://localhost:8080/pacientes", settings)
        .then(response => {
            return response.json();
        })
        .then(data => {
            imprimirPacientes(data);
            console.log(data)
        })
        .catch(error => {
            return error;
        })
    };

    // Funcion que contiene la logica de imprimir en el html las tarjetas de usuarios que trae la peticion get 
    function imprimirPacientes(data){
        contenedor.innerHTML = "";
        
        let timerInterval
        Swal.fire({
            title: 'Cargando Pacientes',
            html: 'tiempo restante <b></b>',
            timer: 1000,
            timerProgressBar: true,
            didOpen: () => {
                Swal.showLoading()
                const b = Swal.getHtmlContainer().querySelector('b')
                timerInterval = setInterval(() => {
                b.textContent = Swal.getTimerLeft()
                }, 100)
            },
            willClose: () => {
                clearInterval(timerInterval)
            }
            }).then((result) => {
            /* Read more about handling dismissals below */
            if (result.dismiss === Swal.DismissReason.timer) {
                console.log('I was closed by the timer')
            }
        })

        setTimeout(()=> {
            if(data.length === 0){
                templateNoItems.querySelector("p").textContent;
                let clone = templateNoItems.cloneNode(true);
                fragment.appendChild(clone);
            }else{ 
                    data.forEach(user => {
                    console.log(user);
                    templateItems.getElementById("idPaciente").innerHTML = `<p id="idPaciente" class="text-center text-yellow-400"><span class="text-start text-pink-100">ID </span>${user.id}</p>`
                    templateItems.getElementById("nombrePaciente").innerHTML = `<p id="nombrePaciente" class="text-center text-yellow-400"><span class="text-pink-100">NOMBRE </span>${user.nombre}</p>`
                    templateItems.getElementById("apellidoPaciente").innerHTML = `<p id="apellidoPaciente" class="text-center text-yellow-400"><span class="text-pink-100">APELLIDO </span>${user.apellido}</p>`
                    templateItems.getElementById("localidad").innerHTML = `<p id="localidad" class="text-center text-yellow-400"><span class="text-pink-100">LOCALIDAD </span>${user.domicilio.localidad}</p>`
                    templateItems.querySelector(".btn").dataset.id = user.id;
                    let clone = templateItems.cloneNode(true);
                    fragment.appendChild(clone);
                });
            }
            contenedor.appendChild(fragment);
        },1000);

    }
});