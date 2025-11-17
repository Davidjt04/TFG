import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiciosService {

  constructor(private http: HttpClient) {}
  private baseUrl = 'http://localhost:8081/service';
  

  // Obtener lista de servicios
  getServicios(): Observable<any> {
    return this.http.get(`${this.baseUrl}/lista`);
  }

  // Obtener servicios por ID (para edición)
  editarServicios(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/editar/${id}`); 
  }

  // Crear nuevo servicios (opcional, solo si quieres iniciar con objeto vacío desde el backend)
  crearServicios(): Observable<any> {
    return this.http.post(`${this.baseUrl}/crear`, {});
  }

  // Guardar servicios (crear o actualizar según si hay id o no)
  guardarServicios(arbitro: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/guardar`, arbitro);
  }

  // Borrar servicios
  borrarServicios(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/borrar/${id}`);
  }

}
