import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { FormComponent } from './pages/form/form.component';
import { ListComponent } from './pages/list/list.component';
import { ProductoViewComponent } from './pages/producto-view/producto-view.component';

export const routes: Routes = [

    //ruta por defecto
    { path : '', pathMatch: 'full', redirectTo: 'home' },

    //rutas para poder acceder desde navbar
    { path : 'home', component: HomeComponent },
    { path : 'form', component: FormComponent },
    { path : 'list', component: ListComponent },

    //aceder al los detalles de los productos
    { path : 'producto/:url', component: ProductoViewComponent },

    //si no ecuntra la ruta va a home
    { path : '**', redirectTo: 'home' },


];
