import { Component, OnInit } from '@angular/core';
import { WorkerscheduleServiceService } from '../../services/WorkerscheduleService/workerschedule-service.service';
import { AuthService } from '../../services/Auth/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-justificantes-trabajador',
  imports: [CommonModule, FormsModule],
  templateUrl: './justificantes-trabajador.component.html',
  styleUrls: ['./justificantes-trabajador.component.css']
})
export class JustificantesTrabajadorComponent implements OnInit {

  token: string | null = null;
  idTrabajador!: number;
  fechaSeleccionada: string = '';
  horaSeleccionada: string = '';
  horasDisponibles: any[] = [];
  mensaje: string = '';

  constructor(
    private wsService: WorkerscheduleServiceService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // Obtener token JWT
    this.token = this.authService.getToken();
    if (!this.token) {
      this.mensaje = 'No se encontró token de autenticación';
      return;
    }

    console.log('[Init] Token JWT encontrado');

    // Obtener ID del trabajador usando solo el token
    this.wsService.getIdTrabajadorConToken().subscribe({
      next: (id: number) => {
        this.idTrabajador = id;
        console.log('[Init] idTrabajador:', this.idTrabajador);
        this.cargarHoras();
      },
      error: (err) => {
        console.error('[Init] Error al obtener idTrabajador', err);
        this.mensaje = 'Error al obtener id del trabajador desde JWT';
      }
    });
  }

  // Cargar horas disponibles para el trabajador y la fecha seleccionada
  cargarHoras(): void {
    if (!this.idTrabajador || !this.fechaSeleccionada) return;

    this.wsService.getHorasDisponiblesPorTrabajador(this.idTrabajador, this.fechaSeleccionada)
      .subscribe({
        next: (horas) => {
          this.horasDisponibles = horas;
          console.log('[Horas Disponibles]', horas);
        },
        error: (err) => {
          console.error('[Error] al obtener horas disponibles', err);
          this.mensaje = 'No se pudieron cargar las horas disponibles';
        }
      });
  }

  // Cambiar la fecha desde el calendario
  onFechaCambio(fecha: string): void {
    this.fechaSeleccionada = fecha;
    console.log('[onFechaCambio] Nueva fecha seleccionada:', this.fechaSeleccionada);
    this.cargarHoras();
  }

  // Marcar una hora como no disponible
  marcarHora(): void {
  if (!this.fechaSeleccionada || !this.horaSeleccionada) return;

  console.log(
    "[Marcar Hora] Trabajador:", this.idTrabajador,
    "Fecha:", this.fechaSeleccionada,
    "Hora:", this.horaSeleccionada
  );

  this.wsService.marcarHoraNoDisponible(
    this.idTrabajador,
    this.fechaSeleccionada,
    this.horaSeleccionada
  ).subscribe({
    next: () => {
      this.mensaje = `Hora ${this.horaSeleccionada} marcada como no disponible`;

      // Recargar horas para actualizar la UI
      this.cargarHoras();
    },
    error: (err) => {
      console.error("[Error] al marcar hora no disponible", err);
      this.mensaje = "No se pudo marcar la hora como no disponible";
    }
  });
}

}
