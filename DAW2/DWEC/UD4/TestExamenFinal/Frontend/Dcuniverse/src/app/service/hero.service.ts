import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { HeroI } from '../interfaces/hero.interface';
import { firstValueFrom, lastValueFrom, map, Observable } from 'rxjs';
import { PowerstatsI } from '../interfaces/powerstats.interface';


@Injectable({
  providedIn: 'root',
})
export class HeroService {

  private baseUrl: string = "http://localhost:8080/api/characters";
  httpClient = inject(HttpClient);

  constructor() { }

  getAllHero(): Promise<HeroI[]> {
    return lastValueFrom(this.httpClient.get<HeroI[]>(this.baseUrl));
  }


  getHeroById(id: string): Promise<HeroI> {
    return lastValueFrom(this.httpClient.get<HeroI>(this.baseUrl + "/" + id));
  }

  getHeroByPower(minPower: number): Promise<HeroI[]> {
    return lastValueFrom(this.httpClient.get<HeroI[]>(this.baseUrl + "/power/" + minPower));
  }

  getHeroByName(name: string): Promise<HeroI[]> {
    return lastValueFrom(this.httpClient.get<HeroI[]>(this.baseUrl + "/name/" + name));
  }


  insertHero(hero: HeroI): Promise<HeroI> {
    return lastValueFrom(this.httpClient.post<HeroI>(this.baseUrl, hero));
  }

  updateHero(hero: HeroI): Promise<any> {
    return lastValueFrom(this.httpClient.put<HeroI>(this.baseUrl, hero));
  }

  deleteById(id: string): Promise<HeroI> {
    return lastValueFrom(this.httpClient.delete<HeroI>(this.baseUrl + "/" + id));
  }








//---------------------------------------PLAN B SI NO VA LA PAGINACION




// // HeroService
// async getAllHerosComoArray(): Promise<HeroI[]> {
//   const response: any = await lastValueFrom(this.httpClient.get<any>(this.baseUrl));
  
//   // Si viene paginado, los héroes están en .content
//   // Si no viene paginado, la respuesta ya es el array
//   if (response.content) {
//     return response.content; 
//   }
//   return response;
// }

// export class ListHeroComponent implements OnInit {
//   private serviceHero = inject(HeroService);
  
//   arrHerosCompleto: HeroI[] = []; // Almacén total
//   currentPage: number = 0;
//   pageSize: number = 4;
//   filtroNombre: string = "";

//   async ngOnInit() {
//     // 1. Traemos TODOS los héroes al cargar la página
//     this.arrHerosCompleto = await this.serviceHero.getAllHerosComoArray();
//   }

//   // 2. Esta función "mágica" hace todo el trabajo de filtrado y paginado
//   get herosAMostrar(): HeroI[] {
//     // FILTRADO: Filtramos el almacén completo por nombre
//     const filtrados = this.arrHerosCompleto.filter(h => 
//       h.heroname.toLowerCase().includes(this.filtroNombre.toLowerCase())
//     );

//     // PAGINACIÓN: Cortamos el resultado para mostrar solo 4
//     const inicio = this.currentPage * this.pageSize;
//     return filtrados.slice(inicio, inicio + this.pageSize);
//   }

//   // 3. Cálculos auxiliares para el HTML
//   get totalPages(): number {
//     const filtrados = this.arrHerosCompleto.filter(h => 
//       h.heroname.toLowerCase().includes(this.filtroNombre.toLowerCase())
//     );
//     return Math.ceil(filtrados.length / this.pageSize);
//   }

//   cambiarPagina(valor: number) {
//     this.currentPage += valor;
//   }
// }

// <input type="text" [(ngModel)]="filtroNombre" (input)="currentPage = 0" placeholder="Buscar...">

// <div class="row">
//   @for (hero of herosAMostrar; track hero.id) {
//     <app-card-hero [miHero]="hero"></app-card-hero>
//   }
// </div>

// <button (click)="cambiarPagina(-1)" [disabled]="currentPage === 0">Atrás</button>
// <span>{{ currentPage + 1 }} de {{ totalPages }}</span>
// <button (click)="cambiarPagina(1)" [disabled]="currentPage >= totalPages - 1">Siguiente</button>




}
