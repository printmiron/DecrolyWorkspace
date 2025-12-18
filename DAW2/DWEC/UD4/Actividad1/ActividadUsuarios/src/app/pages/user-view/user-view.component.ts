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

 


}
