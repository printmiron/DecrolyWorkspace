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

    return lastValueFrom(
      this.httpClient.get<ApiResponse>(this.baseUrl).pipe(
        //extraer solo la parte de results donde se encunetra los usuarios
        map(respuesta => respuesta.results)
      )
    );

   
  }

  getUserById(_id: string): Promise<UsuarioI | undefined> {

    return lastValueFrom(
      this.httpClient.get<ApiResponse>(this.baseUrl + "/" + _id).pipe(
        map(respuesta => respuesta.results.find(user => user._id === _id))
      )
    );
  }

  getUserByIdOBS(_id: string): Observable<UsuarioI>{
    return this.httpClient.get<UsuarioI>(this.baseUrl + "/" + _id);
  }

  deleteById(_id: string): Promise<UsuarioI> {
    return lastValueFrom(this.httpClient.delete<UsuarioI>(this.baseUrl + "/" + _id));
  }

  insertById(user: UsuarioI): Promise<UsuarioI> {
    return lastValueFrom(this.httpClient.post<UsuarioI>(this.baseUrl, user));
  }

  updateUser(user: UsuarioI): Promise<UsuarioI> {
    return lastValueFrom(this.httpClient.put<UsuarioI>(this.baseUrl + "/" + user._id, user));
  }



}
