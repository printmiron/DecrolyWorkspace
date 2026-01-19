import { Routes } from '@angular/router';
import { LandingPageComponent } from './page/landing-page/landing-page.component';
import { LoginComponent } from './page/login/login.component';
import { DashboardComponent } from './page/dashboard/dashboard.component';
import { loginGuard } from './guards/login-guard';
import { HerolistComponent } from './components/herolist/herolist.component';
import { HeroformComponent } from './components/heroform/heroform.component';
import { HeroviewComponent } from './components/heroview/heroview.component';

export const routes: Routes = [

    { path: '', pathMatch: 'full', redirectTo: 'landingPage' },

    { path: 'landingPage', component: LandingPageComponent },
    { path: 'login', component: LoginComponent },

    {
        path: 'dashboard', component: DashboardComponent, canActivate: [loginGuard], children: [

            { path: '', component: HerolistComponent },
            { path: 'heroForm', component: HeroformComponent },
            { path: 'hero/:id', component: HeroviewComponent },
            { path: 'form/:id', component: HeroformComponent },
        ]
    },

    { path: "**", redirectTo: "landingPage" }
];
