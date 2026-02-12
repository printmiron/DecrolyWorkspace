import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem("token") || "";

  const cloneRequest = req.clone({
    setHeaders: {
      'Content-type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
  });

  if (cloneRequest.url.includes("auth")) {
    return next(req);
  } else {
    return next(cloneRequest);
  }
};
