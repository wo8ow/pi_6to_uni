import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Empresa } from '../../core/models/empresa.model';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="p-6">
      <div class="flex justify-between items-center mb-6">
        <h1 class="text-3xl font-bold">Dashboard Empresas</h1>
        <button (click)="logout()" class="bg-red-500 text-white px-4 py-2 rounded">Salir</button>
      </div>
      
      <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
        <div *ngFor="let emp of empresas" class="bg-white p-4 rounded shadow border-l-4 border-blue-500">
          <h3 class="font-bold text-lg">{{ emp.nombre }}</h3>
          <p class="text-gray-600">RUC: {{ emp.identificacion }}</p>
          <p class="text-sm text-gray-500">Sector: {{ emp.sector }}</p>
          <span class="inline-block mt-2 px-2 py-1 text-xs rounded" 
                [ngClass]="emp.activo ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'">
            {{ emp.activo ? 'Activo' : 'Inactivo' }}
          </span>
        </div>
      </div>
    </div>
  `
})
export class DashboardComponent implements OnInit {
  http = inject(HttpClient);
  auth = inject(AuthService);
  empresas: Empresa[] = [];

  ngOnInit() {
    this.http.get<Empresa[]>(`${environment.apiUrl}/empresas`)
      .subscribe(data => this.empresas = data);
  }

  logout() {
    this.auth.logout();
    window.location.reload();
  }
}