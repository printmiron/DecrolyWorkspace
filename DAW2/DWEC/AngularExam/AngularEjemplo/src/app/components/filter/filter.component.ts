import { Component, EventEmitter, inject, Output } from '@angular/core';
import { CategoriasI } from '../../interface/categorias-i.interface';
import { ProductoSService } from '../../service/producto-s.service';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-filter',
  imports: [FormsModule],
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css',
})
export class FilterComponent {
  
  categorias: CategoriasI[];
  filterProductos: any;
  productoService = inject(ProductoSService);

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
