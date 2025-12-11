import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { UsuarioI } from '../interface/usuario.interface';
import { lastValueFrom, map, Observable } from 'rxjs';
import { ApiResponse } from '../interface/api-response.interface';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {

  private baseUrl: string = "https://peticiones.online/api/users";
  httpClient = inject(HttpClient);

  constructor() { }

  getAllUsers(): Promise<UsuarioI[]> {

    //espeficamos que esperamos la respuesta completa de (ApiResponse)
    const request = this.httpClient.get<ApiResponse>(this.baseUrl).pipe(
    //extraer solo la parte de results donde se encunetra los usuarios
    map(respuesta => respuesta.results)
    );
    //devolvemos el observable modificado que emite solo UsuariosI[]
    return lastValueFrom(request);
  }

  getUserById(_id: string): Promise<UsuarioI>{
    return lastValueFrom(this.httpClient.get<UsuarioI>(this.baseUrl + "/" + _id));
  }



}
