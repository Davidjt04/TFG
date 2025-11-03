import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterLink } from "@angular/router";
import Stepper from 'bs-stepper';
import { After } from 'v8';
import { ServicioCortePredefService } from '../../services/servicio-corte-predef.service';
import { PredefinedCut } from '../../entities/PredefinedCut';

@Component({
  selector: 'app-servicios-cliente',
  imports: [RouterLink,CommonModule,FormsModule],
  templateUrl: './servicios-cliente.component.html',
  styleUrl: './servicios-cliente.component.css'
})
export class ServiciosClienteComponent implements AfterViewInit,OnInit{
  listaPredefCut : PredefinedCut[];
  PredefinedCutSeleccionado: PredefinedCut = new PredefinedCut();

   constructor(private serviPredefCut : ServicioCortePredefService) {
    this.listaPredefCut =[];
  }

  ngOnInit(): void {
      this.serviPredefCut.getCortePredef().subscribe((data) => {
      console.log(data);
      this.listaPredefCut=data;
      this.listaPredefCut = data.map((item: any) => item.equipo || item);
      // console.log(this.listaEquipos);
    });

  }
  private stepper!: Stepper;
  @ViewChild('stepperEl') stepperEl!: ElementRef;

  totalSteps = 3;
  progressWidth = 33;

  nombre = '';
  apellido = '';
  email = '';



  //se ejecuta cuando la vista y sus componenetes asi como sus componentes hijos han sido inicializados
 ngAfterViewInit(): void {
  // Asegurarnos de que Angular haya renderizado todo
  setTimeout(() => {
    // Inicializamos el stepper
    this.stepper = new Stepper(this.stepperEl.nativeElement, {
      linear: true,
      animation: true
    });

    // Forzar visibilidad del primer paso
    const firstStepContent = this.stepperEl.nativeElement.querySelector('#step-1');
    if (firstStepContent) {
      firstStepContent.classList.add('d-block');
    }
    this.stepper.to(1); // hace que el stepper reconozca el primer paso como activo

    // Listener de evento para la barra de progreso
    this.stepperEl.nativeElement.addEventListener('shown.bs-stepper', (event: any) => {
      const index = event.detail.indexStep;
      this.progressWidth = ((index + 1) / this.totalSteps) * 100;
    });
  }, 0);
}



  //si le paso el formulario en agumentos lo valida y si no lo paso, simplemente avanza
  next(form?: NgForm) {
  if (form && !form.valid) {
    // Marca todos los controles como tocados para mostrar errores
    Object.values(form.controls).forEach(control => {
      control.markAsTouched();//asegrura que se muestren los mensajes de error
    });
    return; // No avanzar al siguiente paso
  }
  this.stepper.next();
  }

  prev() {
    this.stepper.previous();
    this.updateProgress();
  }

  updateProgress() {
    this.progressWidth = ( 1 / this.totalSteps) * 100;
  }

  finish() {
    alert(`Formulario finalizado:\n\n${this.nombre} ${this.apellido}\n${this.email}`);
  }

}
