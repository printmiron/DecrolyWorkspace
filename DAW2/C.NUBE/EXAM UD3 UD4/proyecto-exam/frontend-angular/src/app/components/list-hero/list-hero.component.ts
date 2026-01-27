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
  filtroPorNombre: string = "";

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

  //   async ngOnInit(): Promise<void> {
  //   try {
  //     // La respuesta ya no es el array, es el objeto con 'content'
  //     const response: any = await this.serviceHero.getAllHero(); 
  //     this.arrHeros = response.content; // <--- Extraemos el array aquí
  //     this.totalPages = response.totalPages;
  //   } catch (error) {
  //     console.log("error", error);
  //   }
  // }

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

  async filtraNombre() {

    if (this.filtroPorNombre.trim() !== "") {

      this.arrHeros = await this.serviceHero.getHeroByName(this.filtroPorNombre);

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




  //   // hero.service.ts
  // import { PageResponse, HeroI } from '../interfaces/hero.interface'; // Importa ambas

  // async getAllHero(): Promise<HeroI[]> {
  //   // 1. Hacemos el GET esperando la interfaz PageResponse
  //   const response = await lastValueFrom(this.httpClient.get<PageResponse>(this.baseUrl));

  //   // 2. IMPORTANTE: Devolvemos solo la propiedad .content (que es el array de héroes)
  //   return response.content; 
  // }





//  export class ListHeroComponent {
//   serviceHero = inject(HeroService);
//   arrHeros: HeroI[] = [];

//   currentPage: number = 0;
//   pageSize: number = 4;
//   totalPages: number = 0; // <--- NUEVO: El back nos dirá cuántas hay

//   filtroPorPower: number = 0;
//   filtroPorNombre: string = "";

//   async ngOnInit(): Promise<void> {
//     await this.cargarDatos(); // Centralizamos la carga
//   }

//   // Creamos este método para no repetir código en filtros y ngOnInit
//   async cargarDatos() {
//     try {
//       // 1. Llamamos al servicio (asegúrate de que el servicio devuelva Promise<PageResponse>)
//       const response: any = await this.serviceHero.getAllHero(this.currentPage, this.pageSize);
      
//       // 2. IMPORTANTE: Guardamos solo el array de héroes
//       this.arrHeros = response.content; 
      
//       // 3. Guardamos el total de páginas para el HTML
//       this.totalPages = response.totalPages;
//     } catch (error) {
//       console.log("error al obtener los heros | " + error);
//     }
//   }

//   // Ya no usas "get herosPaginados" con slice, 
//   // porque el back ya te trae solo los 4 que tocan.
//   // En el HTML deberás usar "arrHeros" directamente.

//   async cambiarPagina(valor: number) {
//     this.currentPage += valor;
//     await this.cargarDatos(); // Pedimos la nueva página al servidor
//   }

//   async filtrarDirecto() {
//     this.currentPage = 0; // Siempre reset al filtrar
//     await this.cargarDatos();
//   }

//   async filtraNombre() {
//     this.currentPage = 0; // Siempre reset al filtrar
//     await this.cargarDatos();
//   }
// }


// @for (hero of arrHeros; track hero.id) {
//     <app-card-hero [miHero]="hero"></app-card-hero>
// }

// <button (click)="cambiarPagina(-1)" [disabled]="currentPage === 0">Atrás</button>

// <span>Página {{ currentPage + 1 }} de {{ totalPages }}</span>

// <button (click)="cambiarPagina(1)" [disabled]="currentPage >= totalPages - 1">Siguiente</button>
}




