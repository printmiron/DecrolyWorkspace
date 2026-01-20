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
