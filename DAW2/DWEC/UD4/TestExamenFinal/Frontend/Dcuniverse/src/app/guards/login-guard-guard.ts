import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const loginGuardGuard: CanActivateFn = (route, state) => {
   const router = inject(Router);

    let isAuth: boolean = false;
    //True deja navegar a la ruta
    //False bloquea el acceso a la ruta

    if (localStorage.getItem('accessToken')) {
        isAuth = true;
    }
    else {
        router.navigate(['/login']);
    }

    return isAuth;
};



// export const roleGuard: CanActivateFn = (route, state) => {
//   const router = inject(Router);
//   const token = localStorage.getItem('accessToken');
  
//   // Supongamos que guardas los roles en localStorage al hacer login
//   const userRoles = JSON.parse(localStorage.getItem('roles') || '[]');
//   const requiredRole = route.data['expectedRole']; // Definido en tu app-routing.module

//   if (token && userRoles.includes(requiredRole)) {
//     return true;
//   } else {
//     router.navigate(['/login']);
//     return false;
//   }
// };

//----------------------------------

// import { inject } from '@angular/core';
// import { CanActivateFn, Router } from '@angular/router';

// export const authGuard: CanActivateFn = (route, state) => {
//   const router = inject(Router);
//   const token = localStorage.getItem('accessToken');

//   if (token) {
//     return true; // Hay token, puedes pasar
//   } else {
//     // No hay token, te mando al login
//     router.navigate(['/login']);
//     return false;
//   }
// };
