import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { HeroI } from '../interfaces/hero.interface';
import { lastValueFrom, map } from 'rxjs';
import { PowerstatsI } from '../interfaces/powerstats.interface';

@Injectable({
  providedIn: 'root',
})
export class HeroService {

  private baseUrl: string = "http://localhost:8080/api/characters?arg0=1&arg1=6";
  httpClient = inject(HttpClient);

  constructor(){}

  getAllHero(): Promise<HeroI[]> {

    return lastValueFrom(this.httpClient.get<HeroI[]>(this.baseUrl));


  }
}
