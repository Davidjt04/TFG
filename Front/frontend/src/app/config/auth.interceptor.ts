// import { HttpInterceptorFn } from '@angular/common/http';

// export const AuthInterceptor: HttpInterceptorFn = (req, next) => {
//   const token = localStorage.getItem('token');

//   if (token) {
//     const cloned = req.clone({
//       setHeaders: {
//         Authorization: `Bearer ${token}`,
//       },
//     });
//     return next(cloned);
//   }

//   return next(req);
// }
import { HttpInterceptorFn } from '@angular/common/http';

export const AuthInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('token');

  console.log('📡 AuthInterceptor interceptando request:');
  console.log('   URL:', req.url);
  console.log('   Headers antes:', req.headers.keys());
  console.log('   Token:', token);

  if (token) {
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
    console.log('   Headers después:', cloned.headers.keys(), cloned.headers.get('Authorization'));
    return next(cloned);
  }

  console.log('   No hay token, request sin Authorization');
  return next(req);
};
