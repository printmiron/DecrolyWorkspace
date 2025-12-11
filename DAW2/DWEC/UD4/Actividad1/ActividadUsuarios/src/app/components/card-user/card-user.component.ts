import { Component, inject, Input } from '@angular/core';
import { UsuarioService } from '../../service/usuario.service';
import { Router, RouterLink } from '@angular/router';
import { UsuarioI } from '../../interface/usuario.interface';


@Component({
  selector: 'app-card-user',
  imports: [RouterLink],
  templateUrl: './card-user.component.html',
  styleUrl: './card-user.component.css',
})
export class CardUserComponent {
  serviceUser = inject(UsuarioService);
  router = inject(Router);
  @Input() miUser!: UsuarioI;
}
