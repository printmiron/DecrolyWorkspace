import Carrito from "./js/Carrito.js";

const productos = [
    { SKU: "1", title: "Producto 1", price: 10.00, unidades: 0 },
    { SKU: "2", title: "Producto 2", price: 20.00, unidades: 0 },
    { SKU: "3", title: "Producto 3", price: 30.00, unidades: 0 },
];

document.addEventListener("DOMContentLoaded", function () {

    const bodyProduct = document.getElementById("bodyProduct");
    const totalPrice = document.getElementById("totalFinal");

    //crear fila producto
    function crearFilaProducto(producto) {
        const tr = document.createElement("tr");

        // columna 1
        const th = document.createElement("th");
        th.scope = "row";
        const title = document.createElement("h3");
        title.textContent = producto.title;
        const sku = document.createElement("p");
        sku.textContent = producto.SKU;

        th.appendChild(title);
        th.appendChild(sku);

        tr.appendChild(th);

        // columna 2 
        const tdCantidad = document.createElement("td");
        const btnMenos = document.createElement("button");
        btnMenos.textContent = "-";
        btnMenos.className = "border-0 menos-cant";

        const input = document.createElement("input");
        input.type = "text";
        input.value = producto.unidades;
        input.readOnly = true;
        input.className = "cantidad";

        const btnMas = document.createElement("button");
        btnMas.textContent = "+";
        btnMas.className = "border-0 mas-cant";

        tdCantidad.appendChild(btnMenos);
        tdCantidad.appendChild(input);
        tdCantidad.appendChild(btnMas);

        tr.appendChild(tdCantidad);

        // columna 3 
        const tdPrecio = document.createElement("td");
        tdPrecio.textContent = producto.price + "€";
        tr.appendChild(tdPrecio);

        // columna 4 
        const tdTotal = document.createElement("td");
        tdTotal.classList.add("price-total-prod");
        tdTotal.textContent = "0€";

        tr.appendChild(tdTotal);


        // agregar fila al tbody
        bodyProduct.appendChild(tr);


        // eventos botones
        btnMas.addEventListener("click", () => {
            producto.unidades++;
            input.value = producto.unidades;
            tdTotal.textContent = (producto.unidades * producto.price).toFixed(2) + "€";
            totalPrice.textContent = productos.reduce((acc, prod) => acc + (prod.unidades * prod.price), 0).toFixed(2) + "€";

        });

        btnMenos.addEventListener("click", () => {
            if (producto.unidades > 0) {
                producto.unidades--;
                input.value = producto.unidades;
                tdTotal.textContent = (producto.unidades * producto.price).toFixed(2) + "€";
                totalPrice.textContent = productos.reduce((acc, prod) => acc + (prod.unidades * prod.price), 0).toFixed(2) + "€";
            }
        });


    }


    



    productos.forEach(prod => crearFilaProducto(prod));




});



