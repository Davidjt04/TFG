
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

