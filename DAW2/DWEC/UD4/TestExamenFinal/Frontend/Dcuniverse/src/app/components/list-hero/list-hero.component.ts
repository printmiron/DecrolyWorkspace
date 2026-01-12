import { Component, inject } from '@angular/core';
import { HeroService } from '../../service/hero.service';
import { HeroI } from '../../interfaces/hero.interface';
import { CardHeroComponent } from "../card-hero/card-hero.component";

@Component({
  selector: 'app-list-hero',
  imports: [CardHeroComponent],
  templateUrl: './list-hero.component.html',
  styleUrl: './list-hero.component.css',
})
export class ListHeroComponent {
  serviceHero = inject(HeroService);
  arrHeros: HeroI[];

  constructor() {
    this.arrHeros = []
  }

  async ngOnInit(): Promise<void> {
    try {
      this.arrHeros = await this.serviceHero.getAllHero();
    } catch (error) {
      console.log("error al obtener los heros | " + error);
    }
  }
}



