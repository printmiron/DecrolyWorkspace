import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductoI } from '../../interface/producto-i.interface';
import { ProductoSService } from '../../service/producto-s.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CategoriasI } from '../../interface/categorias-i.interface';

@Component({
  selector: 'app-form',
  imports: [ReactiveFormsModule, RouterLink],
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
      categoria: new FormControl(null, [Validators.required]),
      price: new FormControl(null, [Validators.required])
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
      producto.id = -1;
      this.productoService.addProducto(producto);
    }else{
      //!!
      //actualizamos ya existente
      this.productoService.updatePorducto(producto);
    }
  

    this.registerProducto.reset();
    this.router.navigate(['/list']);
  }


  ngOnInit(): void {

    

    this.activatedRoute.params.subscribe((params: any) => {

      let miId = Number(params.id);

      if (miId != undefined) {

        let miProducto = this.productoService.getById(miId);

        //como tenemos en servicio un producto generado manualmente cunado buscamos por id, para pagina si no encuantra el poroducto "ej: /producto/10" que no existe
        //nos manda un mensaje de error, y el formualrio por defecto toma este valor
        //aqui que estoy haciendo "miProducto && miProducto.title !== "Producto no encontrado" es tomar este valor como flase si no es un objeto con titulo "Producto no encontrado"
        if (miProducto && miProducto.title !== "Producto no encontrado") {
          this.nuevoProducto = false;
          this.registerProducto = new FormGroup({

            id: new FormControl(miProducto.id),
            title: new FormControl(miProducto.title, [Validators.required, Validators.minLength(4)]),
            subtitle: new FormControl(miProducto.subtitle, [Validators.required]),
            descripcion: new FormControl(miProducto.descripcion, [Validators.required]),
            image: new FormControl(miProducto.image, [Validators.required, Validators.pattern(/^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/)]),
            categoria: new FormControl(miProducto.categoria, [Validators.required]),
            price: new FormControl(miProducto.price, [Validators.required])

          }, []);
        }

      }


    });
  }



}
