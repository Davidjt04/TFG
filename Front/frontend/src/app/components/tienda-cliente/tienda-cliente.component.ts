import { Component, OnInit } from '@angular/core';
import { ClienteArticleService } from '../../services/Shop-cliente/tienda-cliente.service';
import { Article } from '../../entities/Article';
import { CarritoService } from '../../services/Cart/carrito.service';
import { Cart } from '../../entities/Cart';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface ArticleUI extends Article {
  cantidadSeleccionada: number;
  exitoAgregar: boolean;
  errorAgregar: boolean;
}

@Component({
  selector: 'app-tienda-cliente',
  imports: [CommonModule,FormsModule],
  templateUrl: './tienda-cliente.component.html',
  styleUrls: ['./tienda-cliente.component.css']
})
export class TiendaClienteComponent implements OnInit {

  articulos: ArticleUI[] = [];
  cargando = true;
  cartDelUsuario!: Cart;

  constructor(
    private clienteService: ClienteArticleService,
    private carritoService: CarritoService
  ) {}

  ngOnInit(): void {
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

  obtenerCarritoUsuario(): void {
    const token = localStorage.getItem('token');
    if (!token) return;

    const payload = JSON.parse(atob(token.split('.')[1]));
    const idUsuario = payload.idUsuario;

    this.carritoService.getCarritoPorUsuario(idUsuario).subscribe({
      next: (carrito: Cart) => this.cartDelUsuario = carrito,
      error: (err) => console.error('Error obteniendo carrito:', err)
    });
  }

  agregarAlCarrito(articulo: ArticleUI): void {
    this.carritoService.agregarArticulo({ 
      idArticulo: articulo.idArticulo, 
      cantidad: articulo.cantidadSeleccionada 
    }).subscribe({
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

