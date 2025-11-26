import { Component, inject, Input } from '@angular/core';
import { ProductoI } from '../../interface/producto-i.interface';
import { ProductoSService } from '../../service/producto-s.service';


@Component({
  selector: 'app-product-card',
  imports: [],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.css',
})
export class ProductCardComponent {

  productoService = inject (ProductoSService);

  @Input() miProductos!: ProductoI;

  //!!!
  removeProducto(producto: ProductoI){
    this.productoService.removeProductoById(producto);
  }
}


