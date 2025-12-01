import { Component, inject } from '@angular/core';
import { ListCarritoComponent } from "../../components/list-carrito/list-carrito.component";
import { ProductoSService } from '../../service/producto-s.service';
import { ProductoI } from '../../interface/producto-i.interface';

@Component({
  selector: 'app-carrito',
  imports: [ListCarritoComponent],
  templateUrl: './carrito.component.html',
  styleUrl: './carrito.component.css',
})
export class CarritoComponent {


  serviceProducto = inject (ProductoSService);
  
    arrProductos: ProductoI[];
    
  
    constructor(){
      this.arrProductos = [];
    
  
    }
  
    ngOnInit(): void {
      this.arrProductos = this.serviceProducto.getCarrito();
      
      
    }



}
