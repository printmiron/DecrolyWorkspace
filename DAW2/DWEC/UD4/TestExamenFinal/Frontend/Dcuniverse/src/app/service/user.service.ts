import { inject, Injectable } from '@angular/core';
import { User } from '../interfaces/user.interface';
import { lastValueFrom } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private httpClient = inject(HttpClient);
  private baseUrl: string = 'https://dummyjson.com/auth/';

  constructor() { }

  login(user: User): Promise<any> {
    return lastValueFrom(this.httpClient.post<any>(this.baseUrl + "login", user));
  }

  
}



// import { inject, Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { AuthResponse } from './auth.model'; // La interface que creamos antes
// import { tap } from 'rxjs';
// import { Router } from '@angular/router';

// @Injectable({
//   providedIn: 'root'
// })
// export class AuthService {
//   private http = inject(HttpClient);
//   private router = inject(Router);
  
//   // Cambia esta URL por la de tu examen (ej. http://localhost:8080/api/auth/login)
//   private API_URL = 'http://localhost:8080/api/auth/signin';

//   login(credentials: any) {
//     return this.http.post<AuthResponse>(this.API_URL, credentials).pipe(
//       tap(response => {
//         // Al recibir la respuesta exitosa, guardamos los tokens
//         localStorage.setItem('accessToken', response.accessToken);
//         localStorage.setItem('refreshToken', response.refreshToken);
//         localStorage.setItem('username', response.username);
//       })
//     );
//   }

//   logout() {
//     // 1. Limpiamos el storage (como dice la pág 28 del PDF)
//     localStorage.clear(); 
//     // 2. Mandamos al usuario al login
//     this.router.navigate(['/login']);
//   }
// }
