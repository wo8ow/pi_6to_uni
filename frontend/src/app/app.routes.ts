import { Routes } from '@angular/router';
import { LoginComponent } from './features/login/login.component';
import { DashboardComponent } from './features/dashboard/dashboard.component';
//import { ListaEmpresasComponent } from './features/empresas/lista-empresas/lista-empresas';
//import { FormEmpresaComponent } from './features/empresas/form-empresa/form-empresa';
//import { CatalogoCuentasComponent } from './features/plan-cuentas/catalogo-cuentas/catalogo-cuentas';
import { GestionPeriodosComponent } from './features/periodos/gestion-periodos/gestion-periodos';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', runGuardsAndResolvers: 'always', canActivate: [authGuard], children: 
    [
    { path: 'dashboard', component: DashboardComponent},
    //{ path: 'empresas', component: ListaEmpresasComponent}, 
    //{ path: 'empresas/form', component: FormEmpresaComponent},
    //{ path: 'catalogo-cuentas', component: CatalogoCuentasComponent}
    { path: 'periodos', component: GestionPeriodosComponent}
    
    ] 
},
  { path: '**', redirectTo: 'login' }
];