import { Component, inject, Input } from '@angular/core';
import { HeroService } from '../../service/hero.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { HeroI } from '../../interfaces/hero.interface';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-card-hero',
  imports: [RouterLink],
  templateUrl: './card-hero.component.html',
  styleUrl: './card-hero.component.css',
})
export class CardHeroComponent {
  serviceHero = inject(HeroService);
  router = inject(Router);
  activatedRoute = inject(ActivatedRoute);
  @Input() miHero!: HeroI;

  

  ngOnInit(): void {
    //Usando el endpoint específico para obtener usuario por id
    this.activatedRoute.params.subscribe(async (params: any) => {

      //!id: string si uuid numer si id simple
      let id: string = params.id
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
    // 1. Primero preguntamos al usuario
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

      // 2. Si el usuario confirma, entonces llamamos al servicio
      if (result.isConfirmed) {
        try {
          // Llamamos al borrado
          //!funccion para borrar
          await this.serviceHero.deleteById(hero.id);

          // 3. Mostramos el mensaje de éxito
          Swal.fire({
            title: "¡Borrado!",
            text: "Se ha eliminado el héroe: " + hero.heroname,
            icon: "success"
          }).then(() => {
            // 4. Opcional: Redirigir o recargar la lista
            window.location.reload(); // O this.router.navigate(['/home']);
          });

        } catch (error) {
          // Si el backend da error (ej. por las Powerstats)
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
