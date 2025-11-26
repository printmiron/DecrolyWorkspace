import { Injectable } from '@angular/core';
import { ProductoI } from '../interface/producto-i.interface';
import { ProductosDBDatabase } from '../database/productos-db.database';

@Injectable({
  providedIn: 'root',
})
export class ProductoSService {

  arrPorductos: ProductoI[];

  private id: number;

  
  constructor(){
    this.arrPorductos = ProductosDBDatabase;
    this.id = ProductosDBDatabase.length + 1;
  }

  //coger todos los porductos del array
  getAllProductos(){
    return this.arrPorductos;
  }

  //coger producto por id
  getById(id: number){
    return this.arrPorductos.find(prod => prod.id === id);
  }

  //añadir el producto
  addProducto(producto: ProductoI){
    if (!this.arrPorductos.includes(producto)) {
      producto.id = this.id;
      this.arrPorductos.push(producto);
      this.id++;
    }
  }

  //borrar el producto
  removeProductoById(removeProducto: ProductoI){
    this.arrPorductos = this.arrPorductos.filter(prod => prod.id !== removeProducto.id);
  }

}
