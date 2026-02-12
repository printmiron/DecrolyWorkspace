import { Routes } from '@angular/router';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { loginGuard } from './guardas/login-guard';
import { NinjaListComponent } from './components/ninja-list/ninja-list.component';
import { NuevoNinjaComponent } from './pages/nuevo-ninja/nuevo-ninja.component';
import { NinjaViewComponent } from './pages/ninja-view/ninja-view.component';

export const routes: Routes = [

    { path: '', pathMatch: 'full', redirectTo: 'landingPage' },

    { path: 'landingPage', component: LandingPageComponent },
    { path: 'login', component: LoginComponent },

    {
        path: 'home', component: HomeComponent, canActivate: [loginGuard], children: [
            
            { path: '', component: NinjaListComponent },
            { path: 'nuevoNinja', component: NuevoNinjaComponent },
            { path: 'ninja/:id', component: NinjaViewComponent },
            { path: 'form/:id', component: NuevoNinjaComponent },
        ]
    },
    
    { path: "**", redirectTo: "landingPage" }

];
