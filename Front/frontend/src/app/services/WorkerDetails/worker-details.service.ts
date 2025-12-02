import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WorkerDetails } from '../../entities/WorkerDetails';

@Injectable({
  providedIn: 'root'
})
export class WorkerDetailsService {

  private baseUrl = 'http://localhost:8081/detallesTrabajador';

  constructor(private http: HttpClient) {}

  getTrabajadores(): Observable<WorkerDetails[]> {
    return this.http.get<WorkerDetails[]>(`${this.baseUrl}/lista`);
  }

  editarTrabajador(id: number): Observable<WorkerDetails> {
    return this.http.get<WorkerDetails>(`${this.baseUrl}/editar/${id}`);
  }

  crearTrabajador(): Observable<WorkerDetails> {
    return this.http.post<WorkerDetails>(`${this.baseUrl}/crear`, {});
  }

  guardarTrabajador(detalle: WorkerDetails): Observable<WorkerDetails> {
    return this.http.post<WorkerDetails>(`${this.baseUrl}/guardar`, detalle);
  }

  borrarTrabajador(id: number): Observable<void> {
    return this.http.get<void>(`${this.baseUrl}/borrar/${id}`);
  }

}
