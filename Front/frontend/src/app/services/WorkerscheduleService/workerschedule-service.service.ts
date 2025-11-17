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
}
