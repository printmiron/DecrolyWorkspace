import { Component, inject, model } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductService } from '../../service/product.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ProductoI } from '../../interface/producto.interface';

@Component({
  selector: 'app-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css',
})
export class FormComponent {
  registrarProducto: FormGroup;

  productService = inject(ProductService);
  activeRouter = inject(ActivatedRoute);
  router = inject(Router);

  nuevoProducto: boolean;



  constructor() {
    this.nuevoProducto = true;

    this.registrarProducto = new FormGroup({
      id: new FormControl(-1),
      name: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      description: new FormControl(null, [Validators.required]),
      price: new FormControl(null, [Validators.required]),
      category: new FormControl(null, [Validators.required]),
      image: new FormControl(null, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
      //!!!!!!!!!!! si no lo comento estoy obligado a introducirlo con el formualrio para poder añadir el producto (el boton no se va activar)
      //!! comentarlo -> asi yo puedo añadir y editar los productos que estoy creando
      // active: new FormControl(null, [Validators.required])
    }, []);

  }



  Control(formControlName: string, validator: string): boolean | undefined {
    return this.registrarProducto.get(formControlName)?.hasError(validator) && this.registrarProducto.get(formControlName)?.touched
  }

  getDataFormulario() {
    let producto = this.registrarProducto.value as ProductoI;

    if (this.nuevoProducto) {
      //insertamos nuevo si no existe
      producto.id = -1;
      this.productService.addPorduct(producto);
    } else {
      //si existe
      this.productService.updateProducto(producto);
    }

    this.registrarProducto.reset();
    this.router.navigate(['/list']);

  }

  ngOnInit(): void {

    this.activeRouter.params.subscribe((params: any) => {

      let miId = Number(params.id);

      if (miId != undefined) {

        let miProducto = this.productService.getById(miId);

        if (miProducto && miProducto.name !== "Producto no encontrado") {
          
          this.nuevoProducto = false;

          this.registrarProducto = new FormGroup({
            id: new FormControl(miProducto.id),
            name: new FormControl(miProducto.name, [Validators.required, Validators.minLength(4)]),
            description: new FormControl(miProducto.description, [Validators.required]),
            price: new FormControl(miProducto.price, [Validators.required]),
            category: new FormControl(miProducto.category, [Validators.required]),
            image: new FormControl(miProducto.image, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
            //!!!!!!!!!!!
            // active: new FormControl(miProducto.active, [Validators.required])
          }, []);

        }

      }
    });

  }

}
