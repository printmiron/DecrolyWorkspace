import { Component, inject, Input } from '@angular/core';
import { PoroductInterface } from '../../interface/poroduct-interface';
import { FormsModule } from '@angular/forms';
import { ProductServices } from '../../services/product-services';

@Component({
  selector: 'app-card-component',
  imports: [FormsModule],
  templateUrl: './card-component.html',
  styleUrl: './card-component.css',
})
export class CardComponent {
  //pedimos al interface la estructura de datos que va a recibir el componente, PoroductInterface como padre, tambien guardamos la modena -
  // - para reutilizarla despues en html
  @Input() miProducto!: PoroductInterface["products"][number];
                 //el tipo de elemento "productos" | [number] cualquier posicion en el array [0], [1], etc.
  currency: string;

  //injectamos el servicio para pedir la moneda, y insertarla al inicializar
  productServices = inject(ProductServices);

  cantidad: number;
  total:  number;
  

  constructor() {
    this.cantidad = 0;
    this.total = 0;

    this.currency = "";
  }

  //al inicializar cojemos la moneda
  ngOnInit(): void {
    this.currency = this.productServices.getCurrency();
    
  }

  //funciones para sumar restar cantidad
  sumarCantidad() {
    this.cantidad++;
    this.sumarTotalProd();
  }

  restarCantidad() {
    if (this.cantidad >= 1) {
      this.cantidad--;
      this.sumarTotalProd();
    }
  }

  

  //sumar total de un producto
  sumarTotalProd() {
    
    this.total = this.cantidad * this.miProducto.price;
  }


}


// removeById(removeRemove: Interface): boolean {
//   this.arr.filter(item => 
//   item.id !== removeRemove.id);
// }