import { Component, inject, Input, ViewChild } from '@angular/core';
import { CardComponent } from "../card-component/card-component";
import { ProductServices } from '../../services/product-services';
import { PoroductInterface } from '../../interface/poroduct-interface';


@Component({
  selector: 'app-list-component',
  imports: [CardComponent],
  templateUrl: './list-component.html',
  styleUrl: './list-component.css',
})
export class ListComponent {

  //injectamos el servicio
  ProductServices = inject(ProductServices);

  arrProductos: PoroductInterface["products"];



  constructor() {
    this.arrProductos = [];
  }

  //!!!!
  //al inicializar rellenamos el array con productos desdel servicio
  ngOnInit(): void {
    this.arrProductos = this.ProductServices.getAllProd();
    
  }

  

}
