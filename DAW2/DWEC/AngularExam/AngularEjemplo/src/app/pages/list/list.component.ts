import { Component, inject } from '@angular/core';
import { ProductoSService } from '../../service/producto-s.service';
import { ProductoI } from '../../interface/producto-i.interface';
import { ProductCardComponent } from "../../components/product-card/product-card.component";
import { CategoriasI } from '../../interface/categorias-i.interface';
import { FilterComponent } from "../../components/filter/filter.component";


@Component({
  selector: 'app-list',
  imports: [ProductCardComponent, FilterComponent],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css',
})
export class ListComponent {



  serviceProducto = inject(ProductoSService);

  arrProductos: ProductoI[];
 




  constructor() {
    this.arrProductos = [];
  


  }

  ngOnInit(): void {
    this.arrProductos = this.serviceProducto.getAllProductos();
    //copia el array principal
    

  }

  getCategorias($event: string) {
    this.arrProductos = this.serviceProducto.getProdByCategorias($event);
  }

  
  



}
