import { Injectable } from '@angular/core';
import { ProductoI } from '../interface/producto-i.interface';
import { CategoriasI } from '../interface/categorias-i.interface';
import { CATEGORIAS } from '../database/categorias.db';


@Injectable({
  providedIn: 'root',
})
export class ProductoSService {


  arrPorductos: ProductoI[];
  arrCategorias: CategoriasI[];

  private id: number;


  
  constructor() {
    this.arrPorductos = [];
    this.arrCategorias = CATEGORIAS;


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

  getAllCategorias(){
    return this.arrCategorias;
  }



  //!!!!!!!!!!!!
  //coger producto por id
  getById(miId: number): ProductoI | undefined{
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
        categoria: ""
      }
    }
    return productos;
  }


  getProdByCategorias(categorias: string): ProductoI[] {
    return this.arrPorductos.filter(prod => prod.categoria.includes(categorias));
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

  //!actualizar producto

  updatePorducto(producto: ProductoI): void {
    //buscamos en le array por id no por si es distinto de null como antes
    let i = this.arrPorductos.findIndex(prod => prod.id === producto.id);

    //si no encuaentra el porducto en el array devuelve -1, pero si lo encuantra reemplaza el porducto por uno nuevo
    if (i !== -1) {
      this.arrPorductos[i] = producto;
    }

    

  }


}


