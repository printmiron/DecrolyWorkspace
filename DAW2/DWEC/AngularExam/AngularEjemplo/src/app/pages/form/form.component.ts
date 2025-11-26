import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductoI } from '../../interface/producto-i.interface';
import { ProductoSService } from '../../service/producto-s.service';

@Component({
  selector: 'app-form',
  imports: [ReactiveFormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css',
})
export class FormComponent {

  registerProducto: FormGroup;
  
  productoService = inject (ProductoSService);


  constructor(){

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
  getDataFormulario(){
    //!!!
    let producto = this.registerProducto.value as ProductoI;

    this.productoService.addProducto(producto);
    this.registerProducto.reset();
  }

  

}
