import { Component, OnInit } from '@angular/core';
import { CartHasArticle } from '../../entities/CartHasArticle';
import { CarritoService } from '../../services/Cart/carrito.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-carrito',
  imports: [CommonModule,FormsModule],
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {
  carrito: CartHasArticle[] = [];
  cargando = true;

  constructor(private carritoService: CarritoService) {}

  ngOnInit(): void {
    this.cargarCarrito();
  }

  cargarCarrito(): void {
    this.cargando = true;
    this.carritoService.getCartCliente().subscribe({
      next: (data: CartHasArticle[]) => {
        this.carrito = data;
        this.cargando = false;
      },
      error: (err: any) => {
        console.error('Error cargando carrito', err);
        this.cargando = false;
      }
    });
  }

  incrementarCantidad(item: CartHasArticle): void {
    item.cantidad += 1;
    // aquí deberías llamar al método de actualizar cantidad si lo tienes
  }

  disminuirCantidad(item: CartHasArticle): void {
    if (item.cantidad > 1) {
      item.cantidad -= 1;
      // aquí también llamar al método de actualizar cantidad si lo tienes
    }
  }

  // ================= BORRAR ARTÍCULO =================
  borrarArticulo(item: CartHasArticle): void {
    this.carritoService.borrarArticuloCliente(item).subscribe({
      next: () => {
        this.carrito = this.carrito.filter(i => i !== item);
      },
      error: (err: any) => console.error('Error borrando artículo', err)
    });
  }
}
