import { Injectable } from '@angular/core';
import { PoroductInterface } from '../interface/poroduct-interface'




@Injectable({
  providedIn: 'root',
})

export class ProductServices {

  //----------------------PEDIR DATOS------------------------
  //lo que hago aqui es acceder directamente al array de porductos asi como tenemos el currency tenemos que especializar
  private productos: PoroductInterface["products"] = [];
  private currency: PoroductInterface["currency"] = "";

  constructor() {
    
    //pedimos desdel api los productos y lo rellenamos en array | la moneda tambien
    fetch('http://localhost:8080/api/carrito')
    .then(respuesta => respuesta.json())
    .then((data: PoroductInterface) => {
      //cojemos la moneda desde api y lo guardamos
      this.currency = data.currency

      data.products.forEach(producto => {
        this.productos.push(producto);
      });

    });

  }

  
  //----------------------OBTENER DATOS------------------------
  //obtener productos
  getAllProd(): PoroductInterface["products"] {
    return this.productos;
  }

  //obtener modena
  getCurrency(): string {
    return this.currency;
  }

  //!metodo para calcular el total del carrito utilizando el total que hemos hecho en card-component
  getTotalCarrito(){
    return this.productos.reduce((total, producto) => total + producto.price, 0)
  }



}

