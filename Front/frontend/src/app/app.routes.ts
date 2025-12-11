// import { Routes } from '@angular/router';
import { ServiciosClienteComponent } from './components/servicios-cliente/servicios-cliente.component';
import { ImagenesClienteComponent } from './components/imagenes-cliente/imagenes-cliente.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { ServicioComponent } from './components/servicios-admin/servicios-admin.component';
import { Routes } from '@angular/router';
import { InfoAdminComponent } from './components/info-admin/info-admin.component';
import { ImagenesAdminComponent } from './components/imagenes-admin/imagenes-admin.component';
import { ReseniaComponent } from './components/resenia/resenia.component';
import { TrabajadoresAdminComponent } from './components/trabajadores-admin/trabajadores-admin.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { JustificantesTrabajadorComponent } from './components/justificantes-trabajador/justificantes-trabajador.component';
import { InfoClienteComponent } from './components/info-cliente/info-cliente.component';
import { TiendaClienteComponent } from './components/tienda-cliente/tienda-cliente.component';
import { TiendaAdminComponent } from './components/tienda-admin/tienda-admin.component';
import { CarritoComponent } from './components/carrito/carrito.component';
import { CancelarCitaComponent } from './components/cancelar-cita/cancelar-cita.component';
import { PagoComponent } from './components/pago/pago.component';
// import { ImagenesAdminComponent } from './components/imagenes-admin/imagenes-admin.component';

export const routes: Routes = [
    // //ADMIN ROUTES
    // { path: 'ADMIN/cita/servicios', component: ServicioComponent },
    // { path: 'ADMIN/cita/imagenes', component: ImagenesAdminComponent },
    // { path: 'ADMIN/cita/info', component: InfoAdminComponent },
    // { path: 'ADMIN/cita/trabajadores', component: TrabajadoresAdminComponent },
    // // { path: 'ADMIN/tienda', component: HomeComponent },

    // //WORKER ROUTES
    // // { path: 'TRBAJADOR/calendario', component: HomeComponent },
  
    // //CUSTOMER ROUTES
    // // { path: '', component: ServiciosClienteComponent },//------
    // { path: 'CLIENTE/cita/servicios', component: ServiciosClienteComponent },//------
    // { path: 'CLIENTE/cita/imagenes', component: ImagenesClienteComponent },
    // // { path: 'CLIENTE/cita/info', component: HomeComponent },
    // // { path: 'CLIENTE/tienda', component: HomeComponent },
    // // { path: 'CLIENTE/tienda/saldo', component: HomeComponent },


    // //RUTAS GENERALES
    // { path: '', component: LandingPageComponent },//TODO CAMBIAR LA URL DE ESTA RUTA PORQUE ES LA PRINCIPAL
    // { path: 'login', component: LoginComponent },
    // { path: 'registro', component: RegisterComponent },
    //     // { path: 'partido/lista', component: PartidoComponent },
    // { path: 'review', component: ReseniaComponent }



//   // Rutas públicas
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'landing', component: LandingPageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegisterComponent },
  { path: 'review', component: ReseniaComponent },


//   // Rutas ADMIN
  { path: 'ADMIN/cita/servicios', component: ServicioComponent },
  { path: 'ADMIN/cita/imagenes', component: ImagenesAdminComponent },
  { path: 'ADMIN/cita/info', component: InfoAdminComponent },
  { path: 'ADMIN/cita/trabajadores', component: TrabajadoresAdminComponent },
  {path: 'ADMIN/tienda', component: TiendaAdminComponent },


//   // Rutas CLIENTE
  { path: 'CLIENTE/cita/servicios', component: ServiciosClienteComponent },
  { path: 'CLIENTE/cancelarCita', component: CancelarCitaComponent },
  {path: 'CLIENTE/cita/info', component: InfoClienteComponent },
  {path: 'CLIENTE/tienda', component: TiendaClienteComponent },
  {path: 'CLIENTE/tienda/pago', component: PagoComponent },
  {path: 'CLIENTE/carrito', component: CarritoComponent },


//   // Rutas TRABAJADOR
  { path: 'TRABAJADOR/calendario', component: JustificantesTrabajadorComponent },

//   // Ruta comodín para 404
  { path: '**', redirectTo: '' }
];

