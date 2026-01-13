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
    try {
      const loginUser: User = loginForm.value as User;
      let response = await this.userService.login(loginUser);

      console.log('Respuesta del servidor:', response); // Mira la consola del navegador (F12)

      // IMPORTANTE: Cambia response.accessToken por response.token
      if (response && response.token) {
        localStorage.setItem("accessToken", response.token);
        console.log('Token guardado correctamente');

        this.router.navigate(['/home']);
        loginForm.reset();
      } else {
        console.error('El servidor no envió la propiedad "token"');
      }
    } catch (error) {
      console.error("Error en la petición:", error);
      alert("Credenciales incorrectas o servidor caído");
    }
  }
}
