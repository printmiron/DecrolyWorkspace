import {data} from "js/data.js";

export default class Carrito{
    constructor(productos){
           this.productos = [];
    }


    actualizarUnidades(SKU, unidades){
        const producto = this.productos.find(p => p.SKU === SKU);

        if(producto){
            producto.unidades = unidades; //actualiza unidades
        }else{
            alert("El producto no existe en el carrito");
        }
    }

    obtenerInfoProducto(SKU){
        return this.productos.find(p => p.SKU === SKU); //devuelve el producto/info
    }

    obtenerCarrito(){
        return this.productos; //devuelve el array del carrito
    }
}