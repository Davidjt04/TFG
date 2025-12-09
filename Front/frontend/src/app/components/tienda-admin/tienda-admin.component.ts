import { Component, OnInit } from '@angular/core';
import { Article } from '../../entities/Article';
import { AdminArticleService } from '../../services/Shop-admin/tienda-admin.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-tienda-admin',
  imports: [CommonModule,FormsModule],
  templateUrl: './tienda-admin.component.html',
  styleUrl: './tienda-admin.component.css'
})
export class TiendaAdminComponent implements OnInit{
articulos: Article[] = [];
  cargando = true;

  // Modal
  articuloSeleccionado: Article | null = null;
  mostrarModal = false;

  constructor(private adminService: AdminArticleService) {}

  ngOnInit(): void {
    this.cargarArticulos();
  }

  cargarArticulos() {
    this.cargando = true;
    this.adminService.getArticulos().subscribe({
      next: (data) => {
        this.articulos = data;
        this.cargando = false;
      },
      error: (err) => {
        console.error('Error cargando artículos', err);
        this.cargando = false;
      }
    });
  }

  abrirModal(articulo: Article) {
    this.articuloSeleccionado = { ...articulo }; // Copia para no modificar directamente
    this.mostrarModal = true;
  }

  cerrarModal() {
    this.articuloSeleccionado = null;
    this.mostrarModal = false;
  }

  guardarCambios() {
    if (this.articuloSeleccionado) {
      this.adminService.guardarArticulo(this.articuloSeleccionado).subscribe({
        next: () => {
          this.cargarArticulos();
          this.cerrarModal();
        },
        error: (err) => console.error('Error guardando artículo', err)
      });
    }
  }

  borrarArticulo(id: number) {
    if (confirm('¿Seguro que quieres borrar este artículo?')) {
      this.adminService.borrarArticulo(id).subscribe({
        next: () => this.cargarArticulos(),
        error: (err) => console.error('Error borrando artículo', err)
      });
    }
  }
}
