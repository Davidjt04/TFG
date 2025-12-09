// // import { Component, OnInit } from '@angular/core';
// // import { CartHasArticle } from '../../entities/CartHasArticle';
// // import { CarritoService } from '../../services/Cart/carrito.service';
// // import { FormsModule } from '@angular/forms';
// // import { CommonModule } from '@angular/common';

// // @Component({
// //   selector: 'app-carrito',
// //   imports: [CommonModule,FormsModule],
// //   templateUrl: './carrito.component.html',
// //   styleUrls: ['./carrito.component.css']
// // })
// // export class CarritoComponent implements OnInit {
// //   carrito: CartHasArticle[] = [];
// //   cargando = true;

// //   constructor(private carritoService: CarritoService) {}

// //   ngOnInit(): void {
// //     this.cargarCarrito();
// //   }

// //   cargarCarrito(): void {
// //     this.cargando = true;
// //     this.carritoService.getCartCliente().subscribe({
// //       next: (data: CartHasArticle[]) => {
// //         this.carrito = data;
// //         this.cargando = false;
// //       },
// //       error: (err: any) => {
// //         console.error('Error cargando carrito', err);
// //         this.cargando = false;
// //       }
// //     });
// //   }

// //   // ================= BORRAR ARTÍCULO =================
// //   borrarArticulo(item: CartHasArticle): void {
// //     this.carritoService.borrarArticuloCliente(item).subscribe({
// //       next: () => {
// //         this.carrito = this.carrito.filter(i => i !== item);
// //       },
// //       error: (err: any) => console.error('Error borrando artículo', err)
// //     });
// //   }
// // }

// // import { Injectable } from '@angular/core';
// // import { HttpClient } from '@angular/common/http';
// // import { BehaviorSubject, Observable } from 'rxjs';
// // import { CartHasArticle } from '../../entities/CartHasArticle';
// // import { CartItemDto } from '../../entities/CartItemDto';

// // @Injectable({ providedIn: 'root' })
// // export class CarritoService {
// //   private carritoUrl = 'http://localhost:8080/cartHasArticle/cliente';
// //   private carritoSubject = new BehaviorSubject<CartHasArticle[]>([]);
// //   carrito$ = this.carritoSubject.asObservable();

// //   constructor(private http: HttpClient) {}

// //   // Obtener carrito del usuario y actualizar BehaviorSubject
// //   getCartCliente(userId: number): void {
// //     this.http.get<CartHasArticle[]>(`${this.carritoUrl}/usuario/${userId}`)
// //       .subscribe(data => this.carritoSubject.next(data));
// //   }

// //   agregarArticuloClienteDto(dto: CartItemDto): Observable<void> {
// //     return new Observable<void>((observer) => {
// //       this.http.post<void>(`${this.carritoUrl}/agregar`, dto)
// //         .subscribe({
// //           next: () => {
// //             this.getCartCliente(dto.cartId); // Actualizar automáticamente
// //             observer.next();
// //             observer.complete();
// //           },
// //           error: (err) => observer.error(err)
// //         });
// //     });
// //   }
// // }

// import { Component, OnInit } from '@angular/core';
// import { CartHasArticle } from '../../entities/CartHasArticle';
// import { CarritoService } from '../../services/Cart/carrito.service';
// import { AuthService } from '../../services/Auth/auth.service'; // <-- debes tenerlo
// import { CommonModule } from '@angular/common';
// import { FormsModule } from '@angular/forms';

// @Component({
//   selector: 'app-carrito',
//   standalone: true,
//   imports: [CommonModule, FormsModule],
//   templateUrl: './carrito.component.html',
//   styleUrls: ['./carrito.component.css']
// })
// export class CarritoComponent implements OnInit {

//   carrito: CartHasArticle[] = [];
//   cargando = true;

//   constructor(
//     private carritoService: CarritoService,
//     private authService: AuthService
//   ) {}

//   ngOnInit(): void {
//     const userId = this.authService.getUserId();

//     // 1) Cargar carrito desde backend
//     this.carritoService.getCartClientePorUsuario(userId);

//     // 2) Escuchar cambios del BehaviorSubject
//     this.carritoService.cartItems$.subscribe(data => {
//       this.carrito = data;
//       this.cargando = false;
//     });
//   }

//   borrarArticulo(item: CartHasArticle): void {
//     this.carritoService.borrarArticuloCliente(item).subscribe({
//       next: () => {
//         // La UI se actualiza sola gracias a carrito$
//       },
//       error: (err) => console.error('Error borrando artículo', err)
//     });
//   }

  
//   incrementarCantidad(item: CartHasArticle): void {
//   const nuevaCantidad = item.cantidad + 1;
//   this.cargando = true;
//   this.carritoService.actualizarCantidad(item.cart.idCarrito, item.article.idArticulo, nuevaCantidad)
//     .subscribe(() => this.cargando = false, () => this.cargando = false);
// }
//   disminuirCantidad(item: CartHasArticle): void {
//   if (item.cantidad > 1) {
//     const nuevaCantidad = item.cantidad - 1;
//     this.cargando = true;
//     this.carritoService.actualizarCantidad(item.cart.idCarrito, item.article.idArticulo, nuevaCantidad)
//       .subscribe(() => this.cargando = false, () => this.cargando = false);
//   }
// }
// // CarritoComponent.ts
// get totalCarrito(): number {
//   return this.carrito.reduce((sum, item) => sum + (item.cantidad * item.article.precio), 0);
// }
// }

import { Component, OnInit } from '@angular/core';
import { CartHasArticle } from '../../entities/CartHasArticle';
import { CarritoService } from '../../services/Cart/carrito.service';
import { AuthService } from '../../services/Auth/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-carrito',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {

  carrito: CartHasArticle[] = [];
  cargando = true;

  constructor(
    private carritoService: CarritoService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const userId = this.authService.getUserId();
    console.log('🟡 Cargando carrito para userId:', userId);

    this.carritoService.getCartClientePorUsuario(userId);

    this.carritoService.cartItems$.subscribe(data => {
      this.carrito = data;
      this.cargando = false;
    });
  }

  borrarArticulo(item: CartHasArticle): void {
    console.log('❌ Borrando artículo:', item);
    this.carritoService.borrarArticuloCliente(item).subscribe();
  }

  incrementarCantidad(item: CartHasArticle): void {
    const nuevaCantidad = item.cantidad + 1;
    console.log('🔼 Incrementando cantidad:', item.article.nombre, nuevaCantidad);
    this.cargando = true;
    this.carritoService.actualizarCantidad(item.cart.idCarrito, item.article.idArticulo, nuevaCantidad)
      .subscribe(() => this.cargando = false, () => this.cargando = false);
  }

  disminuirCantidad(item: CartHasArticle): void {
    if (item.cantidad > 1) {
      const nuevaCantidad = item.cantidad - 1;
      console.log('🔽 Disminuyendo cantidad:', item.article.nombre, nuevaCantidad);
      this.cargando = true;
      this.carritoService.actualizarCantidad(item.cart.idCarrito, item.article.idArticulo, nuevaCantidad)
        .subscribe(() => this.cargando = false, () => this.cargando = false);
    }
  }

  get totalCarrito(): number {
    return this.carrito.reduce((sum, item) => sum + (item.cantidad * item.article.precio), 0);
  }
}

