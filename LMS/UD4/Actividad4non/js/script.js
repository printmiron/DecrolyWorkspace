document.querySelectorAll('nav ul li a').forEach(enlace => {
    enlace.addEventListener('click', function(e) {
        e.preventDefault();
        let destino = document.querySelector(this.getAttribute('href'));
        destino.scrollIntoView({ behavior: 'smooth' });
    });
});

// Seleccionar todos los botones
const botones = document.querySelectorAll("button");

// Agregar efecto de cambio de color al pasar el raton
botones.forEach(boton => {
    boton.addEventListener("mouseover", () => {
        boton.style.backgroundColor = "#d7ccc8"; // Color claro
        boton.style.color = "#333";
    });

    boton.addEventListener("mouseout", () => {
        boton.style.backgroundColor = "#795548"; // Color original
        boton.style.color = "#fff";
    });
});

// Ocultar mensaje de bienvenida después de 3 segundos
setTimeout(() => {
    document.getElementById("bienvenida").style.opacity = "0";
}, 3000);

// Seleccionar todos los títulos de productos
const titulosProductos = document.querySelectorAll(".producto h3");

// Agregar evento para aumentar tamano
titulosProductos.forEach(titulo => {
    titulo.addEventListener("mouseover", () => {
        titulo.style.fontSize = "22px";
    });

    titulo.addEventListener("mouseout", () => {
        titulo.style.fontSize = "18px"; // Tamaño original
    });
});

const botonModo = document.getElementById("modo-btn");
const cuerpo = document.body;
const nav = document.querySelector("nav");

botonModo.addEventListener("click", () => {
    cuerpo.classList.toggle("modo-oscuro");
    nav.classList.toggle("modo-oscuro");
    botonModo.classList.toggle("modo-oscuro");

    if (cuerpo.classList.contains("modo-oscuro")) {
        botonModo.textContent = "☀️";
    } else {
        botonModo.textContent = "🌙";
    }
});


const botonesCarrito = document.querySelectorAll(".agregar-carrito");
const listaCarrito = document.getElementById("lista-carrito");
const totalCarrito = document.getElementById("total");

let total = 0;

botonesCarrito.forEach(boton => {
    boton.addEventListener("click", (e) => {
        const producto = e.target.parentElement;
        const nombre = producto.getAttribute("data-nombre");
        const precio = parseFloat(producto.getAttribute("data-precio"));

        // Crear elemento en el carrito
        const item = document.createElement("li");
        item.textContent = `${nombre} - $${precio.toFixed(2)}`;
        listaCarrito.appendChild(item);

        // Sumar al total
        total += precio;
        totalCarrito.textContent = total.toFixed(2);
    });
});

function iniciarContador(duracion) {
    let tiempoRestante = duracion;
    const contadorElemento = document.getElementById("contador");

    function actualizarContador() {
        let horas = Math.floor(tiempoRestante / 3600);
        let minutos = Math.floor((tiempoRestante % 3600) / 60);
        let segundos = tiempoRestante % 60;

        // Mostrar con formato 00:00:00
        contadorElemento.textContent = 
            `${horas.toString().padStart(2, "0")}:${minutos.toString().padStart(2, "0")}:${segundos.toString().padStart(2, "0")}`;

        if (tiempoRestante > 0) {
            tiempoRestante--;
        } else {
            clearInterval(intervalo);
            contadorElemento.textContent = "¡Oferta Finalizada!";
        }
    }

    actualizarContador();
    const intervalo = setInterval(actualizarContador, 1000);
}

// Iniciar contador con 2 horas de duración (7200 segundos)
iniciarContador(7200);

const secciones = document.querySelectorAll("section");

function mostrarSecciones() {
    secciones.forEach(seccion => {
        const rect = seccion.getBoundingClientRect();
        if (rect.top < window.innerHeight * 0.75) {
            seccion.classList.add("visible");
        }
    });
}

window.addEventListener("scroll", mostrarSecciones);
mostrarSecciones();

botonesCarrito.forEach(boton => {
    boton.addEventListener("click", (e) => {
        e.target.classList.add("boton-agregado");
        setTimeout(() => e.target.classList.remove("boton-agregado"), 400);
    });
});
