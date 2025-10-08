import Carrito from "./Carrito.js";

document.addEventListener("DOMContentLoaded", function () {

    const bodyProduct = document.getElementById("bodyProduct");//contendor donde cramos las filas de los productos
    const totalPrice = document.getElementById("totalFinal");//span del total de todos los productos
    const headTotal = document.getElementById("headTotal");//contenedor donde aparecen todos los productos añadidos 

    const carrito = new Carrito();
    let currency = "€"; //valor por defecto

    async function obtenerProductos() {
        try {
            const respuesta = await fetch("./data.json"); //hacer o ralizar solicitudas al api
            const data = await respuesta.json();

            //guardar moneda
            currency = data.currency;

            //
            carrito.productos = data.productos.map(p => ({
                ...p,
                price: parseFloat(p.price), //convertir el precio en numero porque se trae como texto
                unidades: 0 //como no se trae la cantidad de los unidades los definimos como "0"
            }));

            //creamos las filas de la tabla para los productos traidos
            carrito.productos.forEach(prod => crearFilaProducto(prod));

        } catch (error) {
            console.log("Error al obtener los productos: ", error);
        }
    }

    //crear fila producto
    function crearFilaProducto(producto) {


        //=========== parte izquierda =============
        const tr = document.createElement("tr");

        // columna 1
        const th = document.createElement("th");
        th.scope = "row";

        const title = document.createElement("h3");
        title.textContent = producto.title;

        const sku = document.createElement("p");
        sku.textContent = `SKU: ${producto.SKU}`;

        th.append(title);
        th.append(sku);

        tr.append(th);

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

        tdCantidad.append(btnMenos);
        tdCantidad.append(input);
        tdCantidad.append(btnMas);

        tr.append(tdCantidad);

        // columna 3 
        const tdPrecio = document.createElement("td");
        tdPrecio.textContent = producto.price + currency;
        tr.append(tdPrecio);

        // columna 4 
        const tdTotal = document.createElement("td");
        tdTotal.classList.add("price-total-prod");
        tdTotal.textContent = "0" + currency;

        tr.append(tdTotal);


        // agregar fila al tbody
        bodyProduct.append(tr);








        // =========== parte derecha ===========
        const trD = document.createElement("tr");

        // columna 1
        const titleD = document.createElement("p");
        titleD.textContent = producto.title;

        trD.append(titleD);

        // columna 2
        const tdTotalD = document.createElement("td");
        tdTotalD.classList.add("price-total-prod");
        
        trD.append(tdTotalD);

        // agregar fila al thead
        headTotal.append(trD);




        

        //actualizar los totales individuales de cada producto y de todos los productos juntos
        function actualizarTotales() {

            const totalProd = producto.unidades * producto.price;

            //total de cada producto
            tdTotal.textContent = totalProd.toFixed(2) + currency;
            tdTotalD.textContent = totalProd.toFixed(2) + currency;

            //total de todos los productos
            const total = carrito.obtenerCarrito().reduce(
                (acc, prod) => acc + (prod.unidades * prod.price),
                0
            );

            totalPrice.textContent = total.toFixed(2) + currency;



        }









        // eventos botones
        btnMas.addEventListener("click", () => {
            producto.unidades++;
            input.value = producto.unidades;
            carrito.actualizarUnidades(producto.SKU, producto.unidades);
            actualizarTotales();


        });

        btnMenos.addEventListener("click", () => {
            if (producto.unidades > 0) {
                producto.unidades--;
                input.value = producto.unidades;
                carrito.actualizarUnidades(producto.SKU, producto.unidades);
                actualizarTotales();

            }
        });









    }









    //cargamos los productos   
    obtenerProductos();

});



