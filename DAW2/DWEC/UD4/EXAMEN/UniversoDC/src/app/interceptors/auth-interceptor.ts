import { HttpInterceptorFn } from '@angular/common/http';

//!!!
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const cloneRequest = req.clone({
    setHeaders: {
      'Content-type': 'aplication/json',
      'Authorization': localStorage.getItem('token') || '',
      
    }
  });

  if (cloneRequest.url.includes("auth")) {
    return next(req);
  }
  else {
    return next(cloneRequest);
  };
};
