import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-form',
  imports: [],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css',
})
export class FormComponent {

  modelForm: FormGroup;
  private id: number = 0;

  constructor(){

    this.modelForm = new FormGroup({

      id: new FormControl(-1),
      title: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      subtitle: new FormControl(null, [Validators.required, Validators.minLength(10)]),
      descripcion: new FormControl(null, [Validators.required]),
      image: new FormControl(null, [Validators.required]),
      

    }, []);
  }


  Control(formControlName: string, validator: string): boolean | undefined {
        return this.modelForm.get(formControlName)?.hasError(validator) && this.modelForm.get(formControlName)?.touched
    }

  getDataFormulario(){
    //!!!
    let producto = 
  }

}
