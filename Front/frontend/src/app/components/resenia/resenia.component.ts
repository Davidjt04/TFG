import { Component, OnInit } from '@angular/core';
import { Review } from '../../entities/Review';
import { ReviewService } from '../../services/Review/review.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-resenia',
  imports: [FormsModule,CommonModule],
  templateUrl: './resenia.component.html',
  styleUrls: ['./resenia.component.css']
})
export class ReseniaComponent implements OnInit {

  reviews: Review[] = [];
  nuevaReview: Review = new Review();

  constructor(private reviewService: ReviewService) {}

  ngOnInit(): void {
    this.cargarReviews();
  }

  cargarReviews() {
    this.reviewService.getAll().subscribe((res: Review[]) => {
      this.reviews = res;
    });
  }

  guardarReview() {
    // Asegurarse de que las estrellas estén entre 0 y 5
    if (this.nuevaReview.estrellas > 5) {
      this.nuevaReview.estrellas = 5;
    } else if (this.nuevaReview.estrellas < 0) {
      this.nuevaReview.estrellas = 0;
    }

    this.nuevaReview.hora = new Date();

    this.reviewService.guardarReview(this.nuevaReview).subscribe((res: Review) => {
      this.reviews.push(res); // añadir al listado
      this.nuevaReview = new Review(); // limpiar formulario
    });
  }

  // Método para renderizar estrellas
  obtenerEstrellas(n: number): boolean[] {
    return Array.from({ length: 5 }, (_, i) => i < n);
  }

}
