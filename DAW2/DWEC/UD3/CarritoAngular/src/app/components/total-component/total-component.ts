import { Component, inject, Input } from '@angular/core';
import { ProductServices } from '../../services/product-services';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-total-component',
  imports: [FormsModule],
  templateUrl: './total-component.html',
  styleUrl: './total-component.css',
})

export class TotalComponent {
  
  currency: string;

  //injectamos el servicio
  productServices = inject(ProductServices);


  totalCarrito: number;
  constructor(){
    this.totalCarrito = 0;
    this.currency = "" ;
  }

  //al inicializar cojemos la moneda
  ngOnInit(): void {
    this.currency = this.productServices.getCurrency();
  }

  //findIndex
  //array.splice


}
