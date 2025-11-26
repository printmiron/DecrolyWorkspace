import { Injectable } from '@angular/core';
import { ProductoI } from '../interface/producto-i.interface';


@Injectable({
  providedIn: 'root',
})
export class ProductoSService {

  arrPorductos: ProductoI[];

  private id: number;


  constructor() {
    this.arrPorductos = [];
    this.id = 1;
    //!!!!
    //pedimos desdel api los productos y lo rellenamos en array
    fetch('http://localhost:8080/api/productos')
      .then(respuesta => respuesta.json())
      .then(data => {
        data.forEach((element: any) => {
          let producto = element as ProductoI;
          this.arrPorductos.push(producto);
        });
      });

  }

  //coger todos los porductos del array
  getAllProductos() {
    return this.arrPorductos;
  }

  //coger producto por id
  getById(id: number) {
    return this.arrPorductos.find(prod => prod.id === id);
  }

  //añadir el producto
  addProducto(producto: ProductoI) {
    if (!this.arrPorductos.includes(producto)) {
      producto.id = this.id;
      this.arrPorductos.push(producto);
      this.id++;
    }
  }

  //borrar el producto
  removeProductoById(removeProducto: ProductoI) {
    this.arrPorductos = this.arrPorductos.filter(prod => prod.id !== removeProducto.id);
  }

}
