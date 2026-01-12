import { Component, inject, Input } from '@angular/core';
import { HeroService } from '../../service/hero.service';
import { Router, RouterLink } from '@angular/router';
import { HeroI } from '../../interfaces/hero.interface';

@Component({
  selector: 'app-card-hero',
  imports: [RouterLink],
  templateUrl: './card-hero.component.html',
  styleUrl: './card-hero.component.css',
})
export class CardHeroComponent {
  serviceHero = inject(HeroService);
  router = inject(Router);
  @Input() miHero!: HeroI;


  
}
