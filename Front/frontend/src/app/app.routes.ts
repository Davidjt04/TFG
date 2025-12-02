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
// import { ImagenesAdminComponent } from './components/imagenes-admin/imagenes-admin.component';

export const routes: Routes = [
    //ADMIN ROUTES
    { path: 'ADMIN/cita/servicios', component: ServicioComponent },
    { path: 'ADMIN/cita/imagenes', component: ImagenesAdminComponent },
    { path: 'ADMIN/cita/info', component: InfoAdminComponent },
    { path: 'ADMIN/cita/trabajadores', component: TrabajadoresAdminComponent },
    // { path: 'ADMIN/tienda', component: HomeComponent },

    //WORKER ROUTES
    // { path: 'TRBAJADOR/calendario', component: HomeComponent },
  
    //CUSTOMER ROUTES
    // { path: '', component: ServiciosClienteComponent },//------
    { path: 'CLIENTE/cita/servicios', component: ServiciosClienteComponent },//------
    { path: 'CLIENTE/cita/imagenes', component: ImagenesClienteComponent },
    // { path: 'CLIENTE/cita/info', component: HomeComponent },
    // { path: 'CLIENTE/tienda', component: HomeComponent },
    // { path: 'CLIENTE/tienda/saldo', component: HomeComponent },


    //RUTAS GENERALES
    { path: '', component: LandingPageComponent },//TODO CAMBIAR LA URL DE ESTA RUTA PORQUE ES LA PRINCIPAL
    // { path: 'login', component: LoginComponent },
    // { path: 'registro', component: RegisterComponent },
        // { path: 'partido/lista', component: PartidoComponent },
    { path: 'review', component: ReseniaComponent }

];

// import { Routes } from '@angular/router';

// export const routes: Routes = [
//   // ADMIN
//   {
//     path: 'ADMIN/cita/servicios',
//     loadComponent: () =>
//       import('./components/servicios-admin/servicios-admin.component')
//         .then(m => m.ServicioComponent)
//   },

//   // CLIENTE
//   {
//     path: 'CLIENTE/cita/servicios',
//     loadComponent: () =>
//       import('./components/servicios-cliente/servicios-cliente.component')
//         .then(m => m.ServiciosClienteComponent)
//   },
//   {
//     path: 'CLIENTE/cita/imagenes',
//     loadComponent: () =>
//       import('./components/imagenes-cliente/imagenes-cliente.component')
//         .then(m => m.ImagenesClienteComponent)
//   },

//   // LANDING
//   {
//     path: '',
//     loadComponent: () =>
//       import('./components/landing-page/landing-page.component')
//         .then(m => m.LandingPageComponent)
//   }
// ];

