import { Component, inject } from '@angular/core';
import { UsuarioService } from '../../service/usuario.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioI } from '../../interface/usuario.interface';

@Component({
  selector: 'app-nuevo-usuario',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './nuevo-usuario.component.html',
  styleUrl: './nuevo-usuario.component.css',
})
export class NuevoUsuarioComponent {

  userForm: FormGroup;
  serviceUser = inject(UsuarioService);
  activatedRoute = inject(ActivatedRoute);
  router = inject(Router);

  isNew: boolean;

  constructor() {
    this.isNew = true;

    this.userForm = new FormGroup({
      _id: new FormControl(null, []),
      id: new FormControl(null, []),
      first_name: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      last_name: new FormControl(null, [Validators.required]),
      username: new FormControl(null, [Validators.required]),
      email: new FormControl(null, [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$")]),
      image: new FormControl(null, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
      password: new FormControl(null, [Validators.required]),
    }, []);
  }


  async getDataFormulario() {
    let user = this.userForm.value as UsuarioI;

    if (this.isNew) {

      const response = await this.serviceUser.insertById(user);

      if (response.id) {

        alert("El usuario " + user.username + " fui registrado bien");

      } else {

        const response = await this.serviceUser.updateUser(user);

        if (response.id) {
          alert("Usuario editado bien");
        }

      }
    }

    this.userForm.reset();
    this.router.navigate(['/home']);

  }

  Control(formControlName: string, validator: string): boolean | undefined {
    return this.userForm.get(formControlName)?.hasError(validator) && this.userForm.get(formControlName)?.touched
  }


  ngOnInit(): void {
    this.activatedRoute.params.subscribe(async (params: any) => {
      let _id: string = params._id;

      if (_id != undefined) {
        let miUser = await this.serviceUser.getUserById(_id);

        if (miUser != undefined) {
          this.isNew = false;
          this.userForm = new FormGroup({
            _id: new FormControl(miUser._id, []),
            id: new FormControl(miUser.id, []),
            first_name: new FormControl(miUser.first_name, [Validators.required, Validators.minLength(4)]),
            last_name: new FormControl(miUser.last_name, [Validators.required]),
            username: new FormControl(miUser.username, [Validators.required]),
            email: new FormControl(miUser.email, [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$")]),
            image: new FormControl(miUser.image, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
            password: new FormControl(miUser.password, [Validators.required]),
          }, []);
        } else {
          alert("No se encuantra el usuario");
        }

      }

    });
  }


}
