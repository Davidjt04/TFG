import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { FinalCite } from '../../entities/FinalCite';

@Injectable({
  providedIn: 'root'
})
export class ClienteStateServiceService {

private citaSubject = new BehaviorSubject<FinalCite | null>(this.getCitaLocalStorage());
  public cita$ = this.citaSubject.asObservable();

  // Obtener cita desde localStorage
  private getCitaLocalStorage(): FinalCite | null {
    const cita = localStorage.getItem('citaCliente');
    return cita ? JSON.parse(cita) : null;
  }

  setCita(cita: FinalCite) {
    localStorage.setItem('citaCliente', JSON.stringify(cita));
    this.citaSubject.next(cita);
  }

  clearCita() {
    localStorage.removeItem('citaCliente');
    this.citaSubject.next(null);
  }
}