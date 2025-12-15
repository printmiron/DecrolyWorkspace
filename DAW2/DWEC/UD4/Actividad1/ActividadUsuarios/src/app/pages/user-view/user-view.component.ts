import { Component, inject } from '@angular/core';
import { UsuarioI } from '../../interface/usuario.interface';
import { UsuarioService } from '../../service/usuario.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-view',
  imports: [],
  templateUrl: './user-view.component.html',
  styleUrl: './user-view.component.css',
})
export class UserViewComponent {
  miUser !: UsuarioI;
  serviceUser = inject(UsuarioService);
  activatedRoute = inject(ActivatedRoute);

  ngOnInit() {
    //!!!
    // this.activatedRoute.params.subscribe(async (params: any) => {
    //   let _id: string = params._id

    //   if (_id != undefined) {
    //     let respuesta = await this.serviceUser.getUserById(_id);

    //     if (respuesta != undefined) {
    //       this.miUser = respuesta;
    //     }

    //   }


    // });

  }



}
