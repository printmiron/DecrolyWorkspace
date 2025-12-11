import { Component, inject } from '@angular/core';
import { CardUserComponent } from "../card-user/card-user.component";
import { UsuarioService } from '../../service/usuario.service';
import { UsuarioI } from '../../interface/usuario.interface';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-list-user',
  imports: [CardUserComponent],
  templateUrl: './list-user.component.html',
  styleUrl: './list-user.component.css',
})
export class ListUserComponent {
  serviceUser = inject(UsuarioService);
  arrUsuarios: UsuarioI[];

  constructor() {
    this.arrUsuarios = [];
  }

  async ngOnInit(): Promise<void> {
    try {
      this.arrUsuarios = await this.serviceUser.getAllUsers();
    } catch (error) {
      console.log("error al obtener los usuarios | " + error);
    }
  }
}
