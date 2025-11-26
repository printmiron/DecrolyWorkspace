import { Component, inject } from '@angular/core';
import { ProductoSService } from '../../service/producto-s.service';
import { ProductoI } from '../../interface/producto-i.interface';
import { ProductCardComponent } from "../../components/product-card/product-card.component";


@Component({
  selector: 'app-list',
  imports: [ProductCardComponent],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css',
})
export class ListComponent {

  serviceProducto = inject (ProductoSService);

  arrProductosList: ProductoI[];

  constructor(){
    this.arrProductosList = [];
  }

  ngOnInit(): void {
    this.arrProductosList = this.serviceProducto.getAllProductos();
  }

}
