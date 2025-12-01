import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Service } from '../../entities/Service';

@Injectable({
  providedIn: 'root'
})
export class ServiciosService {

  private baseUrl = 'http://localhost:8081/service';

  constructor(private http: HttpClient) {}

  // Obtener lista de servicios
  getServicios(): Observable<Service[]> {
    return this.http.get<Service[]>(`${this.baseUrl}/lista`);
  }

  // Obtener servicio por ID (para edición)
  editarServicios(id: number): Observable<Service> {
    return this.http.get<Service>(`${this.baseUrl}/editar/${id}`); 
  }

  // Crear nuevo servicio (inicia con objeto vacío desde el backend)
  crearServicios(): Observable<Service> {
    return this.http.post<Service>(`${this.baseUrl}/crear`, {});
  }

  // Guardar servicio (crear o actualizar)
  guardarServicios(servicio: Service): Observable<Service> {
    return this.http.post<Service>(`${this.baseUrl}/guardar`, servicio);
  }

  // Borrar servicio
  borrarServicios(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/borrar/${id}`);
  }

}
