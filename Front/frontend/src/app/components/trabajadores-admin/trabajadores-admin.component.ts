import { Component, OnInit } from '@angular/core';
import { WorkerDetails } from '../../entities/WorkerDetails';
import { WorkerDetailsService } from '../../services/WorkerDetails/worker-details.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-trabajadores-admin',
  templateUrl: './trabajadores-admin.component.html',
  imports: [CommonModule, FormsModule],
  styleUrls: ['./trabajadores-admin.component.css']
})
export class TrabajadoresAdminComponent implements OnInit {

  trabajadores: WorkerDetails[] = [];
  trabajadorSeleccionado?: WorkerDetails; // trabajador a editar
  nuevaImagen: string = ''; // nombre de archivo de la imagen
  mostrarModal: boolean = false;

  constructor(private workerService: WorkerDetailsService) {}

  ngOnInit(): void {
    this.cargarTrabajadores();
  }

  cargarTrabajadores(): void {
    this.workerService.getTrabajadores().subscribe({
      next: data => this.trabajadores = data,
      error: err => console.error('Error al cargar trabajadores', err)
    });
  }

  borrarTrabajador(id: number): void {
    if (!confirm('¿Estás seguro de que deseas borrar este trabajador?')) return;

    this.workerService.borrarTrabajador(id).subscribe({
      next: () => this.trabajadores = this.trabajadores.filter(t => t.idDetalle_Trabajador !== id),
      error: err => { console.error('Error al borrar trabajador', err); alert('No se pudo borrar el trabajador'); }
    });
  }

  abrirModal(trabajador: WorkerDetails): void {
    this.trabajadorSeleccionado = { ...trabajador }; // clonamos
    this.nuevaImagen = trabajador.imagen; // cargamos la imagen actual
    this.mostrarModal = true;
  }

  cerrarModal(): void {
    this.trabajadorSeleccionado = undefined;
    this.nuevaImagen = '';
    this.mostrarModal = false;
  }

  guardarCambios(): void {
    if (!this.trabajadorSeleccionado) return;

    // Actualizamos la imagen con la seleccionada
    this.trabajadorSeleccionado.imagen = this.nuevaImagen;

    this.workerService.guardarTrabajador(this.trabajadorSeleccionado).subscribe({
      next: updated => {
        const index = this.trabajadores.findIndex(t => t.idDetalle_Trabajador === updated.idDetalle_Trabajador);
        if (index !== -1) this.trabajadores[index] = updated;
        this.cerrarModal();
      },
      error: err => { console.error('Error al guardar trabajador', err); alert('No se pudieron guardar los cambios'); }
    });
  }

  // Función para generar URL completa de imagen desde /img
  getImageUrl(nombreArchivo: string): string {
    return nombreArchivo ? `http://localhost:8081/img/${nombreArchivo}` : '';
  }
}
