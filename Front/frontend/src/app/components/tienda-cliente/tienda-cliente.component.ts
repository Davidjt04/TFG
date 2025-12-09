import { Component, OnInit } from '@angular/core';
import { ClienteArticleService } from '../../services/Shop-cliente/tienda-cliente.service';
import { Article } from '../../entities/Article';
import { CartHasArticle } from '../../entities/CartHasArticle';
import { Cart } from '../../entities/Cart';
import { CarritoService } from '../../services/Cart/carrito.service';
import { AuthService } from '../../services/Auth/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

// Extensión para la UI
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

  // ==========================
  // Cargar artículos desde backend
  // ==========================
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

  // ==========================
  // Obtener carrito del usuario
  // ==========================
obtenerCarritoUsuario(): void {
  const idUsuario = 17; // ahora mismo fijo para pruebas

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




  // ==========================
  // Agregar artículo al carrito
  // ==========================
  agregarAlCarrito(articulo: ArticleUI): void {

  console.log('🟠 Carrito actual:', this.cartDelUsuario);

  if (!this.cartDelUsuario || !this.cartDelUsuario.idCarrito) {
    console.error('Carrito no disponible');
    return;
  }
  
  const cartId = Number(this.cartDelUsuario.idCarrito);
  const articleId = Number(articulo.idArticulo);
  const quantity = Number(articulo.cantidadSeleccionada) || 1;

  // ✅ USAMOS EL NUEVO MÉTODO QUE ENVÍA SOLO LOS IDs (DTO)
  this.carritoService.agregarArticuloClienteDto(cartId, articleId, quantity).subscribe({
    next: () => {
      articulo.exitoAgregar = true;
      articulo.errorAgregar = false;
      setTimeout(() => articulo.exitoAgregar = false, 2000);
    },
    error: (err) => {
      console.error('Error agregando al carrito', err);
      articulo.exitoAgregar = false;
      articulo.errorAgregar = true;
      setTimeout(() => articulo.errorAgregar = false, 2000);
    }
  });
}
}
