import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { PredefinedCut } from '../../entities/PredefinedCut';
import { ServicioCortePredefService } from '../../services/PredefinedCut/servicio-corte-predef.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-imagenes-cliente',
  standalone: true,
  imports: [CommonModule, RouterLink,FormsModule],
  templateUrl: './imagenes-admin.component.html',
  styleUrl: './imagenes-admin.component.css'
})
export class ImagenesAdminComponent implements OnInit {

  cortes: PredefinedCut[] = [];

  // Para editar la imagen
  corteSeleccionado: PredefinedCut | null = null;
  nuevaImagen: string = '';

  constructor(private corteService: ServicioCortePredefService) {}

  ngOnInit(): void {
    this.cargarCortes();
  }

  cargarCortes() {
    this.corteService.getCortePredef().subscribe({
      next: (data) => {
        this.cortes = data;
      },
      error: (err) => console.error('Error al cargar cortes:', err)
    });
  }

  // Genera la URL de la imagen
  getImageUrl(imagen: string): string {
    return `http://localhost:8081/img/${imagen}`;
  }

  abrirEditor(corte: PredefinedCut) {
    this.corteSeleccionado = { ...corte };
    this.nuevaImagen = corte.imagen;
  }

  guardarCambios() {
    if (this.corteSeleccionado) {
      this.corteSeleccionado.imagen = this.nuevaImagen;

      this.corteService.guardarCortePredef(this.corteSeleccionado).subscribe({
        next: () => {
          alert('Imagen actualizada correctamente');
          this.corteSeleccionado = null;
          this.cargarCortes();
        },
        error: (e) => console.error('Error al guardar', e)
      });
    }
  }

  cancelarEdicion() {
    this.corteSeleccionado = null;
  }
}
