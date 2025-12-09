import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WorkerscheduleServiceService {

  private apiUrl = "http://localhost:8081/workerschedule";

  constructor(private http: HttpClient) {}

  getHorasDisponibles(fecha: string): Observable<any[]> {
    //se envia la fecha como query param (se mete en la url)
    let params = new HttpParams().set("fecha", fecha);
    return this.http.get<any[]>(`${this.apiUrl}/horas`, { params });
  }

  getTrabajadoresDisponibles(fecha: string, hora: string): Observable<any[]> {
    let params = new HttpParams()
      .set("fecha", fecha)
      .set("hora", hora);
    return this.http.get<any[]>(`${this.apiUrl}/trabajadores`, { params });
  }
//   // workerschedule.service.ts
// getHorasDisponiblesPorTrabajador(idTrabajador: number, fecha: string): Observable<any[]> {
//   return this.http.get<any[]>(`${this.apiUrl}/workerschedule/horas-disponibles`, {
//     params: { idTrabajador, fecha }
//   });
// }

// marcarHoraNoDisponible(idTrabajador: number, fecha: string, hora: string): Observable<void> {
//   return this.http.post<void>(`${this.apiUrl}/workerschedule/marcar-no-disponible`, null, {
//     params: { idTrabajador, fecha, hora }
//   });
// }

  // Obtener horas disponibles por trabajador y fecha
  getHorasDisponiblesPorTrabajador(idTrabajador: number, fecha: string): Observable<any[]> {
    const params = new HttpParams()
      .set('idTrabajador', idTrabajador)
      .set('fecha', fecha);
    return this.http.get<any[]>(`${this.apiUrl}/horas-disponibles`, { params });
  }

  // Marcar una hora como no disponible
  marcarHoraNoDisponible(idTrabajador: number, fecha: string, hora: string): Observable<void> {
    const params = new HttpParams()
      .set('idTrabajador', idTrabajador)
      .set('fecha', fecha)
      .set('hora', hora);
    return this.http.post<void>(`${this.apiUrl}/marcar-no-disponible`, null, { params });
  }

  // ✅ NUEVO: Bloqueo de horario por el trabajador (sin pasar ID)
// marcarHoraNoDisponibleTrabajador(fecha: string, hora: string): Observable<void> {
//   const params = new HttpParams()
//     .set('fecha', fecha)
//     .set('hora', hora);

//   return this.http.post<void>(
//     `${this.apiUrl}/TRABAJADOR/marcar-no-disponible`,
//     null,
//     { params }
//   );
// }
// workerschedule.service.ts
// Marcar una hora como no disponible (por el trabajador logueado)
marcarHoraNoDisponiblePorTrabajador(fecha: string, hora: string): Observable<void> {
  const params = new HttpParams()
    .set('fecha', fecha)
    .set('hora', hora);
  return this.http.post<void>(`${this.apiUrl}/trabajador/marcar-no-disponible`, null, { params });
}
getIdTrabajadorPorUsuario(username: string) {
  return this.http.get<number>(`${this.apiUrl}/usuario/id?username=${username}`);
}

// Obtener ID del trabajador usando el JWT del usuario logueado
getIdTrabajadorPorUsuarioLogueado(username: string, token: string): Observable<number> {
  return this.http.get<number>(`${this.apiUrl}/usuario/id?username=${username}`, {
    headers: { Authorization: `Bearer ${token}` }
  });
}

// Obtener horas disponibles del trabajador logueado (nuevo)
getHorasDisponiblesPorTrabajadorLogueado(fecha: string, token: string): Observable<any[]> {
  return this.http.get<any[]>(`${this.apiUrl}/horas-disponibles`, {
    headers: { Authorization: `Bearer ${token}` },
    params: { fecha }
  });
}

// Bloquear hora para el trabajador logueado (nuevo)
marcarHoraNoDisponiblePorTrabajadorLogueado(fecha: string, hora: string, token: string): Observable<void> {
  const params = new HttpParams().set('fecha', fecha).set('hora', hora);
  return this.http.post<void>(`${this.apiUrl}/trabajador/marcar-no-disponible`, null, {
    headers: { Authorization: `Bearer ${token}` },
    params
  });
}

// workerschedule.service.ts
getIdTrabajadorConToken(): Observable<number> {
  const token = localStorage.getItem('token');
  if (!token) throw new Error('Token no encontrado');
  
  // Se envía en el header Authorization
  return this.http.get<number>(`${this.apiUrl}/usuario/id`, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
}

delete(id: number){
  return this.http.delete(`${this.apiUrl}/borrar/${id}`);
}




}

  

