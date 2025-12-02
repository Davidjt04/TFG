import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from '../../entities/Review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private baseUrl = 'http://localhost:8081/Review';

  constructor(private http: HttpClient) {}

  // Obtener todas las reseñas
  getAll(): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.baseUrl}/review/lista`);
  }

  // Obtener reseña por ID (para edición)
  editarReview(id: number): Observable<Review> {
    return this.http.get<Review>(`${this.baseUrl}/review/editar/${id}`);
  }

  // Crear nueva reseña (inicial vacía desde backend)
  crearReview(): Observable<Review> {
    return this.http.post<Review>(`${this.baseUrl}/review/crear`, {});
  }

  // Guardar reseña (crear o actualizar)
  guardarReview(review: Review): Observable<Review> {
    return this.http.post<Review>(`${this.baseUrl}/review/guardar`, review);
  }

  // Borrar reseña
  borrarReview(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/review/borrar/${id}`);
  }
}
