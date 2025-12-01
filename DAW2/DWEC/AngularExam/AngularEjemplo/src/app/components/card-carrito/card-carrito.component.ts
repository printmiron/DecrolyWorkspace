import { Component, inject, Input } from '@angular/core';
import { ProductoI } from '../../interface/producto-i.interface';
import { ProductoSService } from '../../service/producto-s.service';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-card-carrito',
  imports: [ FormsModule],
  templateUrl: './card-carrito.component.html',
  styleUrl: './card-carrito.component.css',
})
export class CardCarritoComponent {


  @Input() miProducto!: ProductoI;


  productServices = inject(ProductoSService);
 


  cantidad: number;
  total: number;
  currency: string;



  constructor() {
    this.cantidad = 0;
    this.total = 0;
    this.currency = "€";

  }


  ngOnInit(): void {
    this.cantidad = this.miProducto.cantidad ?? 1;
    this.total = this.miProducto.total ?? Number(this.miProducto.price);
  }


  //funciones para sumar restar cantidad
  sumarCantidad() {
    this.cantidad++;
    this.sumarTotalProd();
  }

  restarCantidad() {
    if (this.cantidad >= 1) {
      this.cantidad--;
      this.sumarTotalProd();
    }
  }



  //sumar total de un producto
  sumarTotalProd() {
    this.total = this.cantidad * Number(this.miProducto.price);

    this.productServices.actualizarPorductoCarrito(this.miProducto, this.cantidad);
  }

  eliminarProdCarrito(producto: ProductoI){
    this.productServices.removeProductoCarrito(this.miProducto.id!);

    
  }



}
