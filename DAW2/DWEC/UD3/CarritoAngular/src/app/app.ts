import { Component, signal } from '@angular/core';
import { ListComponent } from "./components/list-component/list-component";
import { TotalComponent } from "./components/total-component/total-component";



@Component({
  selector: 'app-root',
  imports: [ListComponent, TotalComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('CarritoAngular');
}
