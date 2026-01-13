import { Component } from '@angular/core';
import { ListHeroComponent } from "../../components/list-hero/list-hero.component";
import { HeroI } from '../../interfaces/hero.interface';

@Component({
  selector: 'app-home',
  imports: [ListHeroComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  

}
