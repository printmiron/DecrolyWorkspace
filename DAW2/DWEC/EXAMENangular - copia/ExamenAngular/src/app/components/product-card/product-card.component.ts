import { Component, inject, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ProductService } from '../../service/product.service';
import { ProductoI } from '../../interface/producto.interface';

@Component({
  selector: 'app-product-card',
  imports: [RouterLink],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.css',
})
export class ProductCardComponent {
  productService = inject(ProductService);
  currency = "€";

  @Input() miProductos!: ProductoI;

  removeProducto(producto: ProductoI){
    this.productService.removeProductoById(producto.id!);
  }


}
