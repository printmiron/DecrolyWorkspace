import { Component, inject } from '@angular/core';
import { HeroService } from '../../service/hero.service';
import { HeroI } from '../../interfaces/hero.interface';
import { CardHeroComponent } from "../card-hero/card-hero.component";
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-list-hero',
  imports: [CardHeroComponent, FormsModule],
  templateUrl: './list-hero.component.html',
  styleUrl: './list-hero.component.css',
})
export class ListHeroComponent {
  serviceHero = inject(HeroService);
  arrHeros: HeroI[];

  currentPage: number = 0;
  pageSize: number = 4;

  filtroPorPower: number = 0;

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

  //funcion para traer solo 4 heroes a la pagina
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

}




