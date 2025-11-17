import { Component, Input } from '@angular/core';
import { PoroductInterface } from '../../interface/poroduct-interface';

import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-card-component',
  imports: [FormsModule],
  templateUrl: './card-component.html',
  styleUrl: './card-component.css',
})
export class CardComponent {
  //pedimos al interface la estructura de datos que va a recibir el componente, PoroductInterface como padre, tambien guardamos la modena -
  // - para reutilizarla despues en html
  @Input() producto!: PoroductInterface["products"][number];
  @Input() currency!: string;

  cantidad: number;
  total: number;

  constructor(){
    this.cantidad = 0;
    this.total = 0;
  }

  //funciones para sumar restar cantidad
  sumarCantidad(){
    this.cantidad++;
    this.sumarTotalProd();
  }

  restarCantidad(){
    if (this.cantidad >= 1) {
      this.cantidad--;
      this.sumarTotalProd();
    }
  }

  //sumar total de un producto
  sumarTotalProd(){
    this.total = this.cantidad * this.producto.price;
  }


}
