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
// import { Injectable } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Observable, BehaviorSubject, tap, switchMap } from 'rxjs';
// import { CartHasArticle } from '../../entities/CartHasArticle';
// import { Cart } from '../../entities/Cart';
// import { CartItemDto } from '../../entities/CartItemDto';
// import { CartArticleDto } from '../../entities/CartArticleDto';

// @Injectable({
//   providedIn: 'root'
// })
// export class CarritoService {

//   private apiUrl = 'http://localhost:8081/CartHasArticle';
//   private cartItemsSubject = new BehaviorSubject<CartHasArticle[]>([]);
//   public cartItems$ = this.cartItemsSubject.asObservable();

//   constructor(private http: HttpClient) {}

//   getCartCliente(): Observable<CartHasArticle[]> {
//     console.log('📦 getCartCliente llamada');
//     return this.http.get<CartHasArticle[]>(`${this.apiUrl}/CLIENTE/usuario`).pipe(
//       tap(items => this.cartItemsSubject.next(items))
//     );
//   }

//   // getCarritoPorUsuario(idUsuario: number): Observable<Cart> {
//   //   console.log('🟡 getCarritoPorUsuario llamada para idUsuario:', idUsuario);
//   //   return this.http.get<Cart>(`${this.apiUrl}/carrito/usuario/${idUsuario}`);
//   // }

// getCarritoPorUsuario(idUsuario: number): Observable<Cart> {
//   const token = localStorage.getItem('token'); // o this.authService.getToken() si quieres usar el servicio
//   console.log('📦 Llamando a getCarritoPorUsuario');
//   console.log('   idUsuario:', idUsuario);
//   console.log('   token:', token);

//   const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
//   const url = `${this.apiUrl}/carrito/usuario/${idUsuario}`;
//   console.log('   URL:', url);

//   return this.http.get<Cart>(url, { headers });
// }


//   agregarArticuloClienteDtoCompleto(dto: CartItemDto): Observable<any> {
//     console.log('➕ agregarArticuloClienteDtoCompleto DTO:', dto);
//     return this.http.post<any>(`${this.apiUrl}/CLIENTE/guardar`, dto).pipe(
//       tap(() => this.getCartCliente().subscribe())
//     );
//   }

//  actualizarCantidad(cartId: number, articleId: number, quantity: number): Observable<any> {
//   const dto: CartItemDto = { cartId, articleId, quantity };
//   return this.http.put<any>(`${this.apiUrl}/CLIENTE/actualizar`, dto).pipe(
//     switchMap(() => this.getCartClientePorUsuario(cartId)) // ahora funciona, retorna Observable
//   );
// }


//   borrarArticuloCliente(item: CartHasArticle): Observable<void> {
//     console.log('❌ borrarArticuloCliente item:', item);
//     return this.http.delete<void>(
//       `${this.apiUrl}/CLIENTE/borrar/${item.cart.idCarrito}/${item.article.idArticulo}`
//     ).pipe(
//       tap(() => this.getCartCliente().subscribe())
//     );
//   }

//   getCartClientePorUsuario(userId: number): Observable<CartHasArticle[]> {
//   return this.http.get<CartHasArticle[]>(`${this.apiUrl}/CLIENTE/usuario/${userId}`).pipe(
//     tap(items => this.cartItemsSubject.next(items)) // actualizar el BehaviorSubject
//   );
// }

// agregarArticulo(dtoParcial: { idArticulo: number; cantidad: number }): Observable<any> {
//   const token = localStorage.getItem('token');

//   console.log("🟦 agregarArticulo() llamado:");
//   console.log("   idArticulo:", dtoParcial.idArticulo);
//   console.log("   cantidad:", dtoParcial.cantidad);
//   console.log("   token:", token);

//   if (!token) {
//     console.error("❌ NO HAY TOKEN, no se puede obtener idUsuario");
//     throw new Error("No se encuentra el token del usuario");
//   }

//   // Extraemos ID del usuario del token (si tu token contiene userId)
//   const payload = JSON.parse(atob(token.split('.')[1]));
//   const idUsuario = payload.idUsuario || payload.userId;

//   console.log("   idUsuario obtenido del token:", idUsuario);

//   const dto: CartArticleDto = {
//     idUsuario,
//     idArticulo: dtoParcial.idArticulo,
//     cantidad: dtoParcial.cantidad
//   };

//   console.log("📤 DTO enviado al backend:", dto);

//   return this.http.post<any>("http://localhost:8081/cart/add", dto).pipe(
//     tap(resp => console.log("📥 Respuesta del backend:", resp))
//   );
// }


// }
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { CartHasArticle } from '../../entities/CartHasArticle';
import { Cart } from '../../entities/Cart';
import { CartArticleDto } from '../../entities/CartArticleDto';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private apiUrl = 'http://localhost:8081/CLIENTE';
  private cartItemsSubject = new BehaviorSubject<CartHasArticle[]>([]);
  public cartItems$ = this.cartItemsSubject.asObservable();

  constructor(private http: HttpClient) {}

  // Obtener carrito completo del usuario
  getCarritoPorUsuario(idUsuario: number): Observable<Cart> {
    const token = localStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };
    return this.http.get<Cart>(`${this.apiUrl}/carrito/${idUsuario}`, { headers });
  }

  // Listar artículos en el carrito
  getCartClientePorUsuario(userId: number): Observable<CartHasArticle[]> {
    return this.http.get<CartHasArticle[]>(`${this.apiUrl}/carrito/${userId}/articulos`).pipe(
      tap(items => this.cartItemsSubject.next(items))
    );
  }

  // Agregar artículo
  agregarArticulo(dtoParcial: { idArticulo: number; cantidad: number }): Observable<any> {
    const token = localStorage.getItem('token');
    if (!token) throw new Error("No se encuentra el token del usuario");

    const payload = JSON.parse(atob(token.split('.')[1]));
    const idUsuario = payload.idUsuario;

    const dto: CartArticleDto = {
      idUsuario,
      idArticulo: dtoParcial.idArticulo,
      cantidad: dtoParcial.cantidad
    };

    return this.http.post<any>(`${this.apiUrl}/carrito/add`, dto).pipe(
      tap(() => this.getCartClientePorUsuario(idUsuario).subscribe())
    );
  }

  // Borrar artículo (si implementas endpoint)
  borrarArticuloCliente(item: CartHasArticle): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/carrito/${item.cart.idCarrito}/articulo/${item.article.idArticulo}`
    ).pipe(
      tap(() => {
        const token = localStorage.getItem('token');
        const payload = token ? JSON.parse(atob(token.split('.')[1])) : null;
        if (payload) this.getCartClientePorUsuario(payload.idUsuario).subscribe();
      })
    );
  }
  // carrito.service.ts
actualizarCantidad(idCarrito: number, idArticulo: number, nuevaCantidad: number) {
  const token = localStorage.getItem('token');
  if (!token) throw new Error("No se encuentra el token del usuario");

  const payload = JSON.parse(atob(token.split('.')[1]));
  const idUsuario = payload.idUsuario;

  return this.http.put(
    `${this.apiUrl}/carrito/${idCarrito}/articulo/${idArticulo}`,
    { cantidad: nuevaCantidad },
    { headers: { Authorization: `Bearer ${token}` } }
  ).pipe(
    tap(() => this.getCartClientePorUsuario(idUsuario).subscribe())
  );
}

}



