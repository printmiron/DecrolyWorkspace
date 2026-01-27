import { Component, inject } from '@angular/core';
import { Router, RouterLink } from "@angular/router";
import { HeroService } from '../../service/hero.service';
import { FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { User } from '../../interfaces/user.interface';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  private userService = inject(UserService);
  private router = inject(Router);

  ngOnInit(): void {
    if (localStorage.getItem('accessToken')) {
      this.router.navigate(['/home']);
    }
  }


  async getUser(loginForm: NgForm) {
    const loginUser: User = loginForm.value as User;
    loginUser.expiresInMins = 30;

    //Hay que hacer la petición de login
    try {
      let response = await this.userService.login(loginUser);
      
      if (response.accessToken) {
        localStorage.setItem("accessToken", response.accessToken);
        
        localStorage.setItem("user", JSON.stringify(response.user));

        this.router.navigate(['/landingPage']);
        loginForm.reset();
      }

    } catch (error) {
      alert("Credenciales incorrectos");
      loginForm.reset();
    }

  }

}
