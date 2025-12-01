import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './services/Auth/auth.service';
import { FooterComponent } from "./components/footer/footer.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FooterComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontend';

  rolActual: string | null = null;

  constructor(public authService: AuthService) {}

  ngOnInit(): void {
    // Nos suscribimos al rol para actualizar la vista dinámicamente
    this.authService.getRolObservable().subscribe(rol => {
      this.rolActual = rol;
    });
  }

  esAdmin(): boolean {
    return this.rolActual === 'ADMIN';
  }

  esCliente(): boolean {
    return this.rolActual === 'CLIENTE';
  }

  esTrabajador(): boolean {
    return this.rolActual === 'TRABAJADOR';
  }

  esPaginaDeAuth(): boolean {
    return window.location.pathname.startsWith('/auth');
  }
}
