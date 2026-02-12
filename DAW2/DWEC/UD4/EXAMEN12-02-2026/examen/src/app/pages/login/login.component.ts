import { Component, inject } from '@angular/core';
import { FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UserService } from '../../services/user.service';
import { UserI } from '../../interfaces/user.interface';

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
    if (localStorage.getItem('token')) {
      this.router.navigate(['/home']);
    }
  }


  async getUser(loginForm: NgForm) {
    const loginUser: UserI = loginForm.value as UserI;;

    //Hay que hacer la petición de login
    try {
      let response = await this.userService.login(loginUser);

      if (response.token) {
        localStorage.setItem("token", response.token);

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


