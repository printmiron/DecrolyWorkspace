import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptorInterceptor: HttpInterceptorFn = (req, next) => {
  // 1. Obtenemos el token
    const token = localStorage.getItem("accessToken");  
  
  // 2. Clonamos con los datos correctos
    const cloneRequest = req.clone({
        setHeaders: {
            'Content-Type': 'application/json', // Corregido: 'pp'
            'Authorization': token ? `Bearer ${token}` : "" // Corregido: Formato Bearer
        }
    });

    // 3. Tu lógica de exclusión para el login
    if (req.url.includes("auth")) {
        return next(req);
    } else {
        return next(cloneRequest);
    }
};
