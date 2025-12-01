import { Component, inject } from '@angular/core';
import { ProductoI } from '../../interface/producto.interface';
import { ProductService } from '../../service/product.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-view',
  imports: [],
  templateUrl: './product-view.component.html',
  styleUrl: './product-view.component.css',
})
export class ProductViewComponent {
  miProducto!: ProductoI;
  productoService = inject(ProductService);
  activatedRoute = inject(ActivatedRoute);
  currency = "€";
  

  ngOnInit() {


    this.activatedRoute.params.subscribe((params: any) => {
      let miId = params.id;

      if (miId != undefined) {
        let respuesta = this.productoService.getById(miId);

        if (respuesta != undefined) {
          this.miProducto = respuesta;
        }

      }

    });
  }

}
