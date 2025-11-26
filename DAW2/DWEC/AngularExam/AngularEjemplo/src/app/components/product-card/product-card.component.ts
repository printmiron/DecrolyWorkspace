import { Component, Input } from '@angular/core';
import { ProductoI } from '../../interface/producto-i.interface';


@Component({
  selector: 'app-product-card',
  imports: [],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.css',
})
export class ProductCardComponent {
  @Input() miProductos!: ProductoI;
}


