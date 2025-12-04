import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, LoginRequest } from '../../services/Auth/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [CommonModule,FormsModule],
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginData: LoginRequest = { nombreUsuario: '', contrasenia: '' };
  cargando = false;
  error: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.cargando = true;
    this.error = null;

    this.authService.login(this.loginData).subscribe({
      next: (res) => {
        // Guardamos token y rol
        this.authService.setToken(res.token);
        this.authService.setRol(res.rol);

        // Redirigimos a landing page
        this.router.navigate(['/landing']);
      },
      error: (err) => {
        // Mostrar mensaje de error
        this.error = err.error ? err.error : 'Error desconocido';
        this.cargando = false;
      }
    });
  }
}
