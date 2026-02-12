import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NinjaService } from '../../services/ninja.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { NinjasI } from '../../interfaces/ninjas.interface';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-nuevo-ninja',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './nuevo-ninja.component.html',
  styleUrl: './nuevo-ninja.component.css',
})
export class NuevoNinjaComponent {
  ninjaForm: FormGroup;
  serviceNinja = inject(NinjaService);
  activatedRoute = inject(ActivatedRoute);
  router = inject(Router);


  isNew: boolean;

  constructor() {
    this.isNew = true;


    this.ninjaForm = new FormGroup({
      id: new FormControl(null, []),
      ninjaname: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      clan: new FormControl(null, [Validators.required]),
      fullname: new FormControl(null, [Validators.required]),
      image1: new FormControl(null, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
      image2: new FormControl(null, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
      gender: new FormControl('', [Validators.required]),
      level: new FormControl('', [Validators.required]),
      naturetype: new FormControl('', [Validators.required]),
      affiliation: new FormControl('', [Validators.required]),
      stats: new FormGroup({
        id: new FormControl(null, []),
        //por defecto 50 se puede cambiar a 0
        ninjutsu: new FormControl(50, [Validators.required]),
        taijutsu: new FormControl(50, [Validators.required]),
        genjutsu: new FormControl(50, [Validators.required]),
        intelligence: new FormControl(50, [Validators.required]),
        strength: new FormControl(50, [Validators.required]),
        speed: new FormControl(50, [Validators.required]),
        stamina: new FormControl(50, [Validators.required]),
        handseals: new FormControl(50, [Validators.required])
      })
    }, []);
  }

  async getDataFormulario() {
    let ninja = this.ninjaForm.value as NinjasI;

    if (this.isNew) {

      const response = await this.serviceNinja.insertNinja(ninja);

      if (response.id) {

        Swal.fire({
          icon: "success",
          text: "Se ha registrado el hero: " + ninja.ninjaname,
          theme: 'bootstrap-5-light'
        });
      }
    } else {

      const response = await this.serviceNinja.updateNinja(ninja);

      if (response.id) {

        Swal.fire({
          icon: "success",
          text: "Se ha editado el hero: " + ninja.ninjaname,
          theme: 'bootstrap-5-light'
        });
      }

    }

    this.ninjaForm.reset();
    this.router.navigate(['/home']);
  }

  Control(formControlName: string, validator: string): boolean | undefined {
    return this.ninjaForm.get(formControlName)?.hasError(validator) && this.ninjaForm.get(formControlName)?.touched
  }


  ngOnInit(): void {
    this.activatedRoute.params.subscribe(async (params: any) => {

      let id: number = params.id;

      if (id != undefined) {
        let miNinja = await this.serviceNinja.getNinjaById(id);

        if (miNinja != undefined) {

          this.isNew = false;

          this.ninjaForm = new FormGroup({
            id: new FormControl(miNinja.id, []),
            ninjaname: new FormControl(miNinja.ninjaname, [Validators.required, Validators.minLength(4)]),
            clan: new FormControl(miNinja.clan, [Validators.required]),
            fullname: new FormControl(miNinja.fullname, [Validators.required]),
            image1: new FormControl(miNinja.image1, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
            image2: new FormControl(miNinja.image2, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
            gender: new FormControl(miNinja.gender, [Validators.required]),
            level: new FormControl(miNinja.level, [Validators.required]),
            naturetype: new FormControl(miNinja.naturetype, [Validators.required]),
            affiliation: new FormControl(miNinja.affiliation, [Validators.required]),
            stats: new FormGroup({
              id: new FormControl(miNinja.stats?.id || null, []),
              ninjutsu: new FormControl(miNinja.stats?.ninjutsu, [Validators.required]),
              taijutsu: new FormControl(miNinja.stats?.taijutsu, [Validators.required]),
              genjutsu: new FormControl(miNinja.stats?.genjutsu, [Validators.required]),
              intelligence: new FormControl(miNinja.stats?.intelligence, [Validators.required]),
              strength: new FormControl(miNinja.stats?.strength, [Validators.required]),
              speed: new FormControl(miNinja.stats?.speed, [Validators.required]),
              stamina: new FormControl(miNinja.stats?.stamina, [Validators.required]),
              handseals: new FormControl(miNinja.stats?.handseals, [Validators.required])
            })
          }, []);


        } else {
          alert("No se encuantra el usuario");
        }

      }

    });
  }


}
