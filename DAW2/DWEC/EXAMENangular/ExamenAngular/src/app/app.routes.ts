import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { FormComponent } from './pages/form/form.component';
import { ListComponent } from './pages/list/list.component';
import { ProductViewComponent } from './pages/product-view/product-view.component';
import { Page404Component } from './pages/page404/page404.component';

export const routes: Routes = [

    { path : '', pathMatch: 'full', redirectTo: 'home' },

    { path: 'home', component: HomeComponent },
    { path: 'form', component: FormComponent },
    { path: 'list', component: ListComponent },

    { path : 'producto/:id', component: ProductViewComponent },
    { path : 'form/:id', component: FormComponent },

    { path : '**', component: Page404Component },

];
