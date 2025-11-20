import { Component, inject, Input, OnInit } from '@angular/core';
import { ProductServices } from '../../services/product-services';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-total-component',
  imports: [FormsModule],
  templateUrl: './total-component.html',
  styleUrl: './total-component.css',
})

export class TotalComponent {

  //moneda
  currency: string;

  //injectamos el servicio
  productServices = inject(ProductServices);

  totalCarrito: number;




  constructor() {
    this.totalCarrito = 0;
    this.currency = "";

   
  }


  ngOnInit(): void {
    //al inicializar cojemos la moneda
    this.currency = this.productServices.getCurrency();
    //!cojemos el total carrito desde el service
    this.totalCarrito = this.productServices.getTotalCarrito();
  }

  

  

  //findIndex
  //array.splice


}
