import {Carrito} from "js/Carrito.js";

const carrito = new Carrito();

document.addEventListener("DOMContentLoaded", function () {


    const bodyProduct = document.getElementById("bodyProduct");
    const totalCarrito = document.getElementById("totalFinal");


    bodyProduct.innerHTML = data.products.map(function(x) {
        
        return `<tr>
            <th scope="row">
                <h3>${x.title}</h3>
                <p>${x.SKU}</p>
            </th>
            <td>
                <button class="border-0">-</button>
                <input type="text" value="${x.unidades}" readonly>
                <button class="border-0">+</button>
            </td>
            <td>${x.price}€</td>
            <td class="price-total-prod">0€</td>
        </tr>`;
    }).join('');

    


        //añadimos funccion a los butones
        butonMas.addEventListener("click", function () {
            producto.unidades++;
            input.value = producto.unidades;
            tdPrecioTotalProd.textContent = (producto.unidades * producto.price).toFixed(2) + "€";
        });


        butonMenos.addEventListener("click", function () {
            if (unidades.value > 0) {
                producto.unidades--;
                input.value = producto.unidades;
                tdPrecioTotalProd.textContent = (producto.unidades / producto.price).toFixed(2) + "€";
            }
        });

        
        const totalFinal = document.getElementById("totalFinal");

       









        

    });



