export interface FinalCite {
  idCita: number;
  fecha: string;           // LocalDate → string ISO: "2025-01-12"
  hora: string;            // LocalTime → string: "16:30"
  precioCorte: number;
  nombreCorte: string;
  nombreTrabajador: string; // Ahora es string
}
