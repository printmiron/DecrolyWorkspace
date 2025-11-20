import { Injectable } from '@angular/core';
import { PoroductInterface } from '../interface/poroduct-interface'
import { BehaviorSubject, Subject } from 'rxjs';


@Injectable({
  providedIn: 'root',
})

export class ProductServices {

  //!!!!
  //calculamos el total de todo el carrito en servicio y despues lo recojemos en el componente "total"
  //de esta manera es mas facil que mandar datos/info entre componentes
  private totalCarrito = new BehaviorSubject<number>(0);

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
  //!!!
  //obtener productos
  getAllProd(): PoroductInterface["products"] {
    return this.productos;
  }

  //obtener modena
  getCurrency(): string {
    return this.currency;
  }




}

