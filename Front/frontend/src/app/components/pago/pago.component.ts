import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pago',
  imports: [CommonModule,FormsModule],
  templateUrl: './pago.component.html',
  styleUrls: ['./pago.component.css']
})
export class PagoComponent implements OnInit {

  saldo: number = 0;
  cantidadAgregar: number = 0;
  totalCarrito: number = 0;
  mensaje: string = '';
  exito: boolean = false;

  private readonly STORAGE_KEY = 'saldoUsuario';

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Obtener saldo del localStorage (persistencia)
    const saldoGuardado = localStorage.getItem(this.STORAGE_KEY);
    this.saldo = saldoGuardado ? Number(saldoGuardado) : 0;

    // Obtener totalCarrito desde queryParam
    this.route.queryParams.subscribe(params => {
      this.totalCarrito = params['total'] ? Number(params['total']) : 0;
    });
  }

  agregarSaldo(): void {
    if (this.cantidadAgregar <= 0) return;

    this.saldo += this.cantidadAgregar;
    localStorage.setItem(this.STORAGE_KEY, this.saldo.toString());

    // Actualizar mensaje de confirmación
    this.mensaje = `Se añadieron ${this.cantidadAgregar} € a tu saldo.`;
    this.exito = true;

    // Reset input
    this.cantidadAgregar = 0;
  }

  pagar(): void {
    if (this.totalCarrito > this.saldo) {
      this.mensaje = 'Te falta saldo. Añade más fondos.';
      this.exito = false;
      return;
    }

    // Restar total del saldo
    this.saldo -= this.totalCarrito;
    localStorage.setItem(this.STORAGE_KEY, this.saldo.toString());

    this.mensaje = 'Compra realizada con éxito.';
    this.exito = true;

    // Opcional: resetear totalCarrito a 0 después de pagar
    this.totalCarrito = 0;
  }
}
