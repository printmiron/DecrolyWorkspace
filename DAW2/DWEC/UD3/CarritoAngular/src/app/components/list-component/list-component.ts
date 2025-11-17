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

  productos: PoroductInterface["products"] = [];
  currency: string = '';

  constructor(private productServices: ProductServices) {}

  //!!!!
  //extare desde back los productos y la moneda
  ngOnInit(): void {
    
    this.ProductServices.getAllProd().subscribe({

      next: (data) => {

        this.productos = data.products;
        this.currency = data.currency;
      },

      error: error => console.error("Error al cargar los productos o moneda")
      
    });
  }

}
