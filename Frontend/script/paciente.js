// Path de API 
const url = "http://localhost:8080/ConsultorioOnline/app/pacientes"
// const url = "https://minticloud.uis.edu.co/c3s56formador/api/clientes"

// Tomar la tabla/datos del tbody
const contenedor = document.querySelector('tbody')

// Para almacenar datos que llegan de la BD
let resultados = ''

// Variables usando el DOM
    // Ventana modal -la que se abre sobre la página para agregar o 
    // modificar
const modalPacientes = new bootstrap.Modal(document.getElementById('modalPaciente'))
    // Todo el fomulario
const formPacientes = document.querySelector('form')
const idPaciente = document.getElementById('id')
const nombrePaciente = document.getElementById('nombre')
const apellidoPaciente = document.getElementById('apellido')
const emailPaciente = document.getElementById('email')
const tipoDocPaciente = document.getElementById('tipoDocumento')
const fechaPaciente = document.getElementById('fechaNacimiento')
const direccionPaciente = document.getElementById('direccion')
const ciudadPaciente = document.getElementById('ciudad')
const sexoPaciente = document.getElementById('sexo')
const tel1Paciente = document.getElementById('tel1')
const tel2Paciente = document.getElementById('tel2')

// Variable para la acción Guardar o Actualizar
let opcion = ''

// Limpieza de los campos para ingresar nuevo registro
btnCrear.addEventListener('click', () => {
    idPaciente.value = ''
    idPaciente.disabled = false
    nombrePaciente.value = ''
    apellidoPaciente.value = ''
    emailPaciente.value = ''
    tipoDocPaciente.value = ''
    fechaPaciente.value = ''
    direccionPaciente.value = ''
    ciudadPaciente.value = ''
    sexoPaciente.value = ''
    tel1Paciente.value = ''
    tel2Paciente.value = ''
    modalPacientes.show()
    opcion = 'crear'
})

// Este objeto tipo ajax se usará para cada una de la operaciones CRUD
const ajax = (options) => {
    // Los parametor que recibirá el objeto para las operaciones CRUD
    let { url, method, success, error, data } = options;
    const xhr = new XMLHttpRequest();
    
    xhr.addEventListener("readystatechange", (e) => {
        if(xhr.readyState !== 4) return;
        
        if(xhr.status >= 200 && xhr.status < 300) {
            let json = JSON.parse(xhr.responseText);
            success(json);
        } else {
            let message = xhr.statusText || "Ocurrión un error";
            error(`Error ${xhr.status}: ${message}`);
        }
    })

    xhr.open(method || "GET", url);
    //xhr.timeout = 10000;
    xhr.setRequestHeader("Accept", "*/*")
    xhr.setRequestHeader("Content-type", "application/json")
    xhr.setRequestHeader("Access-Control-Allow-Origin", "*")
    if (method == "GET") {
        xhr.send();
    }else{
        xhr.send(JSON.stringify(data));
    }
    
};

// GET
const getAll = () => {
    // Petición usando el ajax
    ajax({
        url: url,
        method: "GET",
        crossDomain: true,
        // La información retornada es almacenada en la variable res
        success: (res) => {
            console.log(res);
            // Elaboración de tabla por cada registro
            res.forEach((pacientes) => {
                resultados += `
                <tr>
                    <td width="15%">${pacientes.id}</td>
                    <td width="15%">${pacientes.nombre}</td>
                    <td width="15%">${pacientes.apellido}</td>
                    <td width="20%">${pacientes.email}</td>
                    <td width="10%">${pacientes.tipo_documento}</td>
                    <td width="15%">${pacientes.fecha_nacimiento}</td>
                    <td width="25%">${pacientes.direccion}</td>
                    <td width="15%">${pacientes.ciudad}</td>
                    <td width="10%">${pacientes.sexo}</td>
                    <td width="15%">${pacientes.tel1}</td>
                    <td width="15%">${pacientes.tel2}</td>
                    <td class="text-center" width="20%">
                        <a class="btnEditar btn btn-primary">Editar</a>
                        <a class="btnBorrar btn btn-danger">Borrar</a>
                    </td>
                </tr>
                `
            });
            // Envío de los datos al tbody
            contenedor.innerHTML = resultados
        },
        error: (err) => {
            console.log(err);
            document.getElementById("pedidos").insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`);
        },
    });
};

// Llamado al método encargado del GET para que los datos se muestren 
// al cargar la página
document.addEventListener("DOMContentLoaded", getAll);

// Cada registro tiene un boton para Editar y Borrar. Aquí el script 
// de los botones
document.addEventListener("click", (e) => {
    if (e.target.matches(".btnBorrar")) {
        // Selección de la fila asociada al botón -borrar-
        const fila = e.target.parentNode.parentNode
        const id = fila.firstElementChild.innerHTML
        console.log(id)
        alertify.confirm(
            `¿Está seguro de eliminar el id ${id}?`, 
            function () {
                ajax({
                    url:url + "/" + id,
                    method: "DELETE",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    success: (res) => location.reload(),
                    error: (err) => alert(err),
                });
                alertify.success('Registro eliminado')
            },
            function () {
                alertify.error('Cancel')
            }
        );
    }

    if (e.target.matches(".btnEditar")) {
        // Selección de la fila asociada al botón -editar-
        const fila = e.target.parentNode.parentNode
        // Carga los valores existentes, para proceder a modificarlos
        idPaciente.value = fila.children[0].innerHTML        
        apellidoPaciente.value = fila.children[1].innerHTML        
        emailPaciente.value = fila.children[2].innerHTML        
        tipoDocPaciente.value = fila.children[3].innerHTML        
        fechaPaciente.value = fila.children[4].innerHTML        
        direccionPaciente.value = fila.children[5].innerHTML        
        ciudadPaciente.value = fila.children[6].innerHTML        
        sexoPaciente.value = fila.children[7].innerHTML        
        tel1Paciente.value = fila.children[8].innerHTML        
        tel2Paciente.value = fila.children[9].innerHTML   
        // id no se puede modificar     
        idPaciente.disabled = true
        opcion = 'editar'
        modalPacientes.show()
    }
})

formPacientes.addEventListener('submit', (e) => {
    e.preventDefault()
    let metodo = "POST"
    if (opcion == 'editar') { metodo = "PUT"}
    ajax({
        url: url,
        method: metodo,
        headers: { 'Content-Type': 'application/json'},
        success: (res) => location.reload(),
        error: (err) => $form.insertAdjacentHTML(
            "afterend", `<p><b>${err}</b><p>`
        ),
        data: {
        "id": idPaciente.value,
        "email": emailPaciente.value,
        "nombre": nombrePaciente.value,
        "apellido": apellidoPaciente.value,
        "tipo_documento": tipoDocPaciente.value,
        "fecha_nacimiento": fechaPaciente.value,
        "activo": 1,
        "direccion": direccionPaciente.value,
        "ciudad": ciudadPaciente.value,
        "tel1": tel1Paciente.value,
        "tel2": tel2Paciente.value,
        "sexo": sexoPaciente.value,
        }
    });
    modalPacientes.hide()
})