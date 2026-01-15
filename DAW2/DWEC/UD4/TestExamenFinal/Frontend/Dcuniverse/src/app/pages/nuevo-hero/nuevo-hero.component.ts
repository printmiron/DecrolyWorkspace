import { Component, inject, Input } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HeroService } from '../../service/hero.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import Swal from 'sweetalert2';
import { HeroI } from '../../interfaces/hero.interface';

@Component({
  selector: 'app-nuevo-hero',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './nuevo-hero.component.html',
  styleUrl: './nuevo-hero.component.css',
})
export class NuevoHeroComponent {
  heroForm: FormGroup;
  serviceHero = inject(HeroService);
  activatedRoute = inject(ActivatedRoute);
  router = inject(Router);

  isNew: boolean;

  constructor() {
    this.isNew = true;

    //Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$")]), email
    this.heroForm = new FormGroup({
      id: new FormControl(null, []),
      heroname: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      fullname: new FormControl(null, [Validators.required]),
      image1: new FormControl(null, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
      image2: new FormControl(null, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
      image3: new FormControl(null, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
      gender: new FormControl('', [Validators.required]),
      race: new FormControl('', [Validators.required]),
      alignment: new FormControl('', [Validators.required]),
      powerstats: new FormGroup({
        id: new FormControl(null, []),
        intelligence: new FormControl(50, [Validators.required]),
        strength: new FormControl(50, [Validators.required]),
        speed: new FormControl(50, [Validators.required]),
        durability: new FormControl(50, [Validators.required]),
        power: new FormControl(50, [Validators.required]),
        combat: new FormControl(50, [Validators.required])
      })
    }, []);
  }

  async getDataFormulario() {
    let hero = this.heroForm.value as HeroI;

    if (this.isNew) {

      const response = await this.serviceHero.insertHero(hero);

      if (response.id) {

        Swal.fire({
          icon: "success",
          text: "Se ha registrado el hero: " + hero.heroname,
          theme: 'bootstrap-5-light'
        });
      }
    } else {

      const response = await this.serviceHero.updateHero(hero);

      if (response.id) {

        Swal.fire({
          icon: "success",
          text: "Se ha editado el hero: " + hero.heroname,
          theme: 'bootstrap-5-light'
        });
      }

    }

    this.heroForm.reset();
    this.router.navigate(['/home']);
  }

  Control(formControlName: string, validator: string): boolean | undefined {
    return this.heroForm.get(formControlName)?.hasError(validator) && this.heroForm.get(formControlName)?.touched
  }


  ngOnInit(): void {
    this.activatedRoute.params.subscribe(async (params: any) => {
      //!string o number por id
      let id: string = params.id;

      if (id != undefined) {
        let miHero = await this.serviceHero.getHeroById(id);

        if (miHero != undefined) {

          this.isNew = false;

          this.heroForm = new FormGroup({
            id: new FormControl(miHero.id, []),
            heroname: new FormControl(miHero.heroname, [Validators.required, Validators.minLength(4)]),
            fullname: new FormControl(miHero.fullname, [Validators.required]),
            image1: new FormControl(miHero.image1, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
            image2: new FormControl(miHero.image2, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
            image3: new FormControl(miHero.image3, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
            gender: new FormControl(miHero.gender, [Validators.required]),
            race: new FormControl(miHero.race, [Validators.required]),
            alignment: new FormControl(miHero.alignment, [Validators.required]),
            powerstats: new FormGroup({
              id: new FormControl(miHero.powerstats?.id || null, []),
              intelligence: new FormControl(miHero.powerstats?.intelligence, [Validators.required]),
              strength: new FormControl(miHero.powerstats?.strength, [Validators.required]),
              speed: new FormControl(miHero.powerstats?.speed, [Validators.required]),
              durability: new FormControl(miHero.powerstats?.durability, [Validators.required]),
              power: new FormControl(miHero.powerstats?.power, [Validators.required]),
              combat: new FormControl(miHero.powerstats?.combat, [Validators.required])
            })
          }, []);


        } else {
          alert("No se encuantra el usuario");
        }

      }

    });
  }
}
