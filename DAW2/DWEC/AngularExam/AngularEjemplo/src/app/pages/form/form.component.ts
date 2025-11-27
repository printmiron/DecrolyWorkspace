import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductoI } from '../../interface/producto-i.interface';
import { ProductoSService } from '../../service/producto-s.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-form',
  imports: [ReactiveFormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css',
})
export class FormComponent {

  registerProducto: FormGroup;

  productoService = inject(ProductoSService);
  activatedRoute = inject(ActivatedRoute);
  router = inject(Router);

  nuevoProducto: boolean;

  constructor() {


    this.nuevoProducto = true;

    this.registerProducto = new FormGroup({

      id: new FormControl(-1),
      title: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      subtitle: new FormControl(null, [Validators.required]),
      descripcion: new FormControl(null, [Validators.required]),
      image: new FormControl(null, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),

      // edad: new FormControl(null, [Validators.required, Validators.min(0), Validators.max(120)]),
      // email: new FormControl(null, [Validators.required, Validators.pattern(/^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/)]),

    }, []);
  }


  Control(formControlName: string, validator: string): boolean | undefined {
    return this.registerProducto.get(formControlName)?.hasError(validator) && this.registerProducto.get(formControlName)?.touched
  }



  //recoger todos los datos del formulario
  getDataFormulario() {
 
    let producto = this.registerProducto.value as ProductoI;

    if (this.nuevoProducto) {
      //insertamos uno nuevo
      this.productoService.addProducto(producto);
    }else{
      //!!
      //actualizamos ya existente
      this.productoService.updatePorducto(producto);
    }
    



    this.registerProducto.reset();
  }


  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params: any) => {

      let miId = Number(params.id);

      if (miId != undefined) {

        let miProducto = this.productoService.getById(miId);

        if (miProducto != undefined) {
          this.nuevoProducto = false;
          this.registerProducto = new FormGroup({

            id: new FormControl(-1),
            title: new FormControl(miProducto.title, [Validators.required, Validators.minLength(4)]),
            subtitle: new FormControl(miProducto.subtitle, [Validators.required]),
            descripcion: new FormControl(miProducto.descripcion, [Validators.required]),
            image: new FormControl(miProducto.image, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),

          }, []);
        }

      }


    });
  }



}
