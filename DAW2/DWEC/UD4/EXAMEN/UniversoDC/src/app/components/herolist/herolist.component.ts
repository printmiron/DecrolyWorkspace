import { Component, inject } from '@angular/core';
import { HeroService } from '../../services/hero.service';
import { HeroI } from '../../interfaces/hero.interface';
import { FormsModule } from '@angular/forms';
import { HerocardComponent } from "../herocard/herocard.component";

@Component({
  selector: 'app-herolist',
  imports: [FormsModule, HerocardComponent],
  templateUrl: './herolist.component.html',
  styleUrl: './herolist.component.css',
})
export class HerolistComponent {
  serviceHero = inject(HeroService);
  arrHeros: HeroI[];

  currentPage: number = 0;
  pageSize: number = 4;

  filtroPorPower: number = 0;
  filtroPorNombre: string = "";

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

  get herosPaginados(): HeroI[] {
    const inicio = this.currentPage * this.pageSize;
    const fin = inicio + this.pageSize;

    return this.arrHeros.slice(inicio, fin);
  }

  cambiarPagina(valor: number) {
    this.currentPage += valor;
  }


  async filtrarDirecto() {
    if (this.filtroPorPower > 0) {

      this.arrHeros = await this.serviceHero.getHeroByPower(this.filtroPorPower);

    } else {

      this.arrHeros = await this.serviceHero.getAllHero();

    }
    this.currentPage = 0;
  }

  async filtraNombre() {

    if (this.filtroPorNombre.trim() !== "") {

      this.arrHeros = await this.serviceHero.getHeroByName(this.filtroPorNombre);

    } else {

      this.arrHeros = await this.serviceHero.getAllHero();

    }
    this.currentPage = 0;
  }


}
