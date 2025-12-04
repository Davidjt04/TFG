import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService, RegisterRequest } from '../../services/Auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  formData: RegisterRequest = {
    nombreUsuario: '',
    contrasenia: '',
    email: '',
    rol: ''
  };

  mensaje = '';
  error = '';
  cargando = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  registrar() {
    this.mensaje = '';
    this.error = '';

    if (
      !this.formData.nombreUsuario ||
      !this.formData.contrasenia ||
      !this.formData.email ||
      !this.formData.rol
    ) {
      this.error = 'Todos los campos son obligatorios';
      return;
    }

    this.cargando = true;

    this.authService.register(this.formData).subscribe({
      next: (resp) => {
        this.mensaje = resp;

        // ✅ Limpiar formulario
        this.formData = {
          nombreUsuario: '',
          contrasenia: '',
          email: '',
          rol: ''
        };

        this.cargando = false;

        // ✅ REDIRECCIÓN AUTOMÁTICA AL LOGIN (después de 1 único segundo)
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 1000);
      },
      error: (err) => {
        this.error = err.error;
        this.cargando = false;
      }
    });
  }
}
