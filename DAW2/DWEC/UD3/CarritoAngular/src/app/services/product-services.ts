import { Injectable } from '@angular/core';
import { PoroductInterface } from '../interface/poroduct-interface';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})

export class ProductServices {
  
  

  constructor(private httpClient:HttpClient) {}

  //obtener productos
  getAllProd(): Observable<PoroductInterface[]> {
    return this.httpClient.get<PoroductInterface[]>('http://localhost:8080/api/carrito').pipe(
      map(response => response.products)
    );
  }

  //obtener moneda
  getCurrency(): Observable<string> {
    return this.httpClient.get<>
  }



  // getProdByRef(sku: string): PoroductInterface | undefined{
  //   return this.httpClient.get<PoroductInterface>('http://localhost:8080/api/carrito/sku');
  // }

  actualizarProductoCarrito(producto: PoroductInterface, cantidad: number){
    

  }


}

