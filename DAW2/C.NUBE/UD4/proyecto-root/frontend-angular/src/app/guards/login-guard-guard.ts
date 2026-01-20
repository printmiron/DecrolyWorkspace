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
