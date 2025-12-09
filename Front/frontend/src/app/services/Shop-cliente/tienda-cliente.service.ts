import { HttpClient } from "@angular/common/http";
import { Article } from "../../entities/Article";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class ClienteArticleService {

  private baseUrl = 'http://localhost:8081/articulo';

  constructor(private http: HttpClient) {}

  // ✅ LISTAR ARTÍCULOS (TIENDA CLIENTE)
  getArticulos(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.baseUrl}/lista`);
  }

  // ✅ VER DETALLE
  editarArticulo(id: number): Observable<Article> {
    return this.http.get<Article>(`${this.baseUrl}/editar/${id}`);
  }
}
