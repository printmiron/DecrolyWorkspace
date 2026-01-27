import { Component } from '@angular/core';
import { ListHeroComponent } from "../../components/list-hero/list-hero.component";
import { HeroI } from '../../interfaces/hero.interface';
import { RouterOutlet } from "@angular/router";
import { NavbarComponent } from "../../components/navbar/navbar.component";

@Component({
  selector: 'app-home',
  imports: [ RouterOutlet],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  

}
