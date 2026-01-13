import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptorInterceptor: HttpInterceptorFn = (req, next) => {
    // 1. Obtenemos el token
    const token = localStorage.getItem("accessToken");

    // 2. Clonamos con los datos correctos
    const cloneRequest = req.clone({
        setHeaders: {
            'Content-type': 'application/json',
            'Authorization': localStorage.getItem("accessToken") || ""
        }
    });

    // 3. Logica de exclusión para el login
    if (cloneRequest.url.includes("auth")) {
        return next(req);
    } else {
        return next(cloneRequest);
    }
};
