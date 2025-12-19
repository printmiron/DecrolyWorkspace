import { Component, inject } from '@angular/core';
import { UsuarioI } from '../../interface/usuario.interface';
import { UsuarioService } from '../../service/usuario.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-user-view',
  imports: [RouterLink],
  templateUrl: './user-view.component.html',
  styleUrl: './user-view.component.css',
})
export class UserViewComponent {
  miUser !: UsuarioI;
  serviceUser = inject(UsuarioService);
  activatedRoute = inject(ActivatedRoute);


  ngOnInit(): void {
    //Usando el endpoint específico para obtener usuario por id
    this.activatedRoute.params.subscribe(async (params: any) => {

      let _id: string = params._id
      console.log(_id)

      if (_id != undefined) {
        let response = await this.serviceUser.getUserById(_id);
        console.log(response)

        if (response != undefined) {
          this.miUser = response;

        }

      }

    });

  }

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
