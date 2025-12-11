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

  guardarCiteService(Finalcite: FinalCite): Observable<any> {
    return this.http.post(`${this.baseUrl}/guardar`, Finalcite);
  }

  getCiteService(): Observable<FinalCite[]> {
    return this.http.get<FinalCite[]>(`${this.baseUrl}/lista`);
  }

  delete(idCita: number): Observable<void> {
  return this.http.delete<void>(`${this.baseUrl}/borrar/${idCita}`);
}

}
