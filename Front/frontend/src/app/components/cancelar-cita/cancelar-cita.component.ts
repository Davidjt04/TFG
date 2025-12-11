import { Component, OnInit } from '@angular/core';
import { WorkerscheduleServiceService } from '../../services/WorkerscheduleService/workerschedule-service.service';
import { FinalCiteService } from '../../services/FinalCite/final-cite.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FinalCite } from '../../entities/FinalCite';
import { of } from 'rxjs';
import { ClienteStateServiceService } from '../../services/ClienteStateService/cliente-state-service.service';

@Component({
  selector: 'app-cancelar-cita',
  imports: [CommonModule, FormsModule],
  standalone: true,
  templateUrl: './cancelar-cita.component.html',
  styleUrls: ['./cancelar-cita.component.css']
})
export class CancelarCitaComponent implements OnInit {

  idCita!: number;
  fecha!: string;      
  hora!: string;       
  nombreTrabajador!: string;
  idTrabajador?: number;

  mensaje: string = "";
  error: string = "";

  constructor(
    private wsService: WorkerscheduleServiceService,
    private citaService: FinalCiteService,
    private clienteState: ClienteStateServiceService
  ) {}

  ngOnInit(): void {
    const citaGuardada: FinalCite & { idDetalle_Trabajador?: number } =
      JSON.parse(localStorage.getItem("citaCliente") || "{}");

    if (!citaGuardada || !citaGuardada.idCita) {
      this.error = "No se encontró ninguna cita guardada en la sesión.";
      console.warn(this.error);
      return;
    }

    this.idCita = citaGuardada.idCita;
    this.fecha = citaGuardada.fecha;
    this.hora = citaGuardada.hora;
    this.nombreTrabajador = citaGuardada.nombreTrabajador;
    this.idTrabajador = citaGuardada.idDetalle_Trabajador;
  }

  cancelarCita() {
    this.error = "";
    this.mensaje = "";

    if (!this.idCita) {
      this.error = "No hay cita válida para cancelar.";
      return;
    }

    const marcarDisponible$ = this.idTrabajador
      ? this.wsService.marcarHoraDisponible(this.idTrabajador, this.fecha, this.hora)
      : of(undefined);

    marcarDisponible$.subscribe({
      next: () => this.borrarCita(false),
      error: (err) => {
        console.error("Error al marcar hora disponible:", err);
        this.borrarCita(true);
      }
    });
  }

  private borrarCita(errorMarcarDisponible: boolean) {
    this.citaService.delete(this.idCita).subscribe({
      next: () => {
        this.mensaje = "La cita ha sido cancelada correctamente.";

        if (errorMarcarDisponible) {
          this.error = "La cita fue eliminada, pero no se pudo liberar el horario del trabajador.";
        }

        this.clienteState.clearCita();
        localStorage.removeItem('citaCliente');

        // 🔹 Recargar toda la página para reflejar cambios
        window.location.reload();
      },
      error: (err) => {
        console.error("Error al eliminar la cita:", err);
        this.error = "Ocurrió un error al eliminar la cita. Intenta nuevamente.";
      }
    });
  }
}
