import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { CartHasArticle } from '../../entities/CartHasArticle';
import { Cart } from '../../entities/Cart';

interface CartItemDto {
  cartId: number;
  articleId: number;
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class CarritoService {

  private apiUrl = 'http://localhost:8081/CartHasArticle';
  // Estado reactivo del carrito
  private cartItemsSubject = new BehaviorSubject<CartHasArticle[]>([]);
  public cartItems$ = this.cartItemsSubject.asObservable();

  constructor(private http: HttpClient) {}

  getCartCliente(): Observable<CartHasArticle[]> {
    return this.http.get<CartHasArticle[]>(`${this.apiUrl}/CLIENTE/usuario`).pipe(
      tap(items => this.cartItemsSubject.next(items))
    );
  }

  agregarArticuloClienteDto(cartId: number, articleId: number, quantity: number): Observable<any> {
    const dto: CartItemDto = { cartId, articleId, quantity };
    console.log('LOG ANGULAR SERVICE DTO: Enviando DTO al backend:', dto);
    
    return this.http.post<any>(`${this.apiUrl}/CLIENTE/guardar`, dto).pipe(
      // Después de guardar exitosamente, actualizamos el estado local
      tap(() => this.getCartCliente().subscribe())
    );
  }

  borrarArticuloCliente(item: CartHasArticle): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/CLIENTE/borrar/${item.cart.idCarrito}/${item.article.idArticulo}`
    ).pipe(
      // Después de borrar exitosamente, actualizamos el estado local
      tap(() => this.getCartCliente().subscribe())
    );
  }

  getCarritoPorUsuario(idUsuario: number): Observable<Cart> {
    return this.http.get<Cart>(`http://localhost:8081/carrito/usuario/${idUsuario}`);
  }

  // Método anterior, mantenido por si lo usas en otro lado
  agregarArticuloCliente(cartHasArticle: CartHasArticle): Observable<CartHasArticle> {
    return this.http.post<CartHasArticle>(`${this.apiUrl}/CLIENTE/guardar`, cartHasArticle);
  }
}
