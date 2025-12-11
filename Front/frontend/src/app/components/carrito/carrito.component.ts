
import { Component, OnInit } from '@angular/core';
import { CartHasArticle } from '../../entities/CartHasArticle';
import { CarritoService } from '../../services/Cart/carrito.service';
import { AuthService } from '../../services/Auth/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CartHasArticleResponseDTO } from '../../entities/CartHasArticleResponseDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carrito',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {

  carrito: CartHasArticleResponseDTO[] = [];
  cargando = true;

  constructor(
    private carritoService: CarritoService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const userId = this.authService.getUserId();
    console.log('🟡 Cargando carrito para userId:', userId);

    // Suscribirse a los artículos del carrito y mapear a DTO local
    this.carritoService.cartItems$.subscribe(data => {
      this.carrito = data.map((item: CartHasArticle) => {
        let idCarrito = item.cart?.idCarrito;

        if (!idCarrito) {
          // Intentar usar idCarrito guardado en localStorage
          idCarrito = Number(localStorage.getItem('idCarrito') ?? 0);
          console.warn('⚠ Cart nulo en item, usando idCarrito desde localStorage:', item, idCarrito);
        }

        return {
          cantidad: item.cantidad,
          article: item.article ?? null,
          idCarrito
        };
      }).filter(item => item.idCarrito !== 0); // elimina items inválidos opcionalmente

      console.log('🟢 Carrito mapeado a DTO local:', this.carrito);
      this.cargando = false;
    });

    // Cargar datos iniciales y guardar idCarrito en localStorage
    this.carritoService.getCartClientePorUsuario(userId).subscribe({
      next: (items) => {
        if (items.length > 0 && items[0].cart?.idCarrito) {
          localStorage.setItem('idCarrito', items[0].cart.idCarrito.toString());
        }
        console.log('✅ Carrito inicial cargado');
      },
      error: err => {
        console.error('❌ Error cargando carrito inicial', err);
        this.cargando = false;
      }
    });
  }

  borrarArticulo(item: CartHasArticleResponseDTO): void {
    if (!item.article) return;

    const idCarrito = item.idCarrito || Number(localStorage.getItem('idCarrito') ?? 0);
    if (!idCarrito) {
      console.warn('⚠ No se puede borrar artículo, idCarrito desconocido:', item);
      return;
    }

    console.log('❌ Borrando artículo:', item.article.nombre);

    this.carritoService.borrarArticuloCliente({
      cart: { idCarrito } as any,
      article: item.article
    } as any).subscribe();
  }

  incrementarCantidad(item: CartHasArticleResponseDTO): void {
  if (!item.article) return;

  const nuevaCantidad = item.cantidad + 1;
  console.log('🔼 Incrementando cantidad:', item.article.nombre, 'Nueva cantidad:', nuevaCantidad, 'idCarrito:', item.idCarrito);

  // Actualizamos localmente para que se vea reflejado de inmediato
  item.cantidad = nuevaCantidad;

  // Llamamos al backend para guardar el cambio
  this.carritoService.actualizarCantidad(item.idCarrito, item.article.idArticulo, nuevaCantidad)
    .subscribe({
      next: () => console.log('✅ Cantidad incrementada correctamente para:', item.article?.nombre),
      error: (err) => {
        console.error('❌ Error al incrementar cantidad:', err);
        // Si falla, revertimos el cambio local
        item.cantidad = nuevaCantidad - 1;
      }
    });
}

  disminuirCantidad(item: CartHasArticleResponseDTO): void {
  if (!item.article) return;
  if (item.cantidad <= 1) return;

  const nuevaCantidad = item.cantidad - 1;

  // Actualizamos localmente
  item.cantidad = nuevaCantidad;

  this.carritoService.actualizarCantidad(item.idCarrito, item.article.idArticulo, nuevaCantidad)
    .subscribe({
      next: () => console.log('✅ Cantidad disminuida correctamente para:', item.article?.nombre),
      error: (err) => {
        console.error('❌ Error al disminuir cantidad:', err);
        // Revertimos si falla
        item.cantidad = nuevaCantidad + 1;
      }
    });
}

  get totalCarrito(): number {
    return this.carrito.reduce(
      (sum, item) => sum + (item.article ? item.cantidad * item.article.precio : 0),
      0
    );
  }

  irAPagar(): void {
  // Navega a PagoComponent pasando totalCarrito como queryParam
  this.router.navigate(['/CLIENTE/tienda/pago'], { 
    queryParams: { total: this.totalCarrito } 
  });
}
}
