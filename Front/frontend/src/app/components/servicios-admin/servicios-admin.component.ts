import { Component, OnInit } from '@angular/core';
import { Service } from '../../entities/Service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ServiciosService } from '../../services/Servicios/servicios.service';

declare var bootstrap: any;

@Component({
  selector: 'app-servicio',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './servicios-admin.component.html',
  styleUrls: ['./servicios-admin.component.css']
  
})
export class ServicioComponent implements OnInit {

  listaServicios: Service[] = [];
  servicioSeleccionado: Service = new Service();

  constructor(private servicioService: ServiciosService) {  }

  ngOnInit(): void {
    this.cargarServicios();
  }

  cargarServicios() {
    this.servicioService.getServicios().subscribe((data: Service[]) => {
      this.listaServicios = data;
    });
  }

  borrarServicio(id: number) {
    this.servicioService.borrarServicios(id).subscribe(() => {
      this.listaServicios = this.listaServicios.filter(s => s.idServicio !== id);
    });
  }

  editarServicio(id: number) {
    this.servicioService.editarServicios(id).subscribe((data: Service) => {
      this.servicioSeleccionado = data;

      const modalElement = document.getElementById('servicioModal');
      if (modalElement) {
        const modal = new bootstrap.Modal(modalElement);
        modal.show();
      }
    });
  }

  // crearServicio() {
  //   this.servicioService.crearServicios().subscribe((data: Service) => {
  //     this.servicioSeleccionado = data;

  //     const modalElement = document.getElementById('servicioModal');
  //     if (modalElement) {
  //       const modal = new bootstrap.Modal(modalElement);
  //       modal.show();
  //     }
  //   });
  // }
  // Crear un nuevo servicio
  crearServicio() {
    // Forzamos idServicio = 0 para que el backend lo cree automáticamente
    this.servicioSeleccionado = {
      idServicio: 0,
      nombre: '',
      precio: 0,
    };

    const modalElement = document.getElementById('servicioModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }

  // Guardar (crear o actualizar) servicio
  guardarServicio() {
    this.servicioService.guardarServicios(this.servicioSeleccionado).subscribe((res: Service) => {
      const modalElement = document.getElementById('servicioModal');
      if (modalElement) {
        const modal = bootstrap.Modal.getInstance(modalElement);
        modal?.hide();
      }
      // Actualizamos la lista de servicios
      this.cargarServicios();
    }, err => {
      console.error('Error al guardar el servicio:', err);
    });
  }
}
