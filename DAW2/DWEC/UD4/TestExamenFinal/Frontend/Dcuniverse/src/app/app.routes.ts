import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { NuevoHeroComponent } from './pages/nuevo-hero/nuevo-hero.component';
import { HeroViewComponent } from './pages/hero-view/hero-view.component';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { LoginComponent } from './pages/login/login.component';
import { loginGuardGuard } from './guards/login-guard-guard';
import { ListHeroComponent } from './components/list-hero/list-hero.component';

export const routes: Routes = [

    { path: '', pathMatch: 'full', redirectTo: 'landingPage' },

    { path: 'landingPage', component: LandingPageComponent },
    { path: 'login', component: LoginComponent },

    {
        path: 'home', component: HomeComponent, canActivate: [loginGuardGuard], children: [
            
            { path: '', component: ListHeroComponent },
            { path: 'nuevoHero', component: NuevoHeroComponent },
            { path: 'hero/:id', component: HeroViewComponent },
            { path: 'form/:id', component: NuevoHeroComponent },
        ]
    },
    
    { path: "**", redirectTo: "landingPage" }

];
