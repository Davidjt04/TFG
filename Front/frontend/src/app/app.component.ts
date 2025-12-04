import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './services/Auth/auth.service';
import { FooterComponent } from "./components/footer/footer.component";
import { AdminNavComponent } from './components/admin-nav/admin-nav.component';
import { ClienteNavComponent } from './components/cliente-nav/cliente-nav.component';
import { TrabajadorNavComponent } from './components/trabajador-nav/trabajador-nav.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FooterComponent,AdminNavComponent,ClienteNavComponent,TrabajadorNavComponent,CommonModule],
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
