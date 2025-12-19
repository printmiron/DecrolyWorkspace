import { Component, inject, Input } from '@angular/core';
import { UsuarioService } from '../../service/usuario.service';
import { Router, RouterLink } from '@angular/router';
import { UsuarioI } from '../../interface/usuario.interface';
import Swal from 'sweetalert2';
import 'sweetalert2/themes/bootstrap-5.css'


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


  async deleteUser(user: UsuarioI) {

    const response = await this.serviceUser.deleteById(user._id!);

    if (response._id) {
      Swal.fire({
        icon: "success",
        text: "Se ha eliminado el usuario: " + user.username,
        theme: 'bootstrap-5-light'
      });

      Swal.fire({
        title: "Eres seguro que quieres eliminar " + user.username + "?",
        icon: "warning",
        theme: 'bootstrap-5-light',
        showCancelButton: true,
        confirmButtonColor: "#d33",
        cancelButtonColor: "#3085d6",
        confirmButtonText: "Si, borrar!"
        
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire({
            title: "Borrado!",
            text: "Se ha eliminado el usuario: " + user.username,
            icon: "success",
            theme: 'bootstrap-5-light'
          });
        }
      });

    } else {

      Swal.fire({
        icon: "error",
        text: "No se ha eliminado el usuario: " + user.username,
        theme: 'bootstrap-5-light'
      });
    }
  }

}
