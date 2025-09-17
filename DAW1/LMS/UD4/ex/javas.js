// 1. Contador que aumenta y disminuye con botones
let contador = 0;
const contadorDisplay = document.addEventListener("contador");

function actualizarContador(){
    contadorDisplay.textContent = contador;
}

document.getElementById("sumar").addEventListener("click", () => {
    contador++;
    actualizarContador;
});

document.getElementById("quitar").addEventListener("click", () => {
    if (contador > 0) {
        contador--;
    }
    actualizarContador;
});

actualizarContador;










// 2. Sistema de comentarios
document.getElementById("enviarComentario").addEventListener("click", () => {
    const inputComentario = document.getElementById("comentarioInput");
    const comentarioTexto = inputComentario.value.trim();

    if (comentarioTexto !== "") {
        const listaComentarios = document.getElementById("listaComentarios");
        const nuevoComentario = document.createElement("li");
        nuevoComentario.textContent = comentarioTexto;

        // Agregar el comentario a la lista
        listaComentarios.appendChild(nuevoComentario);

        // Limpiar el input después de agregar el comentario
        inputComentario.value = "";
    } else {
        alert("Por favor, escribe un comentario antes de enviarlo.");
    }
});









// 3. Cambio de color de fondo aleatorio
document.getElementById("cambiarColor").addEventListener("click", () => {
    const colorAleatorio = `#${Math.floor(Math.random() * 16777215).toString(16)}`;
    document.body.style.backgroundColor = colorAleatorio;
});





// 4. Reloj digital
function actualizarReloj() {
    const ahora = new Date();
    const horas = ahora.getHours().toString().padStart(2, "0");
    const minutos = ahora.getMinutes().toString().padStart(2, "0");
    const segundos = ahora.getSeconds().toString().padStart(2, "0");

    document.getElementById("reloj").textContent = `${horas}:${minutos}:${segundos}`;
}

// Actualizar cada segundo
setInterval(actualizarReloj, 1000);

// Mostrar la hora inicial
actualizarReloj();








// 5. Simulador de lanzamiento de un dado
document.getElementById("lanzarDado").addEventListener("click", () => {
    const resultado = Math.floor(Math.random() * 6) + 1;
    document.getElementById("resultadoDado").textContent = `🎲 Salió: ${resultado}`;
});
