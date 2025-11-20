import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FinalCite } from '../../entities/FinalCite';

@Injectable({
  providedIn: 'root'
})
export class FinalCiteService {

  private baseUrl = "http://localhost:8081/cita";

  constructor(private http: HttpClient) { }

  
  // Guardar CortePredef (crear o actualizar según si hay id o no)
  guardarCiteService(Finalcite: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/guardar`, Finalcite);
  }

  getCiteService(): Observable<any> {
    return this.http.get(`${this.baseUrl}/lista`);
  }
}
