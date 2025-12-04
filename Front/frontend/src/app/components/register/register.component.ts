import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService, RegisterRequest } from '../../services/Auth/auth.service';

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

  constructor(private authService: AuthService) {}

  registrar() {
    this.mensaje = '';
    this.error = '';

    if (!this.formData.nombreUsuario ||
        !this.formData.contrasenia ||
        !this.formData.email ||
        !this.formData.rol) {
      this.error = 'Todos los campos son obligatorios';
      return;
    }

    this.cargando = true;

    this.authService.register(this.formData).subscribe({
      next: (resp) => {
        this.mensaje = resp;
        this.formData = {
          nombreUsuario: '',
          contrasenia: '',
          email: '',
          rol: ''
        };
        this.cargando = false;
      },
      error: (err) => {
        this.error = err.error;
        this.cargando = false;
      }
    });
  }
}
