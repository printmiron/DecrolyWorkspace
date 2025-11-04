import { Component, signal } from '@angular/core';
import { PorductCard } from "./cards/porduct-card/porduct-card";
import { FinalCard } from "./cards/final-card/final-card";



@Component({
  selector: 'app-root',
  imports: [PorductCard, FinalCard],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('CarritoAngular');
}
