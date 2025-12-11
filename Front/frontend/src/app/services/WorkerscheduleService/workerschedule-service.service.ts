// import { Injectable } from '@angular/core';
// import { HttpClient, HttpParams } from '@angular/common/http';
// import { Observable } from 'rxjs';


// @Injectable({
//   providedIn: 'root'
// })
// export class WorkerscheduleServiceService {

//   private apiUrl = "http://localhost:8081/TRABAJADOR/HORARIO";

//   constructor(private http: HttpClient) {}

//   // ---- LISTA ----
//   getHorasDisponibles(fecha: string): Observable<any[]> {
//     const params = new HttpParams().set("fecha", fecha);
//     return this.http.get<any[]>(`${this.apiUrl}/horas`, { params });
//   }

//   getTrabajadoresDisponibles(fecha: string, hora: string): Observable<any[]> {
//     const params = new HttpParams()
//       .set("fecha", fecha)
//       .set("hora", hora);
//     return this.http.get<any[]>(`${this.apiUrl}/trabajadores`, { params });
//   }

//   getHorasDisponiblesPorTrabajador(idTrabajador: number, fecha: string): Observable<any[]> {
//     const params = new HttpParams()
//       .set('idTrabajador', idTrabajador)
//       .set('fecha', fecha);
//     return this.http.get<any[]>(`${this.apiUrl}/horas-disponibles`, { params });
//   }

//   // ---- MARCAR NO DISPONIBLE ----
//   marcarHoraNoDisponible(idTrabajador: number, fecha: string, hora: string): Observable<void> {
//     const token = localStorage.getItem('token');
//     const params = new HttpParams()
//       .set('idTrabajador', idTrabajador)
//       .set('fecha', fecha)
//       .set('hora', hora);

//     return this.http.post<void>(`${this.apiUrl}/marcar-no-disponible`, null, {
//       headers: { Authorization: `Bearer ${token}` },
//       params
//     });
//   }

//   marcarHoraNoDisponiblePorTrabajador(fecha: string, hora: string): Observable<void> {
//     const params = new HttpParams().set('fecha', fecha).set('hora', hora);
//     return this.http.post<void>(`${this.apiUrl}/trabajador/marcar-no-disponible`, null, { params });
//   }

//   // ---- OBTENER ID TRABAJADOR (USUARIO LOGUEADO) ----
//   getIdTrabajadorConToken(): Observable<number> {
//     const token = localStorage.getItem('token');
//     return this.http.get<number>(`${this.apiUrl}/usuario/id`, {
//       headers: { Authorization: `Bearer ${token}` }
//     });
//   }

//   // ---- BORRAR ----
//   delete(id: number) {
//     return this.http.delete(`${this.apiUrl}/borrar/${id}`);
//   }

//   marcarHoraDisponible(idTrabajador: number, fecha: string, hora: string): Observable<void> {
//   const token = localStorage.getItem('token');

//   const params = new HttpParams()
//     .set('idTrabajador', idTrabajador)
//     .set('fecha', fecha)
//     .set('hora', hora);

//   return this.http.post<void>(`${this.apiUrl}/marcar-disponible`, null, {
//     headers: { Authorization: `Bearer ${token}` },
//     params
//   });
// }

// }
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WorkerscheduleServiceService {

  private apiUrl = "http://localhost:8081/TRABAJADOR/HORARIO";

  constructor(private http: HttpClient) {}

  // ---- LISTA ----
  getHorasDisponibles(fecha: string): Observable<any[]> {
    const params = new HttpParams().set("fecha", fecha);
    return this.http.get<any[]>(`${this.apiUrl}/horas`, { params });
  }

  getTrabajadoresDisponibles(fecha: string, hora: string): Observable<any[]> {
    const params = new HttpParams().set("fecha", fecha).set("hora", hora);
    return this.http.get<any[]>(`${this.apiUrl}/trabajadores`, { params });
  }

  getHorasDisponiblesPorTrabajador(idTrabajador: number, fecha: string): Observable<any[]> {
    const params = new HttpParams().set('idTrabajador', idTrabajador).set('fecha', fecha);
    return this.http.get<any[]>(`${this.apiUrl}/horas-disponibles`, { params });
  }

  // ---- MARCAR NO DISPONIBLE ----
  marcarHoraNoDisponible(idTrabajador: number, fecha: string, hora: string): Observable<void> {
    const token = localStorage.getItem('token');
    const params = new HttpParams().set('idTrabajador', idTrabajador).set('fecha', fecha).set('hora', hora);

    return this.http.post<void>(`${this.apiUrl}/marcar-no-disponible`, null, {
      headers: { Authorization: `Bearer ${token}` },
      params
    });
  }

  marcarHoraNoDisponiblePorTrabajador(fecha: string, hora: string): Observable<void> {
    const params = new HttpParams().set('fecha', fecha).set('hora', hora);
    return this.http.post<void>(`${this.apiUrl}/trabajador/marcar-no-disponible`, null, { params });
  }

  // ---- OBTENER ID TRABAJADOR (USUARIO LOGUEADO) ----
  getIdTrabajadorConToken(): Observable<number> {
    const token = localStorage.getItem('token');
    return this.http.get<number>(`${this.apiUrl}/usuario/id`, {
      headers: { Authorization: `Bearer ${token}` }
    });
  }

  // ---- BORRAR ----
  delete(id: number) {
    return this.http.delete(`${this.apiUrl}/borrar/${id}`);
  }

  // ---- MARCAR DISPONIBLE ----
  marcarHoraDisponible(idTrabajador: number, fecha: string, hora: string): Observable<void> {
    const token = localStorage.getItem('token');

    const params = new HttpParams().set('idTrabajador', idTrabajador).set('fecha', fecha).set('hora', hora);

    return this.http.post<void>(`${this.apiUrl}/marcar-disponible`, null, {
      headers: { Authorization: `Bearer ${token}` },
      params
    });
  }
}
