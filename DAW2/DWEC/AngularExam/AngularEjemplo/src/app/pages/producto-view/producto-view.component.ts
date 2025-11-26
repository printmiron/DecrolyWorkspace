import { Component, inject } from '@angular/core';
import { ProductoSService } from '../../service/producto-s.service';
import { ActivatedRoute } from '@angular/router';
import { ProductoI } from '../../interface/producto-i.interface';

@Component({
  selector: 'app-producto-view',
  imports: [],
  templateUrl: './producto-view.component.html',
  styleUrl: './producto-view.component.css',
})
export class ProductoViewComponent {

  miProducto !: ProductoI;
  servicePorducto = inject(ProductoSService);
  activatedRoute = inject(ActivatedRoute);

  //!!!!!!!!!!!!
  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params: any) => {

      let miId = Number (params.id);

      if (miId != undefined) {
        
        let respuesta = this.servicePorducto.getById(miId);

        if (respuesta != undefined) {
          this.miProducto = respuesta;
        }
        
      }
      

    });
  }

}
