import { Component, inject } from '@angular/core';
import { HeroService } from '../../service/hero.service';
import { HeroI } from '../../interfaces/hero.interface';
import { CardHeroComponent } from "../card-hero/card-hero.component";
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-list-hero',
  imports: [CardHeroComponent, FormsModule],
  templateUrl: './list-hero.component.html',
  styleUrl: './list-hero.component.css',
})
export class ListHeroComponent {
  serviceHero = inject(HeroService);
  arrHeros: HeroI[];

  currentPage: number = 0;
  pageSize: number = 4;

  filtroPorPower: number = 0;

  constructor() {
    this.arrHeros = []
  }

  async ngOnInit(): Promise<void> {
    try {
      this.arrHeros = await this.serviceHero.getAllHero();
    } catch (error) {
      console.log("error al obtener los heros | " + error);
    }
  }

  //------------------------Paginacion directa en angular
  //funcion para traer solo 4 heroes a la pagina
  get herosPaginados(): HeroI[] {
    const inicio = this.currentPage * this.pageSize;
    const fin = inicio + this.pageSize;

    return this.arrHeros.slice(inicio, fin);
  }

  cambiarPagina(valor: number) {
    this.currentPage += valor;
  }


  async filtrarDirecto() {
    if (this.filtroPorPower > 0) {

      this.arrHeros = await this.serviceHero.getHeroByPower(this.filtroPorPower);

    } else {

      this.arrHeros = await this.serviceHero.getAllHero();
      
    }
    this.currentPage = 0;
  }


//-----------------------------------------------PAGINADO EN BACK
 
// // src/app/interfaces/hero.interface.ts

// export interface HeroI {
//   id: string; // UUID de la base de datos
//   heroname: string;
//   fullname: string;
//   image1: string;
//   image2: string;
//   image3: string;
//   gender: string;
//   race: string;
//   alignment: string;
//   powerstats?: {
//     power: number;
//     intelligence: number;
//     strength: number;
//     speed: number;
//     durability: number;
//     combat: number;
//   }
// }

// // Estructura que devuelve Spring Boot con Pageable
// export interface PageResponse {
//   content: HeroI[];      // Lista de héroes de la página actual
//   totalPages: number;    // Total de páginas disponibles
//   totalElements: number; // Total de héroes en la BD
//   number: number;        // Número de página actual (empieza en 0)
//   last: boolean;         // Si es la última página
//   size: number;          // Tamaño de la página
// }




// // src/app/service/hero.service.ts
// import { inject, Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { firstValueFrom } from 'rxjs';
// import { PageResponse } from '../interfaces/hero.interface';

// @Injectable({ providedIn: 'root' })
// export class HeroService {
//   private httpClient = inject(HttpClient);
//   private baseUrl = 'http://localhost:8080/api/characters';

//   // Obtenemos los datos enviando página y filtro de poder
//   async getHeroesPaginados(page: number, power: number): Promise<PageResponse> {
//     // Genera algo como: .../characters?page=0&size=4&power=50
//     const url = `${this.baseUrl}?page=${page}&size=4&power=${power}`;
//     return firstValueFrom(this.httpClient.get<PageResponse>(url));
//   }
// }





// // src/app/components/list-hero/list-hero.component.ts
// import { Component, inject, OnInit } from '@angular/core';
// import { HeroService } from '../../service/hero.service';
// import { HeroI } from '../../interfaces/hero.interface';
// import { CardHeroComponent } from "../card-hero/card-hero.component";
// import { FormsModule } from '@angular/forms';

// @Component({
//   selector: 'app-list-hero',
//   standalone: true,
//   imports: [CardHeroComponent, FormsModule],
//   templateUrl: './list-hero.component.html',
//   styleUrl: './list-hero.component.css'
// })
// export class ListHeroComponent implements OnInit {
//   private serviceHero = inject(HeroService);

//   // Variables de estado
//   arrHeros: HeroI[] = [];
//   currentPage: number = 0;
//   totalPages: number = 0;
//   filtroPorPower: number = 0;

//   async ngOnInit() {
//     await this.cargarDatos();
//   }

//   // MÉTODO PRINCIPAL: Centraliza la llamada al servicio
//   async cargarDatos() {
//     try {
//       const response = await this.serviceHero.getHeroesPaginados(this.currentPage, this.filtroPorPower);
//       this.arrHeros = response.content; 
//       this.totalPages = response.totalPages;
//     } catch (error) {
//       console.error("Error al cargar héroes:", error);
//     }
//   }

//   // Se ejecuta cada vez que el usuario escribe en el buscador
//   async filtrarDirecto() {
//     this.currentPage = 0; // REGLA DE ORO: Siempre volver a pág 0 al filtrar
//     await this.cargarDatos();
//   }

//   // Se ejecuta en los botones de Atrás/Siguiente
//   async cambiarPagina(valor: number) {
//     this.currentPage += valor;
//     await this.cargarDatos();
//   }
// }




// @for (hero of arrHeros; track hero.id) {
//     <div class="col">
//         <app-card-hero [miHero]="hero"></app-card-hero>
//     </div>
// }

// <button [disabled]="currentPage === 0" (click)="cambiarPagina(-1)">Atrás</button>
// <span>{{ currentPage + 1 }} de {{ totalPages }}</span>
// <button [disabled]="currentPage >= totalPages - 1" (click)="cambiarPagina(1)">Siguiente</button>

}




