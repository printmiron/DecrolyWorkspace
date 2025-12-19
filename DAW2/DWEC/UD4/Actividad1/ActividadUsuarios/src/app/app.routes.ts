import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { NuevoUsuarioComponent } from './pages/nuevo-usuario/nuevo-usuario.component';
import { UserViewComponent } from './pages/user-view/user-view.component';


export const routes: Routes = [

    { path: '', pathMatch: 'full', redirectTo: 'home' },

    { path : 'home', component: HomeComponent },
    { path : 'nuevoUsuario', component: NuevoUsuarioComponent },
    
    { path : 'user/:_id', component: UserViewComponent },

    { path : 'form/:_id', component: NuevoUsuarioComponent },
];
