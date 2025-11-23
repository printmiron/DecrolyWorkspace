import { Injectable } from '@angular/core';
import { PoroductInterface } from '../interface/poroduct-interface'
import { BehaviorSubject } from 'rxjs';




@Injectable({
  providedIn: 'root',
})

export class ProductServices {





  //----------------------PEDIR DATOS------------------------
  //lo que hago aqui es acceder directamente al array de porductos asi como tenemos el currency tenemos que especializar
  //lo de cantidad es añadir una propriedad mas al pedir los productos ademas de sku, title y precio nosotros aqui añadimos cantidad para poder
  //ñiego utilizarle en total-componente
  private productos: (PoroductInterface["products"][number] & { cantidad: number }) [];
  private currency: PoroductInterface["currency"] = ""; 

  //!!!!!!!!!!!!!
  carrito$ = new BehaviorSubject<void>(undefined);



  constructor() {
    
    this.productos = [];

    //pedimos desdel api los productos y lo rellenamos en array | la moneda tambien
    fetch('http://localhost:8080/api/carrito')
    .then(respuesta => respuesta.json())
    .then((data: PoroductInterface) => {
      //cojemos la moneda desde api y lo guardamos
      this.currency = data.currency

      data.products.forEach(producto => {
        //como hemos añadido la propriedad cantidad tenemos que poner que por defecto es 0
        this.productos.push({
          ...producto,

          price: Number(producto.price),
          cantidad: 0
        });
        
      });
      //notificamos que los productos y currency son listos
      //!!!!!!!!!!!!!
      this.carrito$.next();
    });


    

  }

  






  //----------------------OBTENER DATOS GETTERS------------------------

  // obtener productos dentro del carrito, solo los que tienen cantidad mayor que 0
  getProdCarrito(){
    return this.productos.filter(prod => prod.cantidad > 0)
  }

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
    return this.productos
    .filter(prod => prod.cantidad > 0)
    .reduce((total, producto) => total + producto.price * producto.cantidad, 0)
  }







  //----------------------ACTUALIZAR PRODUCTO------------------------
  //!!
  //añadir/actualizar producto con cantidad
  actualizarPorducto(product: any, cantidad: number){
    const index = this.productos.findIndex(prod => prod.sku === product.sku);

    if (index === -1) return;

    //actualizar cantidad
    this.productos[index].cantidad = cantidad;
    //!!!!!!!!!!!!!
    this.carrito$.next();
    
  }


}

