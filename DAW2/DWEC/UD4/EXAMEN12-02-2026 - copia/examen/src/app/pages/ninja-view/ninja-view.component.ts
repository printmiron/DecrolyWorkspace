import { Component, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { NinjasI } from '../../interfaces/ninjas.interface';
import { NinjaService } from '../../services/ninja.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-ninja-view',
  imports: [RouterLink],
  templateUrl: './ninja-view.component.html',
  styleUrl: './ninja-view.component.css',
})
export class NinjaViewComponent {
  miNinja !: NinjasI;
  serviceNinja = inject(NinjaService);
  activatedRoute = inject(ActivatedRoute);


  ngOnInit(): void {

    this.activatedRoute.params.subscribe(async (params: any) => {
      
      let id: number = params.id
      console.log(id)

      if (id != undefined) {
        let response = await this.serviceNinja.getNinjaById(id);
        console.log(response)

        if (response != undefined) {
          this.miNinja = response;

        }

      }

    });

  }


   async deleteNinja(ninja: NinjasI) {
      // 1. Primero preguntamos al usuario
      Swal.fire({
        title: "¿Estás seguro que quieres eliminar a " + ninja.ninjaname + "?",
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
  
            await this.serviceNinja.deleteById(ninja.id);
  
            // 3. Mostramos el mensaje de éxito
            Swal.fire({
              title: "¡Borrado!",
              text: "Se ha eliminado el ninja: " + ninja.ninjaname,
              icon: "success"
            }).then(() => {
              // 4. Redirigir o recargar la lista
              window.location.reload();
            });
  
          } catch (error) {
            Swal.fire({
              icon: "error",
              title: "Error",
              text: "No se pudo eliminar el ninja."
            });
          }
        }
      });
    }
}
