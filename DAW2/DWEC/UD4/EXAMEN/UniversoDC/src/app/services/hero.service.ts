import { inject, Injectable } from '@angular/core';
import { HeroI } from '../interfaces/hero.interface';
import { lastValueFrom } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

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

  getHeroByName(name: string): Promise<HeroI[]> {
    return lastValueFrom(this.httpClient.get<HeroI[]>(this.baseUrl + "/name/" + name));
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
