import { Component, inject } from '@angular/core';
import { ProductCardComponent } from "../../components/product-card/product-card.component";
import { ProductService } from '../../service/product.service';
import { ProductoI } from '../../interface/producto.interface';
import { FilterComponent } from "../../components/filter/filter.component";

@Component({
  selector: 'app-list',
  imports: [ProductCardComponent, FilterComponent],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css',
})
export class ListComponent {
  productoService = inject(ProductService);

  arrProductos: ProductoI[];

  constructor() {
    this.arrProductos = [];
  }


  ngOnInit(){
    this.arrProductos = this.productoService.getAllProductos();
  }

  getCategorias($event: string){
    this.arrProductos = this.productoService.getByCategorias($event);
  }
}
