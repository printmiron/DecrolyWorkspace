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




// // src/app/service/hero.service.ts

// // Versión "Traer Todo" (Si falla la paginación del Back)
// async getAllHeroesSinPaginado(): Promise<HeroI[]> {
//   const url = `${this.baseUrl}`; // URL limpia sin parámetros
//   // Asumimos que el back ahora devuelve el array directo [{}, {}]
//   return firstValueFrom(this.httpClient.get<HeroI[]>(url));
// }




// // src/app/components/list-hero/list-hero.component.ts

// export class ListHeroComponent implements OnInit {
//   private serviceHero = inject(HeroService);

//   arrHerosCompleto: HeroI[] = []; // Aquí guardamos TODO lo que venga del back
//   currentPage: number = 0;
//   pageSize: number = 4;
//   filtroPorPower: number = 0;

//   async ngOnInit() {
//     // Traemos todo de golpe al principio
//     this.arrHerosCompleto = await this.serviceHero.getAllHeroesSinPaginado();
//   }

//   // SIMULACIÓN DE PAGINACIÓN EN FRONT
//   // Esta función "corta" el array completo según la página actual
//   get herosAMostrar(): HeroI[] {
//     // 1. Primero filtramos por poder el array completo
//     const filtrados = this.arrHerosCompleto.filter(h => 
//       (h.powerstats?.power || 0) >= this.filtroPorPower
//     );

//     // 2. Calculamos el total de páginas basado en el filtro
//     this.totalPages = Math.ceil(filtrados.length / this.pageSize);

//     // 3. Cortamos para mostrar solo 4
//     const inicio = this.currentPage * this.pageSize;
//     return filtrados.slice(inicio, inicio + this.pageSize);
//   }

//   // Variable auxiliar para el HTML
//   totalPages: number = 0;

//   cambiarPagina(valor: number) {
//     this.currentPage += valor;
//   }

//   filtrarDirecto() {
//     this.currentPage = 0; // Reset al filtrar
//   }
// }






// @for (hero of herosAMostrar; track hero.id) {
//     <div class="col">
//         <app-card-hero [miHero]="hero"></app-card-hero>
//     </div>
// }

// <button (click)="cambiarPagina(-1)" [disabled]="currentPage === 0">Atrás</button>
// <span>{{ currentPage + 1 }} de {{ totalPages }}</span>
// <button (click)="cambiarPagina(1)" [disabled]="currentPage >= totalPages - 1">Sig.</button>





}
