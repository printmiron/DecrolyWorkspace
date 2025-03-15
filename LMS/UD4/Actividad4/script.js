




// Modo oscuro
const modoBtn = document.getElementById("modoBtn");
modoBtn.addEventListener("click", () => {
    document.body.classList.toggle("dark-mode");
    modoBtn.textContent = document.body.classList.contains("dark-mode") ? "Claro" : "Oscuro";
});





// Mensaje emergente de bienvenida
window.addEventListener("load", () => {
    const popup = document.getElementById("welcome-popup");
    popup.style.display = "block";
    setTimeout(() => {
        popup.style.display = "none";
    }, 3000);
});









// Actualizar carrito y calcular total
// Lista de productos en el carrito
const carrito = [];

// Seleccionar todos los botones "Agregar al carrito"
document.querySelectorAll(".agregar-carrito").forEach(boton => {
    boton.addEventListener("click", (e) => {
        const producto = e.target.closest(".producto");
        const nombre = producto.querySelector("h3").textContent;
        const precioTexto = producto.querySelector("p strong").textContent;
        const precio = parseFloat(precioTexto.replace("$", ""));
        const imagen = producto.querySelector("img").src;

        agregarAlCarrito(nombre, precio, imagen);
    });
});

// Funcion para agregar productos al carrito
function agregarAlCarrito(nombre, precio, imagen) {
    carrito.push({ nombre, precio, imagen });
    actualizarCarrito();
}

// Funcion para actualizar el carrito y calcular el total
function actualizarCarrito() {
    const listaCarrito = document.getElementById("lista-carrito");
    const total = document.getElementById("total");
    listaCarrito.innerHTML = "";  
    let sumaTotal = 0;

    carrito.forEach((producto, index) => {
        const item = document.createElement("div");
        item.classList.add("carrito-item");
        item.innerHTML = `
            <img src="${producto.imagen}" width="150" height="150">
            <p>${producto.nombre}</p>
            <p>$${producto.precio.toFixed(2)}</p>
            <button class="eliminar-producto" data-index="${index}">❌</button>
        `;
        listaCarrito.appendChild(item);
        sumaTotal += producto.precio;
    });

    total.textContent = `$${sumaTotal.toFixed(2)}`;

    // Agregar evento a los botones de eliminar
    document.querySelectorAll(".eliminar-producto").forEach(boton => {
        boton.addEventListener("click", (e) => {
            const index = e.target.dataset.index;
            carrito.splice(index, 1);
            actualizarCarrito();
        });
    });
}







// Llamar a la funcion cuando un boton es presionado:
document.querySelectorAll(".agregar-carrito").forEach(boton => {
    boton.addEventListener("click", (e) => {
        const producto = e.target.closest(".producto");
        const nombre = producto.querySelector("h3").textContent;
        const precio = parseFloat(producto.querySelector(".precio").textContent.replace("$", ""));
        const imagen = producto.querySelector("img").src;
        agregarAlCarrito(nombre, precio, imagen);
    });
});







// Contador regresivo para promociones
function iniciarContador(duracion) {
    let tiempoRestante = duracion;
    const contador = document.getElementById("contador");
    
    const intervalo = setInterval(() => {
        const horas = Math.floor(tiempoRestante / 3600);
        const minutos = Math.floor((tiempoRestante % 3600) / 60);
        const segundos = tiempoRestante % 60;

        contador.textContent = `${horas.toString().padStart(2, "0")}:${minutos.toString().padStart(2, "0")}:${segundos.toString().padStart(2, "0")}`;
        tiempoRestante--;

        if (tiempoRestante < 0) {
            clearInterval(intervalo);
            contador.textContent = "Oferta terminada";
        }
    }, 1000);
}
iniciarContador(3600);




// Monstrar suave las secciones
document.addEventListener("DOMContentLoaded", () => {
    const elementos = document.querySelectorAll(".animado");

    const mostrarElementos = () => {
        elementos.forEach(el => {
            const posicion = el.getBoundingClientRect().top;
            const alturaPantalla = window.innerHeight * 0.9;

            if (posicion < alturaPantalla) {
                el.classList.add("visible");
            }
        });
    };

    window.addEventListener("scroll", mostrarElementos);
    mostrarElementos();
});





// Funcion para mostrar secciones con animacion al hacer scroll
function mostrarSecciones() {
    const secciones = document.querySelectorAll("section");
    secciones.forEach(seccion => {
        const posicion = seccion.getBoundingClientRect().top;
        const alturaPantalla = window.innerHeight * 0.85;

        if (posicion < alturaPantalla) {
            seccion.classList.add("visible");
        }
    });
}

// Evento para detectar el scroll y activar la animacion
window.addEventListener("scroll", mostrarSecciones);
document.addEventListener("DOMContentLoaded", mostrarSecciones);







// Agregar efecto al botn de agregar al carrito
const botonesCarrito = document.querySelectorAll(".agregar-carrito");
botonesCarrito.forEach(boton => {
    boton.addEventListener("click", (e) => {
        e.target.classList.add("boton-agregado");
        setTimeout(() => e.target.classList.remove("boton-agregado"), 400);
    });
});







// //Cambio de tamano de texto al pasar el mouse
// document.querySelectorAll("h2, p").forEach(elemento => {
//     elemento.addEventListener("mouseover", () => {
//         elemento.style.fontSize = "5vh";
//     });
//     elemento.addEventListener("mouseout", () => {
//         elemento.style.fontSize = "";
//     });
// });
