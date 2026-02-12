import { Routes } from '@angular/router';
import { LoginComponent } from './features/login/login.component';
import { DashboardComponent } from './features/dashboard/dashboard.component';
//import { ListaEmpresasComponent } from './features/empresas/lista-empresas/lista-empresas';
//import { FormEmpresaComponent } from './features/empresas/form-empresa/form-empresa';
//import { CatalogoCuentasComponent } from './features/plan-cuentas/catalogo-cuentas/catalogo-cuentas';
import { GestionPeriodosComponent } from './features/periodos/gestion-periodos/gestion-periodos';
import { authGuard } from './core/guards/auth.guard';
import { InicioComponent } from './features/inicio/inicio.component';

export const routes: Routes = [
  { path: '', component: InicioComponent },
  { path: 'login', component: LoginComponent },
  { path: 'app', runGuardsAndResolvers: 'always', canActivate: [authGuard], children: 
    [
    { path: 'dashboard', component: DashboardComponent},
    { path: 'periodos', component: GestionPeriodosComponent},
    { path: '', pathMatch: 'full', redirectTo: 'dashboard' }
    
    ] 
},
  { path: 'dashboard', redirectTo: 'app/dashboard', pathMatch: 'full' },
  { path: 'periodos', redirectTo: 'app/periodos', pathMatch: 'full' },
  { path: '**', redirectTo: '' }
];