// import { Component, OnInit } from '@angular/core';
// import { ClienteArticleService } from '../../services/Shop-cliente/tienda-cliente.service';
// import { Article } from '../../entities/Article';
// import { CartHasArticle } from '../../entities/CartHasArticle';
// import { Cart } from '../../entities/Cart';
// import { CarritoService } from '../../services/Cart/carrito.service';
// import { AuthService } from '../../services/Auth/auth.service';
// import { FormsModule } from '@angular/forms';
// import { CommonModule } from '@angular/common';
// import { CartItemDto } from '../../entities/CartItemDto';

// // Extensión para la UI
// interface ArticleUI extends Article {
//   cantidadSeleccionada: number;
//   exitoAgregar: boolean;
//   errorAgregar: boolean;
// }

// @Component({
//   selector: 'app-tienda-cliente',
//   imports: [CommonModule, FormsModule],
//   templateUrl: './tienda-cliente.component.html',
//   styleUrls: ['./tienda-cliente.component.css']
// })
// export class TiendaClienteComponent implements OnInit {

//   articulos: ArticleUI[] = [];
//   cargando = true;
//   cartDelUsuario!: Cart;

//   constructor(
//     private clienteService: ClienteArticleService,
//     private carritoService: CarritoService,
//     private authService: AuthService
//   ) {}

//   ngOnInit(): void {
//     console.log('🟣 ngOnInit ejecutado');
//     this.cargarArticulos();
//     this.obtenerCarritoUsuario();
//   }

//   // ==========================
//   // Cargar artículos desde backend
//   // ==========================
//   cargarArticulos(): void {
//     this.clienteService.getArticulos().subscribe({
//       next: (data) => {
//         this.articulos = data.map(a => ({
//           ...a,
//           cantidadSeleccionada: 1,
//           exitoAgregar: false,
//           errorAgregar: false
//         }));
//         this.cargando = false;
//       },
//       error: (err) => {
//         console.error('Error cargando artículos', err);
//         this.cargando = false;
//       }
//     });
//   }

//   // ==========================
//   // Obtener carrito del usuario
//   // ==========================
// obtenerCarritoUsuario(): void {
//   const idUsuario = 17; // ahora mismo fijo para pruebas

//   console.log('🟡 Obteniendo carrito del usuario:', idUsuario);

//   this.carritoService.getCarritoPorUsuario(idUsuario).subscribe({
//     next: (carrito: Cart) => {
//       console.log('✅ Carrito recibido correctamente:', carrito);
//       this.cartDelUsuario = carrito;
//     },
//     error: (err) => {
//       console.error('❌ Error obteniendo carrito:', err);
//     }
//   });
// }




//   // ==========================
//   // Agregar artículo al carrito
//   // ==========================
//   agregarAlCarrito(articulo: ArticleUI): void {

//   if (!this.cartDelUsuario) {
//     console.error("No hay carrito definido, no se puede añadir el artículo");
//     return;
//   }

//   const dto: CartItemDto = {
//       cartId: this.cartDelUsuario.idCarrito,
//       articleId: articulo.idArticulo,
//       quantity: articulo.cantidadSeleccionada
//   };

//   this.carritoService.agregarArticuloClienteDtoCompleto(dto)
//     .subscribe({
//       next: () => {
//         articulo.exitoAgregar = true;
//         setTimeout(() => articulo.exitoAgregar = false, 2000);
//       },
//       error: () => {
//         articulo.errorAgregar = true;
//         setTimeout(() => articulo.errorAgregar = false, 2000);
//       }
//     });
// }


// }
import { Component, OnInit } from '@angular/core';
import { ClienteArticleService } from '../../services/Shop-cliente/tienda-cliente.service';
import { Article } from '../../entities/Article';
import { Cart } from '../../entities/Cart';
import { CarritoService } from '../../services/Cart/carrito.service';
import { AuthService } from '../../services/Auth/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CartItemDto } from '../../entities/CartItemDto';

interface ArticleUI extends Article {
  cantidadSeleccionada: number;
  exitoAgregar: boolean;
  errorAgregar: boolean;
}

@Component({
  selector: 'app-tienda-cliente',
  imports: [CommonModule, FormsModule],
  templateUrl: './tienda-cliente.component.html',
  styleUrls: ['./tienda-cliente.component.css']
})
export class TiendaClienteComponent implements OnInit {

  articulos: ArticleUI[] = [];
  cargando = true;
  cartDelUsuario!: Cart;

  constructor(
    private clienteService: ClienteArticleService,
    private carritoService: CarritoService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    console.log('🟣 ngOnInit ejecutado');
    this.cargarArticulos();
    this.obtenerCarritoUsuario();
  }

  cargarArticulos(): void {
    this.clienteService.getArticulos().subscribe({
      next: (data) => {
        this.articulos = data.map(a => ({
          ...a,
          cantidadSeleccionada: 1,
          exitoAgregar: false,
          errorAgregar: false
        }));
        this.cargando = false;
      },
      error: (err) => {
        console.error('Error cargando artículos', err);
        this.cargando = false;
      }
    });
  }

  // obtenerCarritoUsuario(): void {
  //   const idUsuario = this.authService.getUserId();
  //   console.log('🟡 Obteniendo carrito del usuario:', idUsuario);

  //   this.carritoService.getCarritoPorUsuario(idUsuario).subscribe({
  //     next: (carrito: Cart) => {
  //       console.log('✅ Carrito recibido correctamente:', carrito);
  //       this.cartDelUsuario = carrito;
  //     },
  //     error: (err) => console.error('❌ Error obteniendo carrito:', err)
  //   });
  // }
 obtenerCarritoUsuario(): void {
  const idUsuario = this.authService.getUserId();
  console.log('🟡 Obteniendo carrito del usuario:', idUsuario);

  this.carritoService.getCarritoPorUsuario(idUsuario).subscribe({
    next: (carrito: Cart) => {
      console.log('✅ Carrito recibido correctamente:', carrito);
      this.cartDelUsuario = carrito;
    },
    error: (err) => {
      console.error('❌ Error obteniendo carrito:', err);
    }
  });
}



  agregarAlCarrito(articulo: ArticleUI): void {
    if (!this.cartDelUsuario) {
      console.error('❌ No hay carrito definido, no se puede añadir el artículo');
      return;
    }

    const dto: CartItemDto = {
      cartId: this.cartDelUsuario.idCarrito,
      articleId: articulo.idArticulo,
      quantity: articulo.cantidadSeleccionada
    };

    console.log('➕ Agregando al carrito DTO:', dto);

    this.carritoService.agregarArticuloClienteDtoCompleto(dto).subscribe({
      next: () => {
        articulo.exitoAgregar = true;
        setTimeout(() => articulo.exitoAgregar = false, 2000);
      },
      error: () => {
        articulo.errorAgregar = true;
        setTimeout(() => articulo.errorAgregar = false, 2000);
      }
    });
  }
}

