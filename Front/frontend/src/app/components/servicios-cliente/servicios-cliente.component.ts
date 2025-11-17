// import { CommonModule } from '@angular/common';
// import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
// import { FormsModule, NgForm } from '@angular/forms';
// import { RouterLink } from "@angular/router";
// import Stepper from 'bs-stepper';
// import { After } from 'v8';
// import { ServicioCortePredefService } from '../../services/PredefinedCut/servicio-corte-predef.service';
// import { PredefinedCut } from '../../entities/PredefinedCut';
// import { ServiciosService } from '../../services/Servicios/servicios.service';
// import { Service } from '../../entities/Service';

// @Component({
//   selector: 'app-servicios-cliente',
//   imports: [RouterLink,CommonModule,FormsModule],
//   templateUrl: './servicios-cliente.component.html',
//   styleUrl: './servicios-cliente.component.css'
// })
// export class ServiciosClienteComponent implements AfterViewInit,OnInit{
//   // --- HORARIOS ---
//   fechaSeleccionada: string = "";
//   listaHorasDisponibles: any[] = [];
//   horaSeleccionada: string = "";

//   listaTrabajadoresDisponibles: any[] = [];
//   trabajadorSeleccionado: any = null;

//   // --- PREDEFINED CUTS Y SERVICES ---
//   listaPredefCut : PredefinedCut[];
//   listaServices : Service[];
//   PredefinedCutSeleccionado: PredefinedCut = new PredefinedCut();

//   //CALCULAR TOTAL
//   ServiceSeleccionado: Service[] = [];//array de servicios seleccionados
//   totalSeleccionado = 0; // Acumulador de precios

//    constructor(private serviPredefCut : ServicioCortePredefService,
//               private serviServices : ServiciosService,
//               private workerScheduleService: WorkerscheduleService )    
//     {
//     this.listaPredefCut =[];
//     this.listaServices =[];
//     }
  

//   ngOnInit(): void {
//       this.serviPredefCut.getCortePredef().subscribe((data) => {
//       console.log(data);
//       this.listaPredefCut=data;
//       this.listaPredefCut = data.map((item: any) => item);
//       // console.log(this.listaEquipos);
      
//     });
//       this.serviServices.getServicios().subscribe((data) => {
//       console.log(data);
//       this.listaServices=data;
//       this.listaServices = data.map((item: any) => item);
//     });
//   }
//   private stepper!: Stepper;
//   @ViewChild('stepperEl') stepperEl!: ElementRef;

//   totalSteps = 3;
//   progressWidth = 33;

//   nombre = '';
//   apellido = '';
//   email = '';



//   //se ejecuta cuando la vista y sus componenetes asi como sus componentes hijos han sido inicializados
//  ngAfterViewInit(): void {
//   // Asegurarnos de que Angular haya renderizado todo
//   setTimeout(() => {
//     // Inicializamos el stepper
//     this.stepper = new Stepper(this.stepperEl.nativeElement, {
//       linear: true,
//       animation: true
//     });

//     // Forzar visibilidad del primer paso
//     const firstStepContent = this.stepperEl.nativeElement.querySelector('#step-1');
//     if (firstStepContent) {
//       firstStepContent.classList.add('d-block');
//     }
//     this.stepper.to(1); // hace que el stepper reconozca el primer paso como activo

//     // Listener de evento para la barra de progreso
//     this.stepperEl.nativeElement.addEventListener('shown.bs-stepper', (event: any) => {
//       const index = event.detail.indexStep;
//       this.progressWidth = ((index + 1) / this.totalSteps) * 100;
//     });
//   }, 0);
// }



//   //si le paso el formulario en agumentos lo valida y si no lo paso, simplemente avanza
//   next(form?: NgForm) {
//   if (form && !form.valid) {
//     // Marca todos los controles como tocados para mostrar errores
//     Object.values(form.controls).forEach(control => {
//       control.markAsTouched();//asegrura que se muestren los mensajes de error
//     });
//     return; // No avanzar al siguiente paso
//   }
//   this.stepper.next();
//   }

//   prev() {
//     this.stepper.previous();
//     this.updateProgress();
//   }

//   updateProgress() {
//     this.progressWidth = ( 1 / this.totalSteps) * 100;
//   }

//   finish() {
//     alert(`Formulario finalizado:\n\n${this.nombre} ${this.apellido}\n${this.email}`);
//   }

//   //Acumulador de precio del select multiple en el primer paso
// calcularTotal(): void {
//   this.totalSeleccionado = this.ServiceSeleccionado
//     .reduce(function (acumulador, s) {
//       return acumulador + s.precio;
//     }, 0);
// }

// cargarHorasDisponibles() {
//   if (!this.fechaSeleccionada) return;

//   this.workerScheduleService.getHorasDisponibles(this.fechaSeleccionada)
//     .subscribe(data => {
//       this.listaHorasDisponibles = data;
//       this.horaSeleccionada = "";
//       this.listaTrabajadoresDisponibles = [];
//     });
// }

// cargarTrabajadoresDisponibles() {
//   if (!this.fechaSeleccionada || !this.horaSeleccionada) return;

//   this.workerScheduleService
//     .getTrabajadoresDisponibles(this.fechaSeleccionada, this.horaSeleccionada)
//     .subscribe(data => {
//       this.listaTrabajadoresDisponibles = data;
//     });
// }


// }
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

interface Trabajador {
  id: number;
  nombre: string;
}

@Component({
  selector: 'app-servicios-cliente',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './servicios-cliente.component.html',
  styleUrl: './servicios-cliente.component.css'
})
export class ServiciosClienteComponent implements AfterViewInit, OnInit {
  listaPredefCut: PredefinedCut[] = [];
  listaServices: Service[] = [];
  PredefinedCutSeleccionado: PredefinedCut = new PredefinedCut();
  ServiceSeleccionado: Service[] = [];
  totalSeleccionado = 0;

  fechaSeleccionada!: string;
  listaHorasDisponibles: string[] = [];
  horaSeleccionada!: string;

  listaTrabajadoresDisponibles: Trabajador[] = [];
  trabajadorSeleccionado!: Trabajador;

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
    this.totalSeleccionado = this.ServiceSeleccionado.reduce((acc, s) => acc + s.precio, 0);
  }

  cargarHorasDisponibles() {
    if (!this.fechaSeleccionada) {
      this.listaHorasDisponibles = [];
      this.horaSeleccionada = '';
      this.listaTrabajadoresDisponibles = [];
      this.trabajadorSeleccionado = undefined!;
      return;
    }

    this.workerscheduleService.getHorasDisponibles(this.fechaSeleccionada)
      .subscribe((horas: string[]) => {
        this.listaHorasDisponibles = horas;
        this.horaSeleccionada = '';
        this.listaTrabajadoresDisponibles = [];
        this.trabajadorSeleccionado = undefined!;
      });
  }

  cargarTrabajadoresDisponibles() {
    if (!this.fechaSeleccionada || !this.horaSeleccionada) {
      this.listaTrabajadoresDisponibles = [];
      this.trabajadorSeleccionado = undefined!;
      return;
    }

    this.workerscheduleService.getTrabajadoresDisponibles(this.fechaSeleccionada, this.horaSeleccionada)
      .subscribe((trabajadores: Trabajador[]) => {
        this.listaTrabajadoresDisponibles = trabajadores;
      });
  }

  finish() {
    if (!this.PredefinedCutSeleccionado || !this.fechaSeleccionada || !this.horaSeleccionada || !this.trabajadorSeleccionado) {
      alert("Debes completar todos los pasos antes de finalizar");
      return;
    }

    alert(`Reserva realizada con éxito 🚀
          Corte: ${this.PredefinedCutSeleccionado.nombre}
          Fecha: ${this.fechaSeleccionada}
          Hora: ${this.horaSeleccionada}
          Trabajador: ${this.trabajadorSeleccionado.nombre}
          Total: ${this.totalSeleccionado}€`);
  }
}




