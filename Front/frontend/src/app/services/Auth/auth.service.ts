import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
// import { User } from '../../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // BehaviorSubject para manejar el rol del usuario dinámicamente
  private rolUsuarioSubject: BehaviorSubject<string | null> = new BehaviorSubject<string | null>(null);

  constructor() {
    // Inicializamos desde localStorage si hay un rol guardado
    const rol = localStorage.getItem('rol');
    if (rol) {
      this.rolUsuarioSubject.next(rol);
    }
  }

  // Observable para suscribirse al rol
  getRolObservable(): Observable<string | null> {
    return this.rolUsuarioSubject.asObservable();
  }

  // Obtener rol actual (síncrono)
  getRol(): string | null {
    return this.rolUsuarioSubject.value;
  }

  // Guardar rol del usuario
  setRol(rol: string) {
    this.rolUsuarioSubject.next(rol);
    localStorage.setItem('rol', rol);
  }

  // Limpiar rol (logout)
  clearRol() {
    this.rolUsuarioSubject.next(null);
    localStorage.removeItem('rol');
  }

  // Métodos para los roles
  esAdmin(): boolean {
    return this.getRol() === 'ADMIN';
  }

  esCliente(): boolean {
    return this.getRol() === 'CLIENTE';
  }

  esTrabajador(): boolean {
    return this.getRol() === 'TRABAJADOR';
  }
}
