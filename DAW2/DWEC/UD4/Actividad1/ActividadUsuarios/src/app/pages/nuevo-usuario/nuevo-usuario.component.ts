import { Component, inject } from '@angular/core';
import { UsuarioService } from '../../service/usuario.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-nuevo-usuario',
  imports: [],
  templateUrl: './nuevo-usuario.component.html',
  styleUrl: './nuevo-usuario.component.css',
})
export class NuevoUsuarioComponent {

  userForm: FormGroup;
  serviceUser = inject(UsuarioService);
  activatedRoute = inject(ActivatedRoute);
  router = inject(Router);

  constructor(){
    this.userForm = new FormGroup({
      _id: new FormGroup(null, []),
      id: new FormGroup(null, []),
      first_name: new FormGroup(null, [Validators.required, Validators.minLength(4)]),
      last_name: new FormGroup(null, [Validators.required]),
      username: new FormGroup(null, [Validators.required]),
      email: new FormGroup(null, [Validators.required]),
      image: new FormGroup(null, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
      password: new FormGroup(null, [Validators.required]),
    }, []);
  }

}
