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


  getHeroById(id: number): Promise<HeroI> {
    return lastValueFrom(this.httpClient.get<HeroI>(this.baseUrl + "/" + id));
  }

  getHeroByPower(minPower: number): Promise<HeroI[]> {
    return lastValueFrom(this.httpClient.get<HeroI[]>(this.baseUrl + "/power/" + minPower));
  }


  insertHero(hero: HeroI): Promise<HeroI> {
    return lastValueFrom(this.httpClient.post<HeroI>(this.baseUrl, hero));
  }

  updateHero(hero: HeroI): Promise<any> {
    return lastValueFrom(this.httpClient.put<HeroI>(this.baseUrl, hero));
  }

  deleteById(id: number): Promise<HeroI> {
    return lastValueFrom(this.httpClient.delete<HeroI>(this.baseUrl + "/" + id));
  }
}
