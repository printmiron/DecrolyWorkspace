import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { NuevoHeroComponent } from './pages/nuevo-hero/nuevo-hero.component';
import { HeroViewComponent } from './pages/hero-view/hero-view.component';

export const routes: Routes = [

    { path: '', pathMatch: 'full', redirectTo: 'home' },

    { path : 'home', component: HomeComponent },
    { path : 'nuevoHero', component: NuevoHeroComponent },

    { path : 'hero/:id', component: HeroViewComponent },
    { path : 'form/:id', component: NuevoHeroComponent },
    
];
