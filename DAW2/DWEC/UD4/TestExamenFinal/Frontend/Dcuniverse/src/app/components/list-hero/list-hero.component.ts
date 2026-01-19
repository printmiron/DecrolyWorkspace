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





  //   // list-hero.component.ts
  // export class ListHeroComponent implements OnInit {
  //   private serviceHero = inject(HeroService);

  //   arrHeros: HeroI[] = [];         // El array que mostrarás (filtrado/paginado)
  //   arrHerosCompleto: HeroI[] = []; // La copia de seguridad con TODOS

  //   currentPage: number = 0;
  //   pageSize: number = 4;
  //   filtroNombre: string = "";

  //   async ngOnInit() {
  //     // Al usar el servicio corregido arriba, esto ya trae el array .content
  //     this.arrHerosCompleto = await this.serviceHero.getAllHero();
  //     this.actualizarVista();
  //   }

  //   // Función para refrescar lo que se ve en pantalla
  //   actualizarVista() {
  //     // Filtramos sobre el total
  //     const filtrados = this.arrHerosCompleto.filter(h => 
  //       h.heroname.toLowerCase().includes(this.filtroNombre.toLowerCase())
  //     );

  //     // Calculamos el total de páginas para los botones
  //     this.totalPages = Math.ceil(filtrados.length / this.pageSize);

  //     // Cortamos para la paginación de Front
  //     const inicio = this.currentPage * this.pageSize;
  //     this.arrHeros = filtrados.slice(inicio, inicio + this.pageSize);
  //   }

  //   totalPages: number = 0;

  //   cambiarPagina(valor: number) {
  //     this.currentPage += valor;
  //     this.actualizarVista();
  //   }
  // }




  //   <input type="text" [(ngModel)]="filtroNombre" (input)="currentPage = 0; actualizarVista()" placeholder="Buscar héroe...">

  // <div class="row">
  //   @for (hero of arrHeros; track hero.id) {
  //     <div class="col-md-3">
  //       <app-card-hero [miHero]="hero"></app-card-hero>
  //     </div>
  //   }
  // </div>

  // <div class="pagination-controls">
  //   <button [disabled]="currentPage === 0" (click)="cambiarPagina(-1)">Anterior</button>
  //   <span>Página {{ currentPage + 1 }} de {{ totalPages }}</span>
  //   <button [disabled]="currentPage >= totalPages - 1" (click)="cambiarPagina(1)">Siguiente</button>
  // </div>
}




