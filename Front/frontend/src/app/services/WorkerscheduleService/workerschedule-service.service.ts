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
}

  

