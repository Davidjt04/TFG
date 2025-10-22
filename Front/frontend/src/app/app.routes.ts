import { Routes } from '@angular/router';
import { ServiciosClienteComponent } from './components/servicios-cliente/servicios-cliente.component';
import { ImagenesClienteComponent } from './components/imagenes-cliente/imagenes-cliente.component';

export const routes: Routes = [
    //ADMIN ROUTES
    // { path: 'ADMIN/cita/servicios', component: HomeComponent },
    // { path: 'ADMIN/cita/imagenes', component: HomeComponent },
    // { path: 'ADMIN/cita/info', component: HomeComponent },
    // { path: 'ADMIN/cita/trabajadores', component: HomeComponent },
    // { path: 'ADMIN/tienda', component: HomeComponent },

    //WORKER ROUTES
    // { path: 'TRBAJADOR/calendario', component: HomeComponent },
  
    //CUSTOMER ROUTES
    { path: 'CLIENTE/cita/servicios', component: ServiciosClienteComponent },//------
    { path: 'CLIENTE/cita/imagenes', component: ImagenesClienteComponent },
    // { path: 'CLIENTE/cita/info', component: HomeComponent },
    // { path: 'CLIENTE/tienda', component: HomeComponent },
    // { path: 'CLIENTE/tienda/saldo', component: HomeComponent },


    // { path: 'login', component: LoginComponent },
    // { path: 'registro', component: RegisterComponent },
    // { path: 'dashboard', component: DashboardComponent },
        // { path: 'partido/lista', component: PartidoComponent },

];
