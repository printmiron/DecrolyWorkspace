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
  //---Paginacion en back
  //   // hero.service.ts
  // getAllHero(page: number = 0, size: number = 4): Promise<any> {
  //   // Genera la URL: http://localhost:8080/api/characters?page=0&size=4
  //   const url = `${this.baseUrl}?page=${page}&size=${size}`;
  //   return lastValueFrom(this.httpClient.get<any>(url));
  // }

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




  //   // hero.service.ts
  // async getAllHero(): Promise<HeroI[]> {
  //   // Hacemos la petición normal
  //   const response = await lastValueFrom(this.httpClient.get<any>(this.baseUrl));

  //   // Si la respuesta tiene .content (es paginada), devolvemos solo los datos
  //   if (response && response.content) {
  //     return response.content;
  //   }
  //   // Si no, devolvemos la respuesta tal cual (por si el back no fuera paginado)
  //   return response;
  // }




  //   export class ListHeroComponent {
  //   serviceHero = inject(HeroService);

  //   // Tu estructura original
  //   arrHeros: HeroI[] = []; 
  //   currentPage: number = 0;
  //   pageSize: number = 4;
  //   filtroPorNombre: string = "";

  //   constructor() {
  //     this.arrHeros = [];
  //   }

  //   async ngOnInit(): Promise<void> {
  //     try {
  //       // Cargamos todos de golpe al principio
  //       this.arrHeros = await this.serviceHero.getAllHero();
  //     } catch (error) {
  //       console.log("error al obtener los heros | " + error);
  //     }
  //   }

  //   // ESTO ES LO QUE CAMBIA: Ahora el filtro es en el FRONT
  //   // Usamos un getter para que la lista se actualice sola al escribir
  //   get herosAMostrar(): HeroI[] {
  //     // 1. Filtramos por nombre sobre el array que ya tenemos
  //     const filtrados = this.arrHeros.filter(hero => 
  //       hero.heroname.toLowerCase().includes(this.filtroPorNombre.toLowerCase())
  //     );

  //     // 2. Aplicamos tu lógica de paginación (slice) sobre los filtrados
  //     const inicio = this.currentPage * this.pageSize;
  //     const fin = inicio + this.pageSize;

  //     return filtrados.slice(inicio, fin);
  //   }

  //   // Función auxiliar para saber el total de páginas en el HTML
  //   get totalPaginas(): number {
  //     const filtrados = this.arrHeros.filter(hero => 
  //       hero.heroname.toLowerCase().includes(this.filtroPorNombre.toLowerCase())
  //     );
  //     return Math.ceil(filtrados.length / this.pageSize);
  //   }

  //   cambiarPagina(valor: number) {
  //     this.currentPage += valor;
  //   }

  //   // Estos métodos ahora solo sirven para resetear la página
  //   async filtraNombre() {
  //     this.currentPage = 0; 
  //     // Ya no llamamos al servidor, el "get herosAMostrar" hace el trabajo
  //   }
  // }


  //   <input type="text" [(ngModel)]="filtroPorNombre" (input)="filtraNombre()" placeholder="Buscar...">

  // <div class="row">
  //   @for (hero of herosAMostrar; track hero.id) {
  //       <div class="col">
  //           <app-card-hero [miHero]="hero"></app-card-hero>
  //       </div>
  //   }
  // </div>

  // <button (click)="cambiarPagina(-1)" [disabled]="currentPage === 0">Atrás</button>

  // <span> {{ currentPage + 1 }} de {{ totalPaginas }} </span>

  // <button (click)="cambiarPagina(1)" [disabled]="currentPage >= totalPaginas - 1">Siguiente</button>


}
