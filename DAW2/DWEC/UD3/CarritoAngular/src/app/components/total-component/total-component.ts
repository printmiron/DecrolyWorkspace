import { Component, Input } from '@angular/core';
import { PoroductInterface } from '../../interface/poroduct-interface';

@Component({
  selector: 'app-total-component',
  imports: [],
  templateUrl: './total-component.html',
  styleUrl: './total-component.css',
})

export class TotalComponent {
  
  currency: string = '';
    

  totalCarrito: number;
  ProductServices: any;
  

  constructor(){
    
    this.totalCarrito = 0;
  }

  actualizarTotalCarrito(){
   
  }




}
