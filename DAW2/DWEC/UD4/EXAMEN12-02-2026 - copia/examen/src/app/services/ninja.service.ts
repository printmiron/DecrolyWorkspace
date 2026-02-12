import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { NinjasI } from '../interfaces/ninjas.interface';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NinjaService {
  private baseUrl: string = "http://localhost:8080/api/ninjas";
  httpClient = inject(HttpClient);

  constructor() { }

  getAllNinja(): Promise<NinjasI[]> {
    return lastValueFrom(this.httpClient.get<NinjasI[]>(this.baseUrl));
  }

  getNinjaById(id: number): Promise<NinjasI> {
    return lastValueFrom(this.httpClient.get<NinjasI>(this.baseUrl + "/" + id));
  }

  getNinjaByAffiliation(affiliation: string): Promise<NinjasI[]> {
    return lastValueFrom(this.httpClient.get<NinjasI[]>(this.baseUrl + "/affiliation/" + affiliation));
  }

  getNinjaByName(name: string): Promise<NinjasI[]> {
    return lastValueFrom(this.httpClient.get<NinjasI[]>(this.baseUrl + "/name/" + name));
  }

  insertNinja(ninja: NinjasI): Promise<NinjasI> {
    return lastValueFrom(this.httpClient.post<NinjasI>(this.baseUrl, ninja));
  }

  updateNinja(ninja: NinjasI): Promise<any> {
    return lastValueFrom(this.httpClient.put<NinjasI>(this.baseUrl, ninja));
  }

  deleteById(id: number): Promise<NinjasI> {
    return lastValueFrom(this.httpClient.delete<NinjasI>(this.baseUrl + "/" + id));
  }

}
