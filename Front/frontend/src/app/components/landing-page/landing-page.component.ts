import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/Auth/auth.service';

@Component({
  selector: 'app-landing-page',
  imports: [RouterLink],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent {
constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  // =========================
  // ✅ BOTÓN PEDIR CITA
  // =========================
  irAPedirCita() {
    if (this.authService.esAdmin()) {
      this.router.navigate(['/ADMIN/cita/servicios']);
      return;
    }

    if (this.authService.esTrabajador()) {
      this.router.navigate(['/TRABAJADOR/calendario']);
      return;
    }

    if (this.authService.esCliente()) {
      this.router.navigate(['/CLIENTE/cita/servicios']);
      return;
    }

    // Si no está logueado
    this.router.navigate(['/login']);
  }

  // =========================
  // ✅ BOTÓN TIENDA ONLINE
  // =========================
  irATienda() {
    if (this.authService.esAdmin()) {
      this.router.navigate(['/ADMIN/tienda']);
      return;
    }

    if (this.authService.esCliente()) {
      this.router.navigate(['/CLIENTE/tienda']);
      return;
    }

    if (this.authService.esTrabajador()) {
      alert('No tienes permisos para entrar a este apartado');
      return;
    }

    // Si no está logueado
    this.router.navigate(['/login']);
  }
}
