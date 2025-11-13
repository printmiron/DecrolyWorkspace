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

  productos: PoroductInterface[] = [];

  constructor() {}

  //!!!!
  ngOnInit(): void {
    this.ProductServices.getAllProd().subscribe({
      next: (data) => this.productos = data
    });
  }

}
