import { Component, inject } from '@angular/core';
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

  ProductServices = inject(ProductServices);

  arrayProductos: PoroductInterface[];

  constructor() {

    this.arrayProductos = [];
  }

  ngOnInit(): void {
    this.arrayProductos = this.ProductServices.getAllProd();
  }

}
