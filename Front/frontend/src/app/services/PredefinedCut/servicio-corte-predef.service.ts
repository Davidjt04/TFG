import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServicioCortePredefService {

  constructor(private http: HttpClient) {}
  private baseUrl = 'http://localhost:8081/cortePredefinido';
  

  // Obtener lista de trabajadores
  getCortePredef(): Observable<any> {
    return this.http.get(`${this.baseUrl}/lista`);
  }

  // Obtener CortePredef por ID (para edición)
  editarCortePredef(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/editar/${id}`); 
  }

  // Crear nuevo CortePredef (opcional, solo si quieres iniciar con objeto vacío desde el backend)
  crearCortePredef(): Observable<any> {
    return this.http.post(`${this.baseUrl}/crear`, {});
  }

  // Guardar CortePredef (crear o actualizar según si hay id o no)
  guardarCortePredef(arbitro: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/guardar`, arbitro);
  }

  // Borrar CortePredef
  borrarCortePredef(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/borrar/${id}`);
  }


}



