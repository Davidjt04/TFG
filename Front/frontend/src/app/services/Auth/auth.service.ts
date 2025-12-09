import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';


export interface RegisterRequest {
  nombreUsuario: string;
  contrasenia: string;
  email: string;
  rol: string;
}

export interface LoginRequest {
  nombreUsuario: string;
  contrasenia: string;
}

export interface LoginResponse {
  token: string;
  nombreUsuario: string;
  rol: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8081/auth';

  // ✅ BehaviorSubject para manejar el rol dinámicamente
  private rolUsuarioSubject: BehaviorSubject<string | null> =
    new BehaviorSubject<string | null>(null);

  // constructor(private http: HttpClient) {
  //   const rol = localStorage.getItem('rol');
  //   if (rol) {
  //     this.rolUsuarioSubject.next(rol);
  //   }
  // }
  constructor(private http: HttpClient) {
    const rol = localStorage.getItem('rol');
    if (rol) {
      this.rolUsuarioSubject.next(rol);
    }
  }

  // ============================
  // ✅ REGISTRO
  // ============================
  register(data: RegisterRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, data, {
      responseType: 'text'
    });
  }

  // ============================
  // ✅ LOGIN
  // ============================
  // login(data: LoginRequest): Observable<LoginResponse> {
  //   return this.http.post<LoginResponse>(`${this.apiUrl}/login`, data);
  // }

  login(data: LoginRequest): Observable<LoginResponse> {
  return this.http.post<LoginResponse>(`${this.apiUrl}/login`, data).pipe(
    tap(res => {
      // Guardamos token y rol
      this.setToken(res.token);
      this.setRol(res.rol);

      // Guardamos todo el usuario, incluido idUsuario
      localStorage.setItem("user", JSON.stringify(res));
    })
  );
}

  // ============================
  // ✅ TOKEN
  // ============================
  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  clearToken() {
    localStorage.removeItem('token');
  }

  // ============================
  // ✅ ROL (TU LÓGICA ORIGINAL)
  // ============================
  getRolObservable(): Observable<string | null> {
    return this.rolUsuarioSubject.asObservable();
  }

  getRol(): string | null {
    return this.rolUsuarioSubject.value;
  }

  setRol(rol: string) {
    this.rolUsuarioSubject.next(rol);
    localStorage.setItem('rol', rol);
  }

  clearRol() {
    this.rolUsuarioSubject.next(null);
    localStorage.removeItem('rol');
  }

  // ============================
  // ✅ MÉTODOS DE ROL
  // ============================
  esAdmin(): boolean {
    return this.getRol() === 'ADMIN';
  }

  esCliente(): boolean {
    return this.getRol() === 'CLIENTE';
  }

  esTrabajador(): boolean {
    return this.getRol() === 'TRABAJADOR';
  }

  // ============================
  // ✅ LOGOUT COMPLETO
  // ============================
  logout() {
    this.clearRol();
    this.clearToken();
  }

  // AuthService
getUsername(): string | null {
  const token = this.getToken();
  if (!token) return null;

  try {
    // Decodificamos el payload del JWT (la parte central, base64)
    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.sub; // según tu log, el username está en 'sub'
  } catch (e) {
    console.error('Error al decodificar JWT', e);
    return null;
  }
}

getUserId(): number {
  const user = localStorage.getItem('user');
  if (!user) return 0;
  return JSON.parse(user).idUsuario; // o el nombre correcto del campo
}

}
