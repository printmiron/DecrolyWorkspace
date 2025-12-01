import { Component, EventEmitter, inject, Output } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { ProductoI } from '../../interface/producto.interface';
import { CategoriasI } from '../../interface/categorias.interface';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-filter',
  imports: [FormsModule],
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css',
})
export class FilterComponent {
  productoService = inject(ProductService);
  flterPorductos: any;
  categorias: CategoriasI[];

  @Output() categoriaSelecionada: EventEmitter<string> = new EventEmitter();
 
  constructor(){
    this.categorias = [];
  }

  ngOnInit(){
    this.categorias = this.productoService.getAllCategorias();
  }

  getDataFilter(filter: any){
    this.categoriaSelecionada.emit(filter.value.categoria);
  }

}
