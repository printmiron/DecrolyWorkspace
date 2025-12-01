import { Injectable } from '@angular/core';
import { ProductoI } from '../interface/producto.interface';
import { CategoriasI } from '../interface/categorias.interface';
import { CATEGORIAS } from '../database/categorias.db';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  arrProductos: ProductoI[];
  arrCategorias: CategoriasI[];
  private id: number;


  constructor() {
    this.id = 10;
    this.arrProductos = [];
    this.arrCategorias = CATEGORIAS;

    //pedimos desdel api los productos y lo rellenamos en array
    fetch('http://localhost:8080/api/products')
      .then(respuesta => respuesta.json())
      .then(data => {
        data.forEach((element: any) => {
          let producto = element as ProductoI;
          this.arrProductos.push(producto);
        });
      });


  }

  //meter todos los productos al array
  getAllProductos() {
    return this.arrProductos;
  }

  getAllCategorias() {
    return this.arrCategorias;
  }

  //---------------------------BUSCAR PRODUCTO

  getByCategorias(categorias: string): ProductoI[] {
    return this.arrProductos.filter(prod => prod.category.includes(categorias));
  }

  //---------------------------BUSCAR PRODUCTO POR ID

  getById(miId: number): ProductoI | undefined {
    let productos: ProductoI;

    let respuesta = this.arrProductos.find(prod => prod.id == miId);
    if (respuesta != undefined) {
      productos = respuesta
    } else {
      productos = {
        name: "Producto no encontrado",
        description: "",
        price: "",
        category: "",
        image: "",
      
      }
    }
    return productos;
  }

  //---------------------------AÑADIR PRODUCTO

  addPorduct(producto: ProductoI) {
    if (!this.arrProductos.includes(producto)) {

      producto.id = this.id;
      this.arrProductos.push(producto);
      this.id++;
    }
  }

  //---------------------------ACTUALIZAR PRODUCTO

  updateProducto(producto: ProductoI): void {
    //buscamos en el array por id. no por si es distinto de null
    let i = this.arrProductos.findIndex(prod => prod.id === producto.id);

    //si no encuantra el porducto el array devuelve un -1, pero si lo encuantra lo reemplaza por uno nuevo, en la misma position
    if (i !== -1) {
      this.arrProductos[i] = producto;
    }

  }



  //---------------------------ELIMINAR PRODUCTO
  //borrar el producto
  removeProductoById(id: number): void {
    let i = this.arrProductos.findIndex(producto => producto.id == id);

    if (i != -1 && i >= 0 && i < this.arrProductos.length) {
      this.arrProductos.splice(i, 1);
    }
  }








}
