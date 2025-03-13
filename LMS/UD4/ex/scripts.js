

// Alternar Modo Oscuro
const btnModo = document.getElementById("btnModo");
btnModo.addEventListener("click", () => {
    document.body.classList.toggle("dark");
    btnModo.textContent = document.body.classList.contains("dark") ? "Modo Claro" : "Modo Oscuro";
});

// Bienvenida con mensaje emergente
window.addEventListener("load", () => {
    const mensaje = document.getElementById("mensaje-bienvenida");
    mensaje.style.display = "block";
    setTimeout(() => mensaje.style.display = "none", 3000);
});

// Carrito de compras simple
let carrito = [];
document.querySelectorAll(".add-cart").forEach(boton => {
    boton.addEventListener("click", (e) => {
        const producto = e.target.closest(".producto");
        const nombre = producto.querySelector("h3").textContent;
        const precio = parseFloat(producto.querySelector(".precio").textContent.replace("$", ""));
        agregarAlCarrito(nombre, precio);
    });
});

function agregarAlCarrito(nombre, precio) {
    carrito.push({ nombre, precio });
    actualizarCarrito();
}

function actualizarCarrito() {
    const lista = document.getElementById("lista-carrito");
    lista.innerHTML = "";
    let total = 0;

    carrito.forEach((item, index) => {
        const elemento = document.createElement("div");
        elemento.innerHTML = `${item.nombre} - $${item.precio.toFixed(2)} <button data-index="${index}" class="remove">❌</button>`;
        lista.appendChild(elemento);
        total += item.precio;
    });

    document.getElementById("total").textContent = `$${total.toFixed(2)}`;
    
    document.querySelectorAll(".remove").forEach(boton => {
        boton.addEventListener("click", (e) => {
            const index = e.target.dataset.index;
            carrito.splice(index, 1);
            actualizarCarrito();
        });
    });
}

// Lista de tareas con almacenamiento local
let tareas = JSON.parse(localStorage.getItem("tareas")) || [];
const tareaForm = document.getElementById("tareaForm");
const tareaInput = document.getElementById("tareaInput");
const listaTareas = document.getElementById("listaTareas");

tareaForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const nuevaTarea = tareaInput.value.trim();
    if (nuevaTarea) {
        tareas.push({ texto: nuevaTarea, completa: false });
        localStorage.setItem("tareas", JSON.stringify(tareas));
        renderizarTareas();
        tareaInput.value = "";
    }
});

function renderizarTareas() {
    listaTareas.innerHTML = "";
    tareas.forEach((tarea, index) => {
        const item = document.createElement("li");
        item.innerHTML = `${tarea.texto} <button data-index="${index}" class="completar">${tarea.completa ? "☑" : "🔲"}</button> <button data-index="${index}" class="eliminar">🗑</button>`;
        listaTareas.appendChild(item);
    });

    document.querySelectorAll(".completar").forEach(btn => {
        btn.addEventListener("click", (e) => {
            const index = e.target.dataset.index;
            tareas[index].completa = !tareas[index].completa;
            localStorage.setItem("tareas", JSON.stringify(tareas));
            renderizarTareas();
        });
    });

    document.querySelectorAll(".eliminar").forEach(btn => {
        btn.addEventListener("click", (e) => {
            const index = e.target.dataset.index;
            tareas.splice(index, 1);
            localStorage.setItem("tareas", JSON.stringify(tareas));
            renderizarTareas();
        });
    });
}

renderizarTareas();

// Contador regresivo
function iniciarCuentaRegresiva(segundos) {
    const display = document.getElementById("contador");
    let tiempo = segundos;

    const intervalo = setInterval(() => {
        const min = Math.floor(tiempo / 60);
        const seg = tiempo % 60;
        display.textContent = `${min}:${seg.toString().padStart(2, "0")}`;
        tiempo--;

        if (tiempo < 0) {
            clearInterval(intervalo);
            display.textContent = "Tiempo agotado";
        }
    }, 1000);
}

iniciarCuentaRegresiva(300);

// Efecto en botones de agregar
document.querySelectorAll(".add-cart").forEach(boton => {
    boton.addEventListener("click", (e) => {
        e.target.classList.add("clicked");
        setTimeout(() => e.target.classList.remove("clicked"), 400);
    });
});

// Animaciones al hacer scroll
document.addEventListener("scroll", () => {
    document.querySelectorAll(".animar").forEach(el => {
        if (el.getBoundingClientRect().top < window.innerHeight * 0.9) {
            el.classList.add("visible");
        }
    });
});



















// 1. Reloj en tiempo real
function actualizarReloj() {
    const reloj = document.getElementById("reloj");
    const ahora = new Date();
    const horas = ahora.getHours().toString().padStart(2, "0");
    const minutos = ahora.getMinutes().toString().padStart(2, "0");
    const segundos = ahora.getSeconds().toString().padStart(2, "0");
    reloj.textContent = `${horas}:${minutos}:${segundos}`;
}
setInterval(actualizarReloj, 1000);
actualizarReloj();

// 2. Generador de citas aleatorias
const citas = [
    "La vida es un 10% lo que me ocurre y 90% cómo reacciono a ello.",
    "El éxito es la suma de pequeños esfuerzos repetidos día tras día.",
    "La disciplina es el puente entre metas y logros.",
    "Cree en ti mismo y todo será posible.",
];

document.getElementById("generarCita").addEventListener("click", () => {
    const cita = citas[Math.floor(Math.random() * citas.length)];
    document.getElementById("cita").textContent = `"${cita}"`;
});

// 3. Sistema de notas con almacenamiento local
let notas = JSON.parse(localStorage.getItem("notas")) || [];
const listaNotas = document.getElementById("listaNotas");
const inputNota = document.getElementById("inputNota");

document.getElementById("agregarNota").addEventListener("click", () => {
    const texto = inputNota.value.trim();
    if (texto) {
        notas.push(texto);
        localStorage.setItem("notas", JSON.stringify(notas));
        renderizarNotas();
        inputNota.value = "";
    }
});

function renderizarNotas() {
    listaNotas.innerHTML = "";
    notas.forEach((nota, index) => {
        const item = document.createElement("li");
        item.textContent = nota;
        const btnEliminar = document.createElement("button");
        btnEliminar.textContent = "❌";
        btnEliminar.addEventListener("click", () => {
            notas.splice(index, 1);
            localStorage.setItem("notas", JSON.stringify(notas));
            renderizarNotas();
        });
        item.appendChild(btnEliminar);
        listaNotas.appendChild(item);
    });
}
renderizarNotas();

// 4. Botón para cambiar color aleatorio de fondo
document.getElementById("cambiarColor").addEventListener("click", () => {
    const colores = ["#FF5733", "#33FF57", "#3357FF", "#F3FF33", "#FF33E3"];
    document.body.style.backgroundColor = colores[Math.floor(Math.random() * colores.length)];
});

// 5. Contador de clics con límite
let contador = 0;
const limite = 10;
const contadorDisplay = document.getElementById("contadorClics");

document.getElementById("botonContador").addEventListener("click", () => {
    if (contador < limite) {
        contador++;
        contadorDisplay.textContent = `Clics: ${contador}`;
    } else {
        alert("¡Límite alcanzado!");
    }
});



















// 1. Validación de formulario (evitar envío si los campos están vacíos)
document.getElementById("formulario").addEventListener("submit", function(event) {
    const nombre = document.getElementById("nombre").value.trim();
    const correo = document.getElementById("correo").value.trim();
    
    if (nombre === "" || correo === "") {
        event.preventDefault(); // Evita el envío
        alert("Todos los campos son obligatorios");
    } else {
        alert("Formulario enviado correctamente");
    }
});

// 2. Conversor de temperatura (Celsius a Fahrenheit y viceversa)
document.getElementById("convertirTemp").addEventListener("click", () => {
    const valor = parseFloat(document.getElementById("temperatura").value);
    const tipo = document.getElementById("tipoTemp").value;
    let resultado = 0;

    if (!isNaN(valor)) {
        if (tipo === "celsius") {
            resultado = (valor * 9/5) + 32;
            alert(`${valor}°C equivale a ${resultado.toFixed(2)}°F`);
        } else {
            resultado = (valor - 32) * 5/9;
            alert(`${valor}°F equivale a ${resultado.toFixed(2)}°C`);
        }
    } else {
        alert("Ingresa un número válido");
    }
});

// 3. Contador de palabras en un textarea
document.getElementById("texto").addEventListener("input", () => {
    const texto = document.getElementById("texto").value.trim();
    const palabras = texto === "" ? 0 : texto.split(/\s+/).length;
    document.getElementById("contadorPalabras").textContent = `Palabras: ${palabras}`;
});

// 4. Galería de imágenes con navegación
const imagenes = ["img1.jpg", "img2.jpg", "img3.jpg"];
let indiceActual = 0;
const imgElement = document.getElementById("galeria");

document.getElementById("prev").addEventListener("click", () => {
    indiceActual = (indiceActual === 0) ? imagenes.length - 1 : indiceActual - 1;
    imgElement.src = imagenes[indiceActual];
});

document.getElementById("next").addEventListener("click", () => {
    indiceActual = (indiceActual === imagenes.length - 1) ? 0 : indiceActual + 1;
    imgElement.src = imagenes[indiceActual];
});

// 5. Generador de número aleatorio dentro de un rango
document.getElementById("generarNumero").addEventListener("click", () => {
    const min = parseInt(document.getElementById("min").value);
    const max = parseInt(document.getElementById("max").value);

    if (!isNaN(min) && !isNaN(max) && min < max) {
        const numero = Math.floor(Math.random() * (max - min + 1)) + min;
        document.getElementById("numeroAleatorio").textContent = `Número generado: ${numero}`;
    } else {
        alert("Ingrese un rango válido (mínimo debe ser menor que el máximo)");
    }
});

