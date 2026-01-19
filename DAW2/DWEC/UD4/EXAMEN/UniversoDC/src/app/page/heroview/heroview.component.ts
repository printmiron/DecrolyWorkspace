import { Component, inject } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { HeroI } from '../../interfaces/hero.interface';
import { HeroService } from '../../services/hero.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-heroview',
  imports: [RouterLink],
  templateUrl: './heroview.component.html',
  styleUrl: './heroview.component.css',
})
export class HeroviewComponent {
  miHero !: HeroI;
  serviceHero = inject(HeroService);
  activatedRoute = inject(ActivatedRoute);
  router = inject(Router);

  ngOnInit(): void {
    //Usando el endpoint específico para obtener heroe por id
    this.activatedRoute.params.subscribe(async (params: any) => {

      let id: number = params.id
      console.log(id)

      if (id != undefined) {
        let response = await this.serviceHero.getHeroById(id);
        console.log(response)

        if (response != undefined) {
          this.miHero = response;

        }

      }

    });

  }


  async deleteHero(hero: HeroI) {

    Swal.fire({
      title: "¿Estás seguro que quieres eliminar a " + hero.heroname + "?",
      text: "Esta acción no se puede deshacer",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#d33",
      cancelButtonColor: "#3085d6",
      confirmButtonText: "Sí, ¡borrar!",
      cancelButtonText: "Cancelar"
    }).then(async (result) => {

      if (result.isConfirmed) {
        try {

          await this.serviceHero.deleteById(hero.id);


          Swal.fire({
            title: "¡Borrado!",
            text: "Se ha eliminado el héroe: " + hero.heroname,
            icon: "success"
          }).then(() => {

            this.router.navigate(['/dashboard'])
          });

        } catch (error) {

          Swal.fire({
            icon: "error",
            title: "Error",
            text: "No se pudo eliminar el héroe."
          });
        }
      }
    });
  }
}
