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

  arrCarrito: ProductoI[];

  private id: number;



  constructor() {
    this.arrPorductos = [];
    this.arrCategorias = CATEGORIAS;

    this.arrCarrito = [];


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


  getCarrito() {
    return this.arrCarrito;
  }

  //coger todos los porductos del array
  getAllProductos() {
    return this.arrPorductos;
  }

  getAllCategorias() {
    return this.arrCategorias;
  }



  //!!!!!!!!!!!!
  //coger producto por id
  getById(miId: number): ProductoI | undefined {
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
        categoria: "",
        price: ""
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

  addProductoCarrito(producto: ProductoI) {
    const existe = this.arrCarrito.find(p => p.id === producto.id);

    if (!existe) {
      this.arrCarrito.push({
        ...producto,
        cantidad: 1,
        total: Number(producto.price)
      });
    } else {
      existe.cantidad! += 1;
      existe.total = existe.cantidad! * Number(existe.price);
    }
  }


  //borrar el producto
  removeProductoById(id: number): void {
    let i = this.arrPorductos.findIndex(producto => producto.id == id);

    if (i != -1 && i >= 0 && i < this.arrPorductos.length) {
      this.arrPorductos.splice(i, 1);
    }
  }

  //!actualizar producto por formulario

  updatePorducto(producto: ProductoI): void {
    //buscamos en le array por id no por si es distinto de null como antes
    let i = this.arrPorductos.findIndex(prod => prod.id === producto.id);

    //si no encuaentra el porducto en el array devuelve -1, pero si lo encuantra reemplaza el porducto por uno nuevo
    if (i !== -1) {
      this.arrPorductos[i] = producto;
    }

  }



  actualizarPorductoCarrito(producto: ProductoI, cantidad: number) {
    const p = this.arrCarrito.find(p => p.id === producto.id);

    if (p) {
      p.cantidad = cantidad;
      p.total = cantidad * Number(p.price);
    }
  }

  removeProductoCarrito(id: number) {
    const index = this.arrCarrito.findIndex(p => p.id === id);
    if (index !== -1) {
      this.arrCarrito.splice(index, 1);
    }
  }





}


