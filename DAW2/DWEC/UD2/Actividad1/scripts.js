import { data } from "./js/data.js";
import Carrito from "./js/Carrito.js";

const carrito = new Carrito();

data.products.forEach(producto => {
    carrito.productos.push({ ...producto, unidades: 0 }); // agregamos unidades iniciales
});

document.addEventListener("DOMContentLoaded", function () {


    const bodyProduct = document.getElementById("bodyProduct");
    const totalCarrito = document.getElementById("totalFinal");

    bodyProduct.innerHTML = data.products.map(function (x) {

        return `<tr>
            <th scope="row">
                <h3>${x.title}</h3>
                <p>${x.SKU}</p>
            </th>
            <td>
                <button class="border-0">-</button>
                <input type="text" value="${x.unidades || 0}" readonly>
                <button class="border-0">+</button>
            </td>
            <td>${x.price}€</td>
            <td class="price-total-prod">0€</td>
        </tr>`;
    }).join('');




    //asignar eventos despues de crear el HTML

      
});


