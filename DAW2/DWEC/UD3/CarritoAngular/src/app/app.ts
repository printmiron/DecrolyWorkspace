import { Component, signal } from '@angular/core';
import { PorductCard } from "./cards/porduct-card/porduct-card";



@Component({
  selector: 'app-root',
  imports: [PorductCard],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('CarritoAngular');
}
