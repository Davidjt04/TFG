import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ClienteStateServiceService } from '../../services/ClienteStateService/cliente-state-service.service';

@Component({
  selector: 'app-cliente-nav',
  imports: [RouterLink],
  templateUrl: './cliente-nav.component.html',
  styleUrl: './cliente-nav.component.css'
})
export class ClienteNavComponent implements OnInit{
citaDisponible = false;

  constructor(private clienteState: ClienteStateServiceService) {}

  ngOnInit(): void {
    // Nos suscribimos a los cambios en la cita
    this.clienteState.cita$.subscribe(cita => {
      this.citaDisponible = !!cita;
      console.log('Navbar - cita disponible:', this.citaDisponible);
    });
  }
}
