import { Injectable } from '@angular/core';
import { PoroductInterface } from '../interface/poroduct-interface';
import { PRODUCTOS } from '../database/productos-db';

@Injectable({
  providedIn: 'root',
})

export class ProductServices {
  private arrayProductos: PoroductInterface[];
  

  constructor() {

    this.arrayProductos = PRODUCTOS;
    
  }

  getAllProd(): PoroductInterface[]{
    return this.arrayProductos;
  }

  getProdByRef(sku: string): PoroductInterface | undefined{
    return this.arrayProductos.find( producto => producto.sku == sku);
  }

  actualizarProductoCarrito(producto: PoroductInterface, cantidad: number){
    

  }


}

