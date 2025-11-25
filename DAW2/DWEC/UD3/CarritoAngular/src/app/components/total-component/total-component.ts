import { Component, inject, Input, OnInit } from '@angular/core';
import { ProductServices } from '../../services/product-services';
import { FormsModule } from '@angular/forms';
import { PoroductInterface } from '../../interface/poroduct-interface';


@Component({
  selector: 'app-total-component',
  imports: [FormsModule],
  templateUrl: './total-component.html',
  styleUrl: './total-component.css',
})

export class TotalComponent {



  //injectamos el servicio
  productServices = inject(ProductServices);


  //moneda
  currency: string;
  totalCarrito: number;
  productosCarrito: any[];



  constructor() {
    this.totalCarrito = 0;
    this.currency = "";
    this.productosCarrito = [];


  }


  ngOnInit(): void {

    //!aqui el componete total se sbscribe al objeto "carrito$" que esta en servico
    this.productServices.carrito$.subscribe(() => {

      //al inicializar cojemos la moneda
      this.currency = this.productServices.getCurrency();

      //!cojemos el total carrito desde el service
      this.totalCarrito = this.productServices.getTotalCarrito();

      //cojemos los productos con cantidad mayor a 0 desdel service
      this.productosCarrito = this.productServices.getProdCarrito();
      
    });

  }



  //findIndex
  //array.splice


}
