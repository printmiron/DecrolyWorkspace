
// Modo oscuro
const modoBtn = document.getElementById("toggle-dark-mode");
modoBtn.addEventListener("click", () => {
    document.body.classList.toggle("dark-mode");
    modoBtn.textContent = document.body.classList.contains("dark-mode") ? "Modo Claro" : " Modo Oscuro";
});

//Carrito para agregar productos
const carrito = [];

// Seleccionar todos los botones "Agregar al carrito"
document.querySelectorAll(".add-to-cart").forEach(boton => {
    boton.addEventListener("click", (e) => {
        const product = e.target.closest("product");
        const nombre = product.querySelector("h3").textContent;
        const precioTexto = product.querySelector("p").textContent;
        const precio = parseFloat(precioTexto.replace("$", ""));

        agregarAlCarrito(nombre, precio);
    });
});

// Funcion para agregar productos al carrito
function agregarAlCarrito(nombre, precio) {
    carrito.push({ nombre, precio});
    actualizarCarrito();
}

function actualizarCarrito() {
    const listaCarrito = document.getElementById("cart-content");
    const total = document.getElementById("total");
    listaCarrito.innerHTML = "";  
    let sumaTotal = 0;

    carrito.forEach((producto, index) => {
        const item = document.createElement("div");
        item.classList.add("carrito-item");
        item.innerHTML = `
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