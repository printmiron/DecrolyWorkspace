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
    this.id = 10;
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



  //!!!!!!!!!!!!
  //coger producto por id
  getById(miId: number): ProductoI {
    let productos: ProductoI;
    let respuesta = this.arrPorductos.find(prod => prod.id == miId);
    if (respuesta != undefined) {
      productos = respuesta;
      
    }
    else {
      productos = {
        title: "Producto no encontrado",
        subtitle: "Error",
        descripcion: "",
        image: "",
      }
    }
    return productos;
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
  removeProductoById(id: number): void {
    let i = this.arrPorductos.findIndex(producto => producto.id == id);

    if (i != -1 && i >= 0 && i < this.arrPorductos.length) {
      this.arrPorductos.splice(i, 1);
    }
  }




}


