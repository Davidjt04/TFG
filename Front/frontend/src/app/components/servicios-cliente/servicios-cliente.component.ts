import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterLink } from '@angular/router';
import Stepper from 'bs-stepper';
import { ServicioCortePredefService } from '../../services/PredefinedCut/servicio-corte-predef.service';
import { ServiciosService } from '../../services/Servicios/servicios.service';
import { PredefinedCut } from '../../entities/PredefinedCut';
import { Service } from '../../entities/Service';
import { WorkerscheduleServiceService } from '../../services/WorkerscheduleService/workerschedule-service.service';
import { WorkerDetails } from '../../entities/WorkerDetails';

@Component({
  selector: 'app-servicios-cliente',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './servicios-cliente.component.html',
  styleUrls: ['./servicios-cliente.component.css']
})
export class ServiciosClienteComponent implements AfterViewInit, OnInit {

  // --- Cortes y servicios ---
  listaPredefCut: PredefinedCut[] = [];
  listaServices: Service[] = [];
  PredefinedCutSeleccionado: PredefinedCut = new PredefinedCut();
  ServiceSeleccionado: Service[] = [];
  totalSeleccionado = 0;

  // --- Fecha y hora ---
  fechaSeleccionada!: string;
  listaHorasDisponibles: string[] = [];
  horaSeleccionada!: string;

  // --- Trabajadores ---
  listaTrabajadoresDisponibles: WorkerDetails[] = [];
  trabajadorSeleccionado!: WorkerDetails;

  // --- Stepper ---
  private stepper!: Stepper;
  currentStepIndex = 0;
  @ViewChild('stepperEl') stepperEl!: ElementRef;

  totalSteps = 3;
  progressWidth = 33;

  constructor(
    private serviPredefCut: ServicioCortePredefService,
    private serviServices: ServiciosService,
    private workerscheduleService: WorkerscheduleServiceService
  ) {}

  ngOnInit(): void {
    this.serviPredefCut.getCortePredef().subscribe((cortes: PredefinedCut[]) => this.listaPredefCut = cortes);
    this.serviServices.getServicios().subscribe((servicios: Service[]) => this.listaServices = servicios);
  }

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.stepper = new Stepper(this.stepperEl.nativeElement, {
        linear: true,
        animation: true
      });

      this.stepper.to(1);

      this.stepperEl.nativeElement.addEventListener('shown.bs-stepper', (event: any) => {
        this.currentStepIndex = event.detail.indexStep;
        this.progressWidth = ((this.currentStepIndex + 1) / this.totalSteps) * 100;
      });
    }, 0);
  }

  next(form?: NgForm) {
    if (form && !form.valid) {
      Object.values(form.controls).forEach(c => c.markAsTouched());
      return;
    }

    if (this.currentStepIndex === 1 && this.fechaSeleccionada && this.horaSeleccionada) {
      this.cargarTrabajadoresDisponibles();
    }

    this.stepper.next();
  }

  prev() {
    this.stepper.previous();
  }

  calcularTotal(): void {
  if (this.PredefinedCutSeleccionado) {
    // Si hay un corte predefinido, el total es su precio
    this.totalSeleccionado = this.PredefinedCutSeleccionado.precio_Total;
  } else {
    // Si no hay corte predefinido, suma los servicios seleccionados
    this.totalSeleccionado = this.ServiceSeleccionado.reduce((acc, s) => acc + s.precio, 0);
  }
}


  cargarHorasDisponibles() {
    if (!this.fechaSeleccionada) {
      this.listaHorasDisponibles = [];
      return;
    }

    this.workerscheduleService.getHorasDisponibles(this.fechaSeleccionada).subscribe(
      (data: any[]) => {
        // Suponiendo que el backend devuelve un array de objetos { hora: string }
        this.listaHorasDisponibles = data.map(item => item.hora || item);
      },
      error => console.error("Error cargando horas disponibles:", error)
    );
  }

  cargarTrabajadoresDisponibles() {
  if (!this.fechaSeleccionada || !this.horaSeleccionada) {
    this.listaTrabajadoresDisponibles = [];
    this.trabajadorSeleccionado ;
    return;
  }

  this.workerscheduleService.getTrabajadoresDisponibles(this.fechaSeleccionada, this.horaSeleccionada)
    .subscribe((trabajadores: any[]) => {
      console.log("Trabajadores disponibles RAW:", trabajadores);

      // Extraer detalleTrabajador y mapear a WorkerDetails
      this.listaTrabajadoresDisponibles = trabajadores.map(t => ({
        ...t.detalleTrabajador,
        fecha: t.fecha,
        hora: t.hora,
        disponible: t.disponible
      }));

      // Limpiar selección previa
      this.trabajadorSeleccionado ;

      console.log("Trabajadores procesados:", this.listaTrabajadoresDisponibles);
    });
}


finish() {
  if (!this.PredefinedCutSeleccionado || !this.fechaSeleccionada || !this.horaSeleccionada || !this.trabajadorSeleccionado) {
    alert("Debes completar todos los pasos antes de finalizar");
    return;
  }

  const nombreTrabajador = this.trabajadorSeleccionado.nombre || 'Sin nombre';
  console.log("Nombre del trabajador seleccionado:", nombreTrabajador);

  alert(`Reserva realizada con éxito 🚀
        Corte: ${this.PredefinedCutSeleccionado.nombre}
        Fecha: ${this.fechaSeleccionada}
        Hora: ${this.horaSeleccionada}
        Trabajador: ${nombreTrabajador}
        Total: ${this.totalSeleccionado}€`);
}

seleccionarCorte(corte: PredefinedCut) {
  this.PredefinedCutSeleccionado = corte;

  // Limpiar servicios seleccionados si eliges un corte predefinido
  this.ServiceSeleccionado = [];

  // Actualizar total al precio del corte predefinido
  this.totalSeleccionado = corte.precio_Total;
}




}





