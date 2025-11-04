import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-porduct-card',
  imports: [],
  templateUrl: './porduct-card.html',
  styleUrl: './porduct-card.css',
})
export class PorductCard {
  productos = [
    {id: 1, nombre: 'iPhone 13 Pro', ref: '0K3QOSOV4V', precio: 938.99, cantidad: 0},
    {id: 2, nombre: 'Cargador', ref: 'TGD5X0RY1L', precio: 49.99, cantidad: 0},
    {id: 3, nombre: 'Funda de piel', ref: 'IOKW9BQ9F3', precio: 79.99, cantidad: 0}
  ]

  disminuirCantidad(producto: any){
    if(producto.cantidad > 0){
      producto.cantidad--;
    }
  }

  aumentarCantidad(producto: any){
    producto.cantidad++;
  }

  getTotal(): number{
    return this.productos.reduce((sum, producto) => sum + producto.precio * producto.cantidad, 0)
  }
}
