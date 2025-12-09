// import { Injectable } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Observable, BehaviorSubject, tap } from 'rxjs';
// import { CartHasArticle } from '../../entities/CartHasArticle';
// import { Cart } from '../../entities/Cart';

// interface CartItemDto {
//   cartId: number;
//   articleId: number;
//   quantity: number;
// }

// @Injectable({
//   providedIn: 'root'
// })
// export class CarritoService {

//   private apiUrl = 'http://localhost:8081/CartHasArticle';
//   // Estado reactivo del carrito
//   private cartItemsSubject = new BehaviorSubject<CartHasArticle[]>([]);
//   public cartItems$ = this.cartItemsSubject.asObservable();

//   constructor(private http: HttpClient) {}

//   getCartCliente(): Observable<CartHasArticle[]> {
//     return this.http.get<CartHasArticle[]>(`${this.apiUrl}/CLIENTE/usuario`).pipe(
//       tap(items => this.cartItemsSubject.next(items))
//     );
//   }

//   agregarArticuloClienteDto(cartId: number, articleId: number, quantity: number): Observable<any> {
//     const dto: CartItemDto = { cartId, articleId, quantity };
//     console.log('LOG ANGULAR SERVICE DTO: Enviando DTO al backend:', dto);
    
//     return this.http.post<any>(`${this.apiUrl}/CLIENTE/guardar`, dto).pipe(
//       // Después de guardar exitosamente, actualizamos el estado local
//       tap(() => this.getCartCliente().subscribe())
//     );
//   }

//   borrarArticuloCliente(item: CartHasArticle): Observable<void> {
//     return this.http.delete<void>(
//       `${this.apiUrl}/CLIENTE/borrar/${item.cart.idCarrito}/${item.article.idArticulo}`
//     ).pipe(
//       // Después de borrar exitosamente, actualizamos el estado local
//       tap(() => this.getCartCliente().subscribe())
//     );
//   }

//   // getCarritoPorUsuario(idUsuario: number): Observable<Cart> {
//   //   return this.http.get<Cart>(`http://localhost:8081/carrito/usuario/${idUsuario}`);
//   // }
//   getCarritoPorUsuario(idUsuario: number): Observable<Cart> {
//   const token = localStorage.getItem('token'); // o donde guardes el token
//   const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
//   return this.http.get<Cart>(`http://localhost:8081/carrito/usuario/${idUsuario}`, { headers });
// }


//   // Método anterior, mantenido por si lo usas en otro lado
//   agregarArticuloCliente(cartHasArticle: CartHasArticle): Observable<CartHasArticle> {
//     return this.http.post<CartHasArticle>(`${this.apiUrl}/CLIENTE/guardar`, cartHasArticle);
//   }

//   agregarArticuloClienteDtoCompleto(dto: CartItemDto): Observable<any> {
//   console.log('LOG ANGULAR SERVICE DTO Enviado al backend:', dto);

//   return this.http.post<any>(`${this.apiUrl}/CLIENTE/guardar`, dto).pipe(
//     tap(() => {
//       // Actualiza automáticamente el carrito del cliente
//       this.getCartCliente().subscribe();
//     })
//   );
// }
// getCartClientePorUsuario(userId: number): void {
//   this.http.get<CartHasArticle[]>(`${this.apiUrl}/CLIENTE/usuario/${userId}`)
//     .subscribe(items => this.cartItemsSubject.next(items));
// }

// // En CarritoService
// actualizarCantidad(cartId: number, articleId: number, quantity: number): Observable<any> {
//   const dto: CartItemDto = { cartId, articleId, quantity };
//   return this.http.put<any>(`${this.apiUrl}/CLIENTE/actualizar`, dto).pipe(
//     tap(() => this.getCartClientePorUsuario(dto.cartId)) // refresca el carrito
//   );
// }


// }
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject, tap, switchMap } from 'rxjs';
import { CartHasArticle } from '../../entities/CartHasArticle';
import { Cart } from '../../entities/Cart';
import { CartItemDto } from '../../entities/CartItemDto';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {

  private apiUrl = 'http://localhost:8081/CartHasArticle';
  private cartItemsSubject = new BehaviorSubject<CartHasArticle[]>([]);
  public cartItems$ = this.cartItemsSubject.asObservable();

  constructor(private http: HttpClient) {}

  getCartCliente(): Observable<CartHasArticle[]> {
    console.log('📦 getCartCliente llamada');
    return this.http.get<CartHasArticle[]>(`${this.apiUrl}/CLIENTE/usuario`).pipe(
      tap(items => this.cartItemsSubject.next(items))
    );
  }

  // getCarritoPorUsuario(idUsuario: number): Observable<Cart> {
  //   console.log('🟡 getCarritoPorUsuario llamada para idUsuario:', idUsuario);
  //   return this.http.get<Cart>(`${this.apiUrl}/carrito/usuario/${idUsuario}`);
  // }

getCarritoPorUsuario(idUsuario: number): Observable<Cart> {
  const token = localStorage.getItem('token'); // o this.authService.getToken() si quieres usar el servicio
  console.log('📦 Llamando a getCarritoPorUsuario');
  console.log('   idUsuario:', idUsuario);
  console.log('   token:', token);

  const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
  const url = `${this.apiUrl}/carrito/usuario/${idUsuario}`;
  console.log('   URL:', url);

  return this.http.get<Cart>(url, { headers });
}


  agregarArticuloClienteDtoCompleto(dto: CartItemDto): Observable<any> {
    console.log('➕ agregarArticuloClienteDtoCompleto DTO:', dto);
    return this.http.post<any>(`${this.apiUrl}/CLIENTE/guardar`, dto).pipe(
      tap(() => this.getCartCliente().subscribe())
    );
  }

 actualizarCantidad(cartId: number, articleId: number, quantity: number): Observable<any> {
  const dto: CartItemDto = { cartId, articleId, quantity };
  return this.http.put<any>(`${this.apiUrl}/CLIENTE/actualizar`, dto).pipe(
    switchMap(() => this.getCartClientePorUsuario(cartId)) // ahora funciona, retorna Observable
  );
}


  borrarArticuloCliente(item: CartHasArticle): Observable<void> {
    console.log('❌ borrarArticuloCliente item:', item);
    return this.http.delete<void>(
      `${this.apiUrl}/CLIENTE/borrar/${item.cart.idCarrito}/${item.article.idArticulo}`
    ).pipe(
      tap(() => this.getCartCliente().subscribe())
    );
  }

  getCartClientePorUsuario(userId: number): Observable<CartHasArticle[]> {
  return this.http.get<CartHasArticle[]>(`${this.apiUrl}/CLIENTE/usuario/${userId}`).pipe(
    tap(items => this.cartItemsSubject.next(items)) // actualizar el BehaviorSubject
  );
}

}



