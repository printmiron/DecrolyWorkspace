import { Component, inject, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { User } from '../../interfaces/user.interface';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent implements OnInit{
  private router = inject(Router);
  

  miUser: User | undefined;

  ngOnInit(): void {
      const userJson = localStorage.getItem('user');
      if (userJson) {
        try {
          this.miUser = JSON.parse(userJson);
        }catch{
          console.error("Error");
        }
      }
  }
  

  get isToken(): boolean {
    return !!localStorage.getItem('accessToken');
  }

  logout() {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('user');
    this.router.navigate(['/landingPage']);
  }
}

