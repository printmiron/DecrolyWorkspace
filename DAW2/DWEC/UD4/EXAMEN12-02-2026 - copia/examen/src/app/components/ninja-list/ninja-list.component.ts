import { Component, inject } from '@angular/core';
import { NinjaService } from '../../services/ninja.service';
import { NinjasI } from '../../interfaces/ninjas.interface';
import { NinjaCardComponent } from '../ninja-card/ninja-card.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-ninja-list',
  imports: [NinjaCardComponent, FormsModule],
  templateUrl: './ninja-list.component.html',
  styleUrl: './ninja-list.component.css',
})
export class NinjaListComponent {
  serviceNinja = inject(NinjaService);
  arrNinjas: NinjasI[];

  currentPage: number = 0;
  pageSize: number = 4;
  totalPages: number = 0;

  filtroPorAffiliation: string = "";
  filtroPorNombre: string = "";

  constructor() {
    this.arrNinjas = []
  }

  async ngOnInit(): Promise<void> {
    await this.cargarDatos();
  }

  async cargarDatos(): Promise<void> {
    try {
      const response: any = await this.serviceNinja.getAllNinja();
      this.arrNinjas = response.content;
      this.totalPages = response.totalPages;
    } catch (error) {
      console.log("error", error);
    }
  }


  get ninjasPaginados(): NinjasI[] {
    const inicio = this.currentPage * this.pageSize;
    const fin = inicio + this.pageSize;

    return this.arrNinjas.slice(inicio, fin);
  }

  async cambiarPagina(valor: number) {
    this.currentPage += valor;
    await this.cargarDatos();
  }


  async filtrarAffiliation() {
    if (this.filtroPorAffiliation.trim() !== "") {

      this.arrNinjas = await this.serviceNinja.getNinjaByAffiliation(this.filtroPorAffiliation);

    } else {

      this.arrNinjas = await this.serviceNinja.getAllNinja();

    }
    this.currentPage = 0;
  }

  async filtraNombre() {

    if (this.filtroPorNombre.trim() !== "") {

      this.arrNinjas = await this.serviceNinja.getNinjaByName(this.filtroPorNombre);

    } else {

      this.arrNinjas = await this.serviceNinja.getAllNinja();

    }
    this.currentPage = 0;
  }

}
