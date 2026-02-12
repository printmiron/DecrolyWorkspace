import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptorInterceptor: HttpInterceptorFn = (req, next) => {
    // // 1. Obtenemos el token
    // const token = localStorage.getItem("accessToken");
    

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

// 'Authorization': 'Bearer ' + localStorage.getItem("accessToken")
// // 2. Si hay token, clonamos la petición y le añadimos el "Bearer "
//     const cloneRequest = req.clone({
//         setHeaders: {
//             'Authorization': `Bearer ${token}` // Estándar de la industria
//         }
//     });



//------------------------------------
// // Estructura conceptual del interceptor basada en el PDF [cite: 152]
// intercept(req: HttpRequest<any>, next: HttpHandler) {
//   const token = localStorage.getItem("token"); // [cite: 142]
  
//   if (token) {
//     req = req.clone({
//       setHeaders: {
//         Authorization: `Bearer ${token}` // 
//       }
//     });
//   }
//   return next.handle(req);
// }

//-------------------
// import { HttpInterceptorFn } from '@angular/common/http';

// export const authInterceptor: HttpInterceptorFn = (req, next) => {
//   // 1. Buscamos el token en el storage
//   const token = localStorage.getItem('accessToken');

//   // 2. Si existe, clonamos la petición y le ponemos la cabecera "Authorization"
//   if (token) {
//     const cloned = req.clone({
//       setHeaders: {
//         Authorization: `Bearer ${token}`
//       }
//     });
//     return next(cloned);
//   }

//   // 3. Si no hay token, la petición sigue su curso normal
//   return next(req);
// };
