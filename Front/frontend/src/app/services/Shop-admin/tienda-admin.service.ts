import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from '../../entities/Article';

@Injectable({
  providedIn: 'root'
})
export class AdminArticleService {

  private baseUrl = 'http://localhost:8081/articulo';

  constructor(private http: HttpClient) {}

  // ✅ LISTAR
  getArticulos(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.baseUrl}/lista`);
  }

  // ✅ EDITAR (OBTENER)
  editarArticulo(id: number): Observable<Article> {
    return this.http.get<Article>(`${this.baseUrl}/editar/${id}`);
  }

  // ✅ CREAR (VACÍO)
  crearArticulo(): Observable<Article> {
    return this.http.post<Article>(`${this.baseUrl}/crear`, {});
  }

  // ✅ GUARDAR
  guardarArticulo(articulo: Article): Observable<Article> {
    return this.http.post<Article>(`${this.baseUrl}/guardar`, articulo);
  }

  // ✅ BORRAR
  borrarArticulo(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/borrar/${id}`);
  }
}

