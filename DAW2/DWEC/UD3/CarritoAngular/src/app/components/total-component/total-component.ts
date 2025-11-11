import { Component, Input } from '@angular/core';
import { CURRENCY } from '../../database/productos-db';
import { CardComponent } from '../card-component/card-component';
import { ProductServices } from '../../services/product-services';

@Component({
  selector: 'app-total-component',
  imports: [],
  templateUrl: './total-component.html',
  styleUrl: './total-component.css',
})

export class TotalComponent {
  
  @Input() producto!: CardComponent;

  totalCarrito: number;
  currency = CURRENCY;

  constructor(){
    
    this.totalCarrito = 0;
  }

  actualizarTotalCarrito(){
   
  }

}
