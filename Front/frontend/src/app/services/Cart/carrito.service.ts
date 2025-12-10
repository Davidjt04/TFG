
// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable, BehaviorSubject, tap } from 'rxjs';
// import { CartHasArticle } from '../../entities/CartHasArticle';
// import { Cart } from '../../entities/Cart';
// import { CartArticleDto } from '../../entities/CartArticleDto';
// import { AuthService } from '../Auth/auth.service';

// @Injectable({
//   providedIn: 'root'
// })
// export class CarritoService {
//   private apiUrl = 'http://localhost:8081/CLIENTE';
//   private cartItemsSubject = new BehaviorSubject<CartHasArticle[]>([]);
//   public cartItems$ = this.cartItemsSubject.asObservable();

// constructor(private http: HttpClient, private authService: AuthService) { }

  // // Obtener carrito completo del usuario
  // getCarritoPorUsuario(idUsuario: number): Observable<Cart> {
  //   const token = localStorage.getItem('token');
  //   const headers = { Authorization: `Bearer ${token}` };
  //   return this.http.get<Cart>(`${this.apiUrl}/carrito/${idUsuario}`, { headers });
  // }

//   // Listar artículos en el carrito
//   // getCartClientePorUsuario(userId: number): Observable<CartHasArticle[]> {
//   //   return this.http.get<CartHasArticle[]>(`${this.apiUrl}/carrito/${userId}/articulos`).pipe(
//   //     tap(items => this.cartItemsSubject.next(items))
//   //   );
//   // }
//   getCartClientePorUsuario(userId: number): Observable<CartHasArticle[]> {
//     return this.http.get<CartHasArticle[]>(`${this.apiUrl}/carrito/${userId}/articulos`).pipe(
//       tap(items => this.cartItemsSubject.next(items))
//     );
//   }

//   // Agregar artículo
//   agregarArticulo(dtoParcial: { idArticulo: number; cantidad: number }): Observable<any> {
//     const token = localStorage.getItem('token');
//     if (!token) throw new Error("No se encuentra el token del usuario");

//     const payload = JSON.parse(atob(token.split('.')[1]));
//     const idUsuario = payload.idUsuario;

//     const dto: CartArticleDto = {
//       idUsuario,
//       idArticulo: dtoParcial.idArticulo,
//       cantidad: dtoParcial.cantidad
//     };

//     return this.http.post<any>(`${this.apiUrl}/carrito/add`, dto).pipe(
//       tap(() => this.getCartClientePorUsuario(idUsuario).subscribe())
//     );
//   }

//   // Borrar artículo (si implementas endpoint)
//   borrarArticuloCliente(item: CartHasArticle): Observable<void> {
//     return this.http.delete<void>(
//       `${this.apiUrl}/carrito/${item.cart.idCarrito}/articulo/${item.article.idArticulo}`
//     ).pipe(
//       tap(() => {
//         const token = localStorage.getItem('token');
//         const payload = token ? JSON.parse(atob(token.split('.')[1])) : null;
//         if (payload) this.getCartClientePorUsuario(payload.idUsuario).subscribe();
//       })
//     );
//   }
//   // carrito.service.ts
// // actualizarCantidad(idCarrito: number, idArticulo: number, nuevaCantidad: number) {
// //   const token = localStorage.getItem('token');
// //   if (!token) throw new Error("No se encuentra el token del usuario");

// //   const payload = JSON.parse(atob(token.split('.')[1]));
// //   const idUsuario = payload.idUsuario;

// //   return this.http.put(
// //     `${this.apiUrl}/carrito/${idCarrito}/articulo/${idArticulo}`,
// //     { cantidad: nuevaCantidad },
// //     { headers: { Authorization: `Bearer ${token}` } }
// //   ).pipe(
// //     tap(() => this.getCartClientePorUsuario(idUsuario).subscribe())
// //   );
// // }
// actualizarCantidad(idCarrito: number, idArticulo: number, nuevaCantidad: number) {
//   return this.http.put<CartHasArticle>(
//     `http://localhost:8081/CLIENTE/carrito/${idCarrito}/articulo/${idArticulo}?cantidad=${nuevaCantidad}`,
//     {}
//   ).pipe(
//     tap(() => this.getCartClientePorUsuario(this.authService.getUserId())) // refresca observable
//   );
// }




// }
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, tap, switchMap } from 'rxjs';
import { CartHasArticle } from '../../entities/CartHasArticle';
import { AuthService } from '../Auth/auth.service';
import { Cart } from '../../entities/Cart';
import { CartArticleDto } from '../../entities/CartArticleDto';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private apiUrl = 'http://localhost:8081/CLIENTE';
  private cartItemsSubject = new BehaviorSubject<CartHasArticle[]>([]);
  public cartItems$ = this.cartItemsSubject.asObservable();

  constructor(private http: HttpClient, private authService: AuthService) { }

  getCartClientePorUsuario(userId: number): Observable<CartHasArticle[]> {
    return this.http.get<CartHasArticle[]>(`${this.apiUrl}/carrito/${userId}/articulos`).pipe(
      tap(items => this.cartItemsSubject.next(items))
    );
  }

  borrarArticuloCliente(item: CartHasArticle): Observable<void> {
    const token = localStorage.getItem('token');
    if (!token) throw new Error("No se encuentra el token del usuario");

    return this.http.delete<void>(
      `${this.apiUrl}/carrito/${item.cart.idCarrito}/articulo/${item.article.idArticulo}`,
      { headers: { Authorization: `Bearer ${token}` } }
    ).pipe(
      tap(() => {
        const userId = this.authService.getUserId();
        this.getCartClientePorUsuario(userId).subscribe();
      })
    );
  }

  actualizarCantidad(idCarrito: number, idArticulo: number, nuevaCantidad: number) {
    const token = localStorage.getItem('token');
    if (!token) throw new Error('Token no encontrado');

    return this.http.put<CartHasArticle>(
      `${this.apiUrl}/carrito/${idCarrito}/articulo/${idArticulo}?cantidad=${nuevaCantidad}`,
      {},
      { headers: { Authorization: `Bearer ${token}` } }
    ).pipe(
      tap(() => this.getCartClientePorUsuario(this.authService.getUserId()))
    );
  }
    // Obtener carrito completo del usuario
  getCarritoPorUsuario(idUsuario: number): Observable<Cart> {
    const token = localStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };
    return this.http.get<Cart>(`${this.apiUrl}/carrito/${idUsuario}`, { headers });
  }

  //   // Agregar artículo
  // agregarArticulo(dtoParcial: { idArticulo: number; cantidad: number }): Observable<any> {
  //   const token = localStorage.getItem('token');
  //   if (!token) throw new Error("No se encuentra el token del usuario");

  //   const payload = JSON.parse(atob(token.split('.')[1]));
  //   const idUsuario = payload.idUsuario;

  //   const dto: CartArticleDto = {
  //     idUsuario,
  //     idArticulo: dtoParcial.idArticulo,
  //     cantidad: dtoParcial.cantidad
  //   };

  //   return this.http.post<any>(`${this.apiUrl}/carrito/add`, dto).pipe(
  //     tap(() => this.getCartClientePorUsuario(idUsuario).subscribe())
  //   );
  // }
//   agregarArticulo(dtoParcial: { idArticulo: number; cantidad: number }): Observable<any> {
//   const token = localStorage.getItem('token');
//   if (!token) throw new Error("No se encuentra el token del usuario");

//   const payload = JSON.parse(atob(token.split('.')[1]));
//   const idUsuario = payload.idUsuario;

//   const dto: CartArticleDto = {
//     idUsuario,
//     idArticulo: dtoParcial.idArticulo,
//     cantidad: dtoParcial.cantidad
//   };

//   // ✅ Agregamos headers Authorization
//   return this.http.post<any>(
//     `${this.apiUrl}/carrito/add`,
//     dto,
//     { headers: { Authorization: `Bearer ${token}` } }
//   ).pipe(
//     tap(() => this.getCartClientePorUsuario(idUsuario).subscribe())
//   );
// }
agregarArticulo(dtoParcial: { idArticulo: number; cantidad: number }): Observable<CartHasArticle[]> {
  const token = localStorage.getItem('token');
  if (!token) throw new Error("No se encuentra el token del usuario");

  const payload = JSON.parse(atob(token.split('.')[1]));
  const idUsuario = payload.idUsuario;

  const dto: CartArticleDto = {
    idUsuario,
    idArticulo: dtoParcial.idArticulo,
    cantidad: dtoParcial.cantidad
  };

  return this.http.post<any>(`${this.apiUrl}/carrito/add`, dto, { headers: { Authorization: `Bearer ${token}` } })
    .pipe(
      switchMap(() => this.getCartClientePorUsuario(idUsuario)) // devuelve el carrito actualizado
    );
}

}



