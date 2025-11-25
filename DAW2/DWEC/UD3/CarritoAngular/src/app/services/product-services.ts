import { Injectable } from '@angular/core';
import { PoroductInterface } from '../interface/poroduct-interface'
import { BehaviorSubject } from 'rxjs';




@Injectable({
  providedIn: 'root',
})

export class ProductServices {


  //!todos los comentarios son mas para mi, para poder despues orientarme como todo funciona, y como utilizarlo luego!


  //----------------------PEDIR DATOS------------------------
  //lo que hago aqui es acceder directamente al array de porductos, asi como tenemos el currency, tenemos que especializar "["products"]"
  //lo de cantidad es añadir una propriedad mas al pedir los productos ademas de sku, title y precio nosotros aqui añadimos cantidad para poder
  //luego utilizarle en componentes
  private productos: (PoroductInterface["products"][number] & { cantidad: number }) [];
  private currency: PoroductInterface["currency"] = ""; 

  //!uso el Behavior porque si lo hago con input y out put para mandar o intercambiar datos desde el card hasta el total tengo que utilizar el list
  //!como componenete intermedio esto me crea un error de que tengo dos cont-totales, uno en la lista y otro fuera, el de la lista funciona pero el fuera no.
  //!llevo tres dias intendando de alguna manera madar o avisar el total de card hasta total-componente, lo unico como me funciono es este metodo, aun no lo hemos dado pero
  //!entendi como funciona

  //es un objeto "carrito$" que avisa cunado algo cambia, al principio es un "undefined" porque no enviamos nada si no solo avisamos
  //basicamente todos los componentes que quieramos le podemos suscribir a este objeto para que lo escuha
  
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

          //"...producto" ayuda para copiar el objeto que nos viene de json en uno nuevo, para poder añadir ademas de lo que viene de back
          //lo de convertir el precio y añadir cantidad como 0 -> cont copia = {...producto} es mas para no hacer el push de cada elemento de un producto
          //!sku: producto.sku... etc.
          ...producto,

          //convertimos el string en numero para hacer los calculos
          price: Number(producto.price),

          cantidad: 0
        });
        
      });
      //notificamos que los productos y currency son listos
      //avisamos a todos los componentes que estan suscribidos/escuhan
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
    //buscamos el producto que esta en la lista por "sku"
    const index = this.productos.findIndex(prod => prod.sku === product.sku);

    //!si no encuentra se sale
    if (index === -1) return;

    //!si encuentra -> actualizar cantidad
    this.productos[index].cantidad = cantidad;

    //avisamos a todos los componentes que estan suscribidos/escuhando
    this.carrito$.next();
    
  }


}

