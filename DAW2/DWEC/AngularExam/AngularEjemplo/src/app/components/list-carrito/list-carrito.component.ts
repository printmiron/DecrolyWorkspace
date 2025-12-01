import { Component, inject } from '@angular/core';
import { ProductoSService } from '../../service/producto-s.service';
import { ProductoI } from '../../interface/producto-i.interface';
import { CardCarritoComponent } from "../card-carrito/card-carrito.component";

@Component({
  selector: 'app-list-carrito',
  imports: [CardCarritoComponent],
  templateUrl: './list-carrito.component.html',
  styleUrl: './list-carrito.component.css',
})
export class ListCarritoComponent {
  //injectamos el servicio
  productServices = inject(ProductoSService);

  arrProductos: ProductoI[];



  constructor() {
    this.arrProductos = [];
  }

  //!!!!
  //al inicializar rellenamos el array con productos desdel servicio
  ngOnInit(): void {
    this.arrProductos = this.productServices.getCarrito();
    
  }
}
