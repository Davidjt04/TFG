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
import { FinalCiteService } from '../../services/FinalCite/final-cite.service';
import { FinalCite } from '../../entities/FinalCite';

@Component({
  selector: 'app-servicios-cliente',
  imports: [RouterLink, CommonModule, FormsModule],
  standalone: true,
  templateUrl: './servicios-cliente.component.html',
  styleUrls: ['./servicios-cliente.component.css']
})
export class ServiciosClienteComponent implements AfterViewInit, OnInit {

  // --- Cortes y servicios ---
  listaPredefCut: PredefinedCut[] = [];
  listaServices: Service[] = [];
PredefinedCutSeleccionado?: PredefinedCut;
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
    private workerscheduleService: WorkerscheduleServiceService,
    private finalCiteService: FinalCiteService
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

// calcularTotal(): void {
//   if (this.PredefinedCutSeleccionado && this.PredefinedCutSeleccionado.precio_Total != null) {
//     this.totalSeleccionado = this.PredefinedCutSeleccionado.precio_Total;
//   } else {
//     this.totalSeleccionado = this.ServiceSeleccionado.reduce((acc, s) => acc + s.precio, 0);
//   }

//   console.log("Total calculado:", this.totalSeleccionado);
// }

calcularTotal(): void {
  
  // Si NO hay corte predefinido pero sí servicios → crear corte personalizado
  if (!this.PredefinedCutSeleccionado && this.ServiceSeleccionado.length > 0) {
    this.PredefinedCutSeleccionado = {
      idCorte_Predefinido: 0,
      nombre: "Corte personalizado",
      precio_Total: 0,
      duracion_Base: new Date(),
      imagen: ""
    };
  }

  // Si hay corte predefinido y NO hay servicios seleccionados → usar su precio
  if (this.PredefinedCutSeleccionado && this.ServiceSeleccionado.length === 0) {
    this.totalSeleccionado = this.PredefinedCutSeleccionado.precio_Total;
  } 
  // Si hay servicios → sumar sus precios
  else {
    this.totalSeleccionado = this.ServiceSeleccionado.reduce(
      (acc, s) => acc + s.precio,
      0
    );
  }

  console.log("Total calculado:", this.totalSeleccionado);
}

cargarHorasDisponibles() {
  if (!this.fechaSeleccionada) {
    this.listaHorasDisponibles = [];
    return;
  }

  this.workerscheduleService.getHorasDisponibles(this.fechaSeleccionada)
    .subscribe(horarios => {
      console.log("Horas obtenidas del backend:", horarios);

      // Mapear horas y eliminar duplicados usando Set
      this.listaHorasDisponibles = Array.from(
        new Set(
          horarios
            .filter(h => h.hora)       // previene nulos
            .map(h => h.hora.substring(0, 5)) // "HH:mm"
        )
      );

      if (this.listaHorasDisponibles.length === 0) {
        console.log("No hay horas disponibles para esta fecha");
      }
    }, err => {
      console.error("Error al cargar horas:", err);
      this.listaHorasDisponibles = [];
    });
}



  cargarTrabajadoresDisponibles() {
    if (!this.fechaSeleccionada || !this.horaSeleccionada) {
      this.listaTrabajadoresDisponibles = [];
      return;
    }

    this.workerscheduleService.getTrabajadoresDisponibles(this.fechaSeleccionada, this.horaSeleccionada)
      .subscribe((trabajadores: any[]) => {
        this.listaTrabajadoresDisponibles = trabajadores.map(t => ({
          ...t.detalleTrabajador,
          fecha: t.fecha,
          hora: t.hora,
          disponible: t.disponible
        }));
      });
  }

seleccionarCorte(corte: PredefinedCut) {
  // Si el corte ya está seleccionado → deseleccionarlo
  if (this.PredefinedCutSeleccionado === corte) {
    this.PredefinedCutSeleccionado = undefined;
    this.totalSeleccionado = this.ServiceSeleccionado.reduce((acc, s) => acc + s.precio, 0);
    return;
  }

  // Si NO estaba seleccionado → seleccionarlo
  this.PredefinedCutSeleccionado = corte;
  this.ServiceSeleccionado = []; // limpiar servicios
  this.totalSeleccionado = corte.precio_Total; 
}

// finish() {
//   // Validación de campos
//   if (!this.trabajadorSeleccionado || !this.fechaSeleccionada || !this.horaSeleccionada) {
//     alert("Debes completar todos los pasos antes de finalizar");
//     console.warn("finish(): faltan datos para completar la cita", {
//       trabajador: this.trabajadorSeleccionado,
//       fecha: this.fechaSeleccionada,
//       hora: this.horaSeleccionada
//     });
//     return;
//   }

//   // Formateo de la cita a enviar al backend
//   const citaFormateada: FinalCite = {
//     idCita: 0,
//     fecha: this.fechaSeleccionada,
//     hora: this.horaSeleccionada,
//     precioCorte: this.totalSeleccionado,
//     nombreCorte: this.PredefinedCutSeleccionado?.nombre || 'Corte personalizado',
//     nombreTrabajador: this.trabajadorSeleccionado.nombre
//   };

//   console.log("finish(): enviando cita al backend", citaFormateada);

//   // Guardar la cita
//   this.finalCiteService.guardarCiteService(citaFormateada).subscribe({
//     next: () => {
//       console.log("finish(): cita guardada correctamente");

//       // Marcar la hora como no disponible en Workerschedule
//       this.workerscheduleService.marcarHoraNoDisponible(
//         this.trabajadorSeleccionado.idDetalle_Trabajador,
//         this.fechaSeleccionada,
//         this.horaSeleccionada
//       ).subscribe({
//         next: () => console.log(`finish(): horario ${this.horaSeleccionada} del ${this.fechaSeleccionada} marcado como no disponible`),
//         error: (err) => console.error("finish(): error al marcar hora no disponible", err)
//       });

//       alert("Reserva realizada con éxito 🚀");
//     },
//     error: (error) => {
//       console.error("finish(): error al guardar la cita", error);
//       alert("Hubo un error al guardar la cita");
//     }
//   });
// }

finish() {
  // Validación de campos
  if (!this.trabajadorSeleccionado || !this.fechaSeleccionada || !this.horaSeleccionada) {
    alert("Debes completar todos los pasos antes de finalizar");
    console.warn("finish(): faltan datos para completar la cita", {
      trabajador: this.trabajadorSeleccionado,
      fecha: this.fechaSeleccionada,
      hora: this.horaSeleccionada
    });
    return;
  }

  // Formateo de la cita a enviar al backend
  const citaFormateada: FinalCite = {
    idCita: 0,
    fecha: this.fechaSeleccionada,
    hora: this.horaSeleccionada,
    precioCorte: this.totalSeleccionado,
    nombreCorte: this.PredefinedCutSeleccionado?.nombre || 'Corte personalizado',
    nombreTrabajador: this.trabajadorSeleccionado.nombre
  };

  console.log("finish(): enviando cita al backend", citaFormateada);

  // Guardar la cita
  this.finalCiteService.guardarCiteService(citaFormateada).subscribe({
    next: (citaCreada: FinalCite) => { // recibimos la cita con idCita generado
      console.log("finish(): cita guardada correctamente", citaCreada);

      // Guardamos en localStorage incluyendo idDetalle_Trabajador
      const citaConTrabajador = {
        ...citaCreada,
        idDetalle_Trabajador: this.trabajadorSeleccionado.idDetalle_Trabajador
      };
      localStorage.setItem("citaCliente", JSON.stringify(citaConTrabajador));

      // Marcar la hora como no disponible
      this.workerscheduleService.marcarHoraNoDisponible(
        this.trabajadorSeleccionado.idDetalle_Trabajador,
        this.fechaSeleccionada,
        this.horaSeleccionada
      ).subscribe({
        next: () => console.log(`Horario ${this.horaSeleccionada} del ${this.fechaSeleccionada} marcado como no disponible`),
        error: (err) => console.error("Error al marcar hora no disponible", err)
      });

      alert("Reserva realizada con éxito 🚀");
    },
    error: (error) => {
      console.error("finish(): error al guardar la cita", error);
      alert("Hubo un error al guardar la cita");
    }
  });
}

}





